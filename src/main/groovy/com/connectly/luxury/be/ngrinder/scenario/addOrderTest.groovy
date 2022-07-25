package com.connectly.luxury.be.ngrinder.scenario

import HTTPClient.Cookie
import HTTPClient.HTTPResponse
import HTTPClient.NVPair
import com.connectly.luxury.be.ngrinder.lib.MemberEntity
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

import static net.grinder.script.Grinder.grinder
import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat


@RunWith(GrinderRunner)
class addOrderTest {

    public static GTest test1
    public static GTest test2
    public static GTest test3
    public static GTest test4
    public static GTest test5
    public static GTest test6
    public static GTest test7
    public static GTest test8
    public static GTest test9
    public static GTest test10
    public static GTest test11
    public static GTest test12
    public static GTest test13
    public static GTest test14
    public static GTest test15
    public static GTest test16

    public static HTTPRequest request
    public static NVPair[] headers = []
    public static NVPair[] params = []
    public static List<Cookie> cookies = []



    @BeforeProcess
    static void beforeProcess() {
        HTTPPluginControl.getConnectionDefaults().timeout = 12000

        test1 = new GTest(1, "GET /api/v1/productGroup/3?productGroupId=3" )
        test2 = new GTest(2, "GET /api/v1/carts")
        test3 = new GTest(3, "GET /api/v1/review/product/3")
        test4 = new GTest(4, "GET /api/v1/getProductGroupsBySeller/seller1?page=0&size=5")
        test5 = new GTest(5, "GET /api/v1/seller/seller1")
        test6 = new GTest(6, "GET /api/v1/getProductGroupsByBrand/4085?page=0&size=5")
        test7 = new GTest(7, "POST /api/v1/addCart")
        test8 = new GTest(8, "GET /api/v1/member")
        test9 = new GTest(9, "POST /api/v1/getOrder")
        test10 = new GTest(10, "GET /api/v1/countCart")
        test11 = new GTest(11, " POST /api/v1/addDeliveryAddress")
        test12 = new GTest(12, " GET /api/v1/getOrder")
        test13 = new GTest(13, " POST /api/v1/addOrder")
        test14 = new GTest(14, " POST /api/v1/confirmOrder")
        test15 = new GTest(15, " GET api/v1/paymentHistory/59")
        test16 = new GTest(16, " GET /api/v1/countCart")

        request = new HTTPRequest()

        headers=[new NVPair("Content-Type","application/json")]
        grinder.logger.info("before process.")
    }

    @BeforeThread
    void beforeThread() {
        test1.record(this, "test1")
        test2.record(this, "test2")
        test3.record(this, "test3")
        test4.record(this, "test4")
        test5.record(this, "test5")
        test6.record(this, "test6")
        test7.record(this, "test7")
        test8.record(this, "test8")
        test9.record(this, "test9")
        test10.record(this, "test10")
        test11.record(this, "test11")
        test12.record(this, "test12")
        test13.record(this, "test13")
        test14.record(this, "test14")
        test15.record(this, "test15")
        test16.record(this, "test16")



        grinder.statistics.delayReports = true
    }

    @Before
    public void before() {

        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/apple/ngrinderLogin")
        def jsonMsg = new JsonSlurper().parseText(response.getText())

        headers = [
                new NVPair("Authorization", "Bearer " + jsonMsg.data.token),
        ]
        request.setHeaders(headers)

        grinder.logger.info("before. init headers and cookies")
    }


    @Test
    void test1() {

        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/productGroup/3?productGroupId=3")

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test2() {

        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/carts")

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test3() {

        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/review/product/3")

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test4() {

        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/getProductGroupsBySeller/seller1?page=0&size=5")

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test5() {

        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/seller/seller1")

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test6() {

        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/getProductGroupsByBrand/4085?page=0&size=5")

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    public static long cartId;
    public static MemberEntity member;

    @Test
    void test7() {


        def body ='{"productId":14,"productQuantity":1}'
        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse response = request.POST("https://101a-211-192-187-70.jp.ngrok.io/api/v1/addCart", body.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            def jsonMsg = new JsonSlurper().parseText(response.getText())

            cartId = jsonMsg.data.cartId
            assertThat(response.statusCode, is(200))
        }

        HTTPResponse response1 = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/member")

        if (response1.statusCode == 401 || response1.statusCode == 403 || response1.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response1.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response1.getText())
            def jsonMsg = new JsonSlurper().parseText(response1.getText())
            member = new MemberEntity(jsonMsg.data.memberId,
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
            assertThat(response.statusCode, is(200))
        }

        def body1 ='{"cartIds":['+ cartId +']}'
        grinder.logger.warn("get cartId {}", cartId)

        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse response2 = request.POST("https://101a-211-192-187-70.jp.ngrok.io/api/v1/getOrder", body1.getBytes(), params)

        if (response2.statusCode == 401 || response2.statusCode == 403 || response2.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response2.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response2.getText())
            assertThat(response2.statusCode, is(200))
        }




    }

//
//    @Test
//    void test11() {
//
//        def body =' {"deliveryAddressId":null,\n' +
//                ' "deliveryAddressName":"집", +\n' +
//                ' "memberId":' + member.memberId + ',\n' +
//                ' "receiverName":"회원님",\n' +
//                ' "receiverMobilePhoneNo":' + member.mobilePhoneNo + ',\n' +
//                ' "receiverZipCode":"043"receiverRoadAddress":"서울 용산구 새창로 70",\n' +
//                ' "receiverAddressDetail":"109-2204",\n' +
//                ' "receiverDeliveryRequest":null,\n' +
//                ' "defaultDeliveryAddressYn":"Y"}\''
//        params = [
//                new NVPair("Content-Type", "application/json;charset=UTF-8")
//        ]
//
//        HTTPResponse response = request.POST("https://101a-211-192-187-70.jp.ngrok.io/api/v1/addDeliveryAddress", body.getBytes(), params)
//
//        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
//            grinder.logger.warn("Warning.  fail {}", response.statusCode)
//        } else {
//            grinder.logger.info("get acceptAgreement success {}.", response.getText())
//            assertThat(response.statusCode, is(200))
//        }
//    }
//
//    @Test
//    void test12() {
//
//
//        def body ='{"cartIds":['+ cartId +']}'
//        params = [
//                new NVPair("Content-Type", "application/json;charset=UTF-8")
//        ]
//        HTTPResponse response = request.POST("https://101a-211-192-187-70.jp.ngrok.io/api/v1/getOrder", body.getBytes(), params)
//
//        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
//            grinder.logger.warn("Warning.  fail {}", response.statusCode)
//        } else {
//            grinder.logger.info("get acceptAgreement success {}.", response.getText())
//            assertThat(response.statusCode, is(200))
//        }
//    }
//
//    @Test
//    void test13() {
//
//        def body ='{"cartIds":['+ cartId +'],' + '"importPayloadRequest":{"amount":1701900,"buyerAddr":"서울 용산구 새창로 70 109-2204","buyerEmail":"fbtkdals2@naver.com","buyerName":"류상원","buye"04354","buyerTel":"01036817687","merchantUid":"ORDER20220712-61","name":"디몬트 22 F/W 버버리 ...","payMethod":"card","pg":"html5_inicis"},"importPayloadResponse":{"applyNum":nmpUid":"imp_646461355026","merchantUid":"ORDER20220712-61","paidAmount":1701900},"memberDeliveryAddress":{"deliveryAddressId":39,"deliveryAddressName":"집","memberId":"cead00e78","receiverName":"회원님","receiverMobilePhoneNo":"01036817687","receiverZipCode":"04354","receiverRoadAddress":"서울 용산구 새창로 70","receiverAddressDetail":"109-2204","receiRequest":"부재시 휴대폰으로 연락주세요.","defaultDeliveryAddressYn":"Y"},"paymentMethod":{"paymentMethodId":1,"paymentMethodName":"card","paymentMethodDisplayName":"신용카드","payYn":true}}'
//        params = [
//                new NVPair("Content-Type", "application/json;charset=UTF-8")
//        ]
//        HTTPResponse response = request.POST("https://101a-211-192-187-70.jp.ngrok.io/api/v1/addOrder", body.getBytes(), params)
//
//        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
//            grinder.logger.warn("Warning.  fail {}", response.statusCode)
//        } else {
//            grinder.logger.info("get acceptAgreement success {}.", response.getText())
//            assertThat(response.statusCode, is(200))
//        }
//    }
//
//    @Test
//    void test14() {
//
//        def body ='{"cartIds":['+ cartId +'],' + '"importPayloadRequest":{"amount":1701900,"buyerAddr":"서울 용산구 새창로 70 109-2204","buyerEmail":"fbtkdals2@naver.com","buyerName":"류상원","buye"04354","buyerTel":"01036817687","merchantUid":"ORDER20220712-61","name":"디몬트 22 F/W 버버리 ...","payMethod":"card","pg":"html5_inicis"},"importPayloadResponse":{"applyNum":nmpUid":"imp_646461355026","merchantUid":"ORDER20220712-61","paidAmount":1701900},"memberDeliveryAddress":{"deliveryAddressId":39,"deliveryAddressName":"집","memberId":"cead00e78","receiverName":"회원님","receiverMobilePhoneNo":"01036817687","receiverZipCode":"04354","receiverRoadAddress":"서울 용산구 새창로 70","receiverAddressDetail":"109-2204","receiRequest":"부재시 휴대폰으로 연락주세요.","defaultDeliveryAddressYn":"Y"},"paymentMethod":{"paymentMethodId":1,"paymentMethodName":"card","paymentMethodDisplayName":"신용카드","payYn":true}}'
//        params = [
//                new NVPair("Content-Type", "application/json;charset=UTF-8")
//        ]
//
//        HTTPResponse response = request.POST("https://101a-211-192-187-70.jp.ngrok.io/api/v1/confirmOrder", body.getBytes(), params)
//
//        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
//            grinder.logger.warn("Warning.  fail {}", response.statusCode)
//        } else {
//            grinder.logger.info("get acceptAgreement success {}.", response.getText())
//            assertThat(response.statusCode, is(200))
//        }
//    }
//
//    static long paymentId
//
//    @Test
//    void test15() {
//
//        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/v1/paymentHistory/61")
//
//        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
//            grinder.logger.warn("Warning.  fail {}", response.statusCode)
//        } else {
//            grinder.logger.info("get acceptAgreement success {}.", response.getText())
//            assertThat(response.statusCode, is(200))
//        }
//    }
//
//    @Test
//    void test16() {
//
//        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/countCart")
//
//        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
//            grinder.logger.warn("Warning.  fail {}", response.statusCode)
//        } else {
//            grinder.logger.info("get acceptAgreement success {}.", response.getText())
//            assertThat(response.statusCode, is(200))
//        }
//    }




}
