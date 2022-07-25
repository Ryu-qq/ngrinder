package com.connectly.luxury.be.ngrinder.stress

import HTTPClient.Cookie
import HTTPClient.HTTPResponse
import HTTPClient.NVPair
import com.connectly.luxury.be.ngrinder.dto.AddOrderRequestDto
import com.connectly.luxury.be.ngrinder.dto.ImportPayloadRequestDto
import com.connectly.luxury.be.ngrinder.dto.ImportPayloadResponseDto
import com.connectly.luxury.be.ngrinder.dto.MemberDeliveryAddressDto
import com.connectly.luxury.be.ngrinder.dto.PaymentMethodDto
import com.connectly.luxury.be.ngrinder.lib.MemberEntity
import com.google.gson.Gson
import groovy.json.JsonSlurper
import net.grinder.plugin.http.HTTPPluginControl
import net.grinder.plugin.http.HTTPRequest
import net.grinder.script.GTest
import net.grinder.scriptengine.groovy.junit.GrinderRunner
import net.grinder.scriptengine.groovy.junit.annotation.BeforeProcess
import net.grinder.scriptengine.groovy.junit.annotation.BeforeThread
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.time.LocalDate

import static net.grinder.script.Grinder.grinder
import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat

@RunWith(GrinderRunner)
class OrderTest {

    public static GTest test1
    public static GTest test2
    public static GTest test3

    public static HTTPRequest request
    public static NVPair[] headers = []
    public static NVPair[] params = []
    public static Cookie[] cookies = []


    @BeforeProcess
    static void beforeProcess() {
        HTTPPluginControl.getConnectionDefaults().timeout = 12000

        test1 = new GTest(1, "GET  api/v1/member")
        test2 = new GTest(2, "POST  api/v1/addCart")
        test3 = new GTest(3, "GET  api/v1/product/{productId}")

        request = new HTTPRequest()

        headers=[new NVPair("Content-Type","application/json")]
        grinder.logger.info("before process.")
    }

    //http://localhost:8089/oauth2/authorization/mock?redirect_uri=https://connectluxury.page.link/oauth?client_os=$clientOS"


    @BeforeThread
    void beforeThread() {
        test1.record(this, "test1")
        HTTPPluginControl.getConnectionDefaults().followRedirects = true
        grinder.statistics.delayReports = true
    }

    @Before
    public void before() {

        HTTPResponse response = request.GET("http://localhost:8089/oauth2/authorization/mock?redirect_uri=http://localhost:9000/login/redirect?client_os=mock")

        if (response.statusCode == 302) {
            def text = response.getText();
            println "text = $text"
        }


        def jsonMsg = new JsonSlurper().parseText(response.getText())
        println "jsonMsg = $jsonMsg"
        headers = [
                new NVPair("Authorization", "Bearer " + jsonMsg.token),
        ]
        request.setHeaders(headers)
        grinder.logger.info("before. init headers and cookies")
    }

    public static MemberEntity member;

    @Test
    void test1() {
        HTTPResponse response = request.GET("http://localhost:8089/api/v1/member")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning. get AccessToken fail {}.", response.statusCode)
        } else {
            grinder.logger.info("get member success {}.", response.getText())
            def jsonMsg = new JsonSlurper().parseText(response.getText())
            member = new MemberEntity(
                    jsonMsg.data.memberId,
                    jsonMsg.data.memberName,
                    jsonMsg.data.email,
                    jsonMsg.data.memberType,
                    jsonMsg.data.memberGrade,
                    jsonMsg.data.blackListType,
                    jsonMsg.data.providerType,
                    jsonMsg.data.authYn,
                    jsonMsg.data.blackListYn,
                    jsonMsg.data.lastLoginDate,
                    jsonMsg.data.mobilePhoneNo,
                    jsonMsg.data.certified,
                    jsonMsg.data.agreementYn,
                    jsonMsg.data.adAcceptYn,
                    jsonMsg.data.deleteYn,
                    jsonMsg.data.insertDate,
                    jsonMsg.data.updateDate,
                    jsonMsg.data.insertOperator,
                    jsonMsg.data.updateOperator);

            grinder.logger.info("get member success {}.", member)

            assertThat(response.statusCode, is(200))
        }
    }

    public static long cartId;
    private static AddOrderRequestDto addOrderRequestDto;
    @Test
    void test2() {

        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]

        def body ='{"productId":2,"productQuantity":1}'

        HTTPResponse response = request.POST("http://localhost:8089/api/v1/addCart", body.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning. get AccessToken fail {}.", response.statusCode)
        } else {
            grinder.logger.info("get productInfo success {}.", response.getText())

            assertThat(response.statusCode, is(200))
        }


        def jsonMsg1 = new JsonSlurper().parseText(response.getText())


        def body1 ='{"cartIds":['+ cartId +']}'

        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse response2 = request.POST("http://localhost:8089/api/v1/getOrder", body1.getBytes(), params)

        if (response2.statusCode == 401 || response2.statusCode == 403 || response2.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response2.statusCode)
        } else {
            grinder.logger.info("get order success {}.", response2.getText())
            assertThat(response2.statusCode, is(200))
        }

        def jsonMsg = new JsonSlurper().parseText(response2.getText())

        def memberDeliveryAddress = MemberDeliveryAddressDto.builder()
                                    .memberId(member.getMemberId())
                                    .receiverName("류상원")
                                    .deliveryAddressName("test주소")
                                    .deliveryAddressId(1L)
                                    .receiverMobilePhoneNo("01011111111")
                                    .receiverZipCode("12345")
                                    .receiverRoadAddress("새창로 70")
                                    .receiverAddressDetail("109-2204")
                                    .receiverDeliveryRequest("")
                                    .defaultDeliveryAddressYn("Y")
                                    .build()


        def paymentMethods = PaymentMethodDto.builder()
                            .paymentMethodId(1L)
                            .paymentMethodName("card")
                            .paymentMethodDisplayName("신용카드")
                            .paymentMethodDisplayYn("Y")
                            .build()


        def importPayloadRequestDto = ImportPayloadRequestDto.builder()
                                        .pg("html5_inicis")
                                        .payMethod("card")
                                        .name(member.getMemberName())
                                        .amount(269100L)
                                        .buyerEmail(member.getEmail())
                                        .buyerName("MOCK")
                                        .buyerTel("01011111111")
                                        .buyerAddr("서울시 용산구 새창로 70 109-2204")
                                        .buyerPostcode("12345")
                                        .build()


        def importPayloadResponseDto =ImportPayloadResponseDto.builder()
                .impUid("mock_" + UUID.randomUUID().toString().substring(0,13))
                .paidAmount(269100L)
                .build();

        List<Long> cartIds = new ArrayList<>();
        cartIds.add(jsonMsg1.data.cartId)

        addOrderRequestDto = AddOrderRequestDto.builder()
                                    .cartIds(cartIds)
                                    .importPayloadRequest(importPayloadRequestDto)
                                    .importPayloadResponse(importPayloadResponseDto)
                                    .paymentMethod(paymentMethods)
                                    .memberDeliveryAddress(memberDeliveryAddress)
                                    .build()

        Gson gson = new Gson();
        String jsonString = gson.toJson(addOrderRequestDto);
        print(jsonString)
        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]

        HTTPResponse response3 = request.POST("http://localhost:8089/api/v1/addOrder", jsonString.getBytes(), params)

        if (response3.statusCode == 401 || response3.statusCode == 403 || response3.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response3.statusCode)
        } else {
            grinder.logger.info("get order success {}.", response3.getText())
            assertThat(response3.statusCode, is(200))
        }
        def jsonMsg3 = new JsonSlurper().parseText(response3.getText())

        addOrderRequestDto.addMerChantUid(jsonMsg3.data);
    }

    @Test
    void test3() {
        //Todo 여기서 Mock서버로 위에 addOrderRequestDto 보내면 이 정보로 confirm, webhook
        //그래서 Mock서버가 익스턴을 찌르고 200응답을 주면 Mock서버가 200응답 고로
        //여기 테스트에서 정상 진행으로 confirm오더로 데이터 보내고
        //Mock서버에선 5초 정도 후에 Webhook을 보낸다.
        Gson gson = new Gson();
        String jsonString = gson.toJson(addOrderRequestDto);

        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]

        HTTPResponse response = request.POST("http://localhost:9000/api/v1/order", jsonString.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400 || response.statusCode == 404) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get confirm success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }

    }


}
