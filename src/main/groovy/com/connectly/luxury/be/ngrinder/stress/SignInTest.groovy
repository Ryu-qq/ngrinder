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


@RunWith(GrinderRunner)
class SignInTest {

    public static GTest test1


    public static HTTPRequest request
    public static NVPair[] headers = []
    public static NVPair[] params = []
    public static Cookie[] cookies = []


    @BeforeProcess
    static void beforeProcess() {
        HTTPPluginControl.getConnectionDefaults().timeout = 12000

        test1 = new GTest(1, "GET  api/v1/member")

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

    @Test
    void test1() {
        HTTPResponse response = request.GET("http://localhost:8089/api/v1/member")

        if (response.statusCode == 401 || response.statusCode == 403) {
            grinder.logger.warn("Warning. get AccessToken fail {}.", response.statusCode)
        } else {
            grinder.logger.info("get member success {}.", response.getText())
            def jsonMsg = new JsonSlurper().parseText(response.getText())
            MemberEntity member = new MemberEntity(
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
}
