package com.connectly.luxury.be.ngrinder.stress

import HTTPClient.Cookie
import HTTPClient.HTTPResponse
import HTTPClient.NVPair
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
class CategoryWomenTest {

    public static GTest test1
    public static GTest test2
    public static GTest test3
    public static GTest test4
    public static GTest test5
    //public static GTest test6
//    public static GTest test7
//    public static GTest test8

    public static HTTPRequest request
    public static NVPair[] headers = []
    public static NVPair[] params = []
    public static Cookie[] cookies = []

    @BeforeProcess
    static void beforeProcess() {
        HTTPPluginControl.getConnectionDefaults().timeout = 12000

        test1 = new GTest(1, "POST api/v1/getProductGroups?page=0&size=20" ) //여성
        test2 = new GTest(2, "POST api/v1/getProductGroupsCount ")//여성
        test3 = new GTest(3, "POST api/v1/getProductGroups?page=1&size=20" ) //여성
        test4 = new GTest(4, "POST api/v1/getProductGroups?page=2&size=20" ) //여성
        test5 = new GTest(5, "POST api/v1/getProductGroups?page=3&size=20" ) //여성


//        test3 = new GTest(3, "POST api/v1/getProductGroups?page=0&size=20")//남성
//        test4 = new GTest(4, "POST api/v1/getProductGroupsCount")//남성
//        test5 = new GTest(5, "POST api/v1/getProductGroupOrderByRanking?page=0&size=30")//랭킹


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

        HTTPResponse response = request.GET("https://1bb3-211-192-187-70.jp.ngrok.io/api/v1/apple/ngrinderLogin")
        def jsonMsg = new JsonSlurper().parseText(response.getText())

        headers = [
                new NVPair("Authorization", "Bearer " + jsonMsg.data.token),
        ]
        request.setHeaders(headers)


        grinder.statistics.delayReports = true
    }

    @Before
    public void before() {

        grinder.logger.info("before. init headers and cookies")
    }


    @Test
    void test1() {

        def body ='{"brandIds": [], "categoryIds": [1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1246, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288, 1289, 1290, 1291, 1292, 1293, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1350, 1351, 1352, 1353, 1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390], "minSalePrice": 0, "maxSalePrice": 999999999, "minDiscountRate": 0, "maxDiscountRate": 100}'
        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse response = request.POST("https://1bb3-211-192-187-70.jp.ngrok.io/api/v1/getProductGroups?page=0&size=20", body.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test2() {

        def body = '{"brandIds":[],"categoryIds":[1216,1217,1218,1219,1220,1221,1222,1223,1224,1225,1226,1227,1228,1229,1230,1231,1232,1233,1234,1235,1236,1237,1238,1239,1240,1241,1242,1243,1244,1245,1246,1247,1248,1249,1250,1251,1252,1253,1254,1255,1256,1257,1258,1259,1260,1261,1262,1263,1264,1265,1266,1267,1268,1269,1270,1271,1272,1273,1274,1275,1276,1277,1278,1279,1280,1281,1282,1283,1284,1285,1286,1287,1288,1289,1290,1291,1292,1293,1294,1295,1296,1297,1298,1299,1300,1301,1302,1303,1304,1305,1306,1307,1308,1309,1310,1311,1312,1313,1314,1315,1316,1317,1318,1319,1320,1321,1322,1323,1324,1325,1326,1327,1328,1329,1330,1331,1332,1333,1334,1335,1336,1337,1338,1339,1340,1341,1342,1343,1344,1345,1346,1347,1348,1349,1350,1351,1352,1353,1354,1355,1356,1357,1358,1359,1360,1361,1362,1363,1364,1365,1366,1367,1368,1369,1370,1371,1372,1373,1374,1375,1376,1377,1378,1379,1380,1381,1382,1383,1384,1385,1386,1387,1388,1389,1390],"minSalePrice":0,"maxSalePrice":999999999,"minDiscountRate":0,"maxDiscountRate":100}'

        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]

        HTTPResponse response = request.POST("https://1bb3-211-192-187-70.jp.ngrok.io/api/v1/getProductGroupsCount", body.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test3() {

        def body ='{"brandIds": [], "categoryIds": [1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1246, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288, 1289, 1290, 1291, 1292, 1293, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1350, 1351, 1352, 1353, 1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390], "minSalePrice": 0, "maxSalePrice": 999999999, "minDiscountRate": 0, "maxDiscountRate": 100}'
        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse response = request.POST("https://1bb3-211-192-187-70.jp.ngrok.io/api/v1/getProductGroups?page=1&size=20", body.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test4() {

        def body ='{"brandIds": [], "categoryIds": [1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1246, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288, 1289, 1290, 1291, 1292, 1293, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1350, 1351, 1352, 1353, 1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390], "minSalePrice": 0, "maxSalePrice": 999999999, "minDiscountRate": 0, "maxDiscountRate": 100}'

        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]

        HTTPResponse response = request.POST("https://1bb3-211-192-187-70.jp.ngrok.io/api/v1/getProductGroups?page=2&size=20", body.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test5() {

        def body ='{"brandIds": [], "categoryIds": [1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1246, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288, 1289, 1290, 1291, 1292, 1293, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1350, 1351, 1352, 1353, 1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390], "minSalePrice": 0, "maxSalePrice": 999999999, "minDiscountRate": 0, "maxDiscountRate": 100}'

        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]

        HTTPResponse response = request.POST("https://1bb3-211-192-187-70.jp.ngrok.io/api/v1/getProductGroups?page=3&size=20", body.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

}
