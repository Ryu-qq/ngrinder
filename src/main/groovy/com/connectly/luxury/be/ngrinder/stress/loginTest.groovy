package com.connectly.luxury.be.ngrinder.stress

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

/**
 * login 플로우
 * 회원가입 후 메인화면 까지
 */
@RunWith(GrinderRunner)
class loginTest {
    public static GTest test1
    public static GTest test2
    public static GTest test3
    public static GTest test4
    public static GTest test5
    public static GTest test6
    public static GTest test7
    public static GTest test8

    public static HTTPRequest request
    public static NVPair[] headers = []
    public static NVPair[] params = []
    public static Cookie[] cookies = []

    @BeforeProcess
    static void beforeProcess() {
        HTTPPluginControl.getConnectionDefaults().timeout = 12000

        test1 = new GTest(1, "GET api/v1/member")
        test2 = new GTest(2, "GET  /api/v1/category")
        test3 = new GTest(3, "GET /api/v1/likes")
        test4 = new GTest(4, "POST /api/v1/acceptAgreement")
        test5 = new GTest(5, "GET /api/v1/countCart")
        test6 = new GTest(5, "GET /api/v1/displays")
        test7 = new GTest(6, "POST /api/v1/import/certification")
        test8 = new GTest(7, "GET /api/v1/countCart")

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

    public static MemberEntity member;

    @Test
    void test1() {
        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/member")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning. get AccessToken fail {}.", response.statusCode)
        } else {
            grinder.logger.info("get member success {}.", response.getText())
            def jsonMsg = new JsonSlurper().parseText(response.getText())
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

            grinder.logger.info("get member success {}.", member)

            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test2() {
        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/category")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning {}.", response.statusCode)
        } else {
            grinder.logger.info("get category success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }
    @Test
    void test3() {
        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/likes")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning.  {}.", response.statusCode)
        } else {
            grinder.logger.info("get member likes success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }


    @Test
    void test4() {
        def json = '{"adAcceptYn": "Y" }';
        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse response = request.POST("https://101a-211-192-187-70.jp.ngrok.io/api/v1/acceptAgreement", json.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {

            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }


    @Test
    void test5() {
        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/countCart")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning. {}.", response.statusCode)
        } else {
            grinder.logger.info("get member cart  success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }


    @Test
    void test6() {
        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/displays")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning.  {}.", response.statusCode)
        } else {
            grinder.logger.info("get displays success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test7() {
        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/countCart")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning. {}.", response.statusCode)
        } else {
            grinder.logger.info("get member cart  success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }


    @Test
    void test8() {
        HTTPResponse response = request.GET("https://101a-211-192-187-70.jp.ngrok.io/api/v1/displays")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning.  {}.", response.statusCode)
        } else {
            grinder.logger.info("get displays success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }



//    class RandomStrIssuer {
//
//        private static int NUMBER_LENGTH=5;
//        private static int NUMBER_BOUND=100;
//
//        public static String getRandomNumbers(){
//            Random random = new Random();
//            StringBuilder sb= new StringBuilder();
//            for(int i=0; i<NUMBER_LENGTH; i++){
//                sb.append(String.valueOf(random.nextInt(NUMBER_BOUND)));
//            }
//            return sb.toString();
//        }
//    }

}
