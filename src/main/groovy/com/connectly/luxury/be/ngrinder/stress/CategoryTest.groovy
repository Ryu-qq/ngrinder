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
class CategoryTest {

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
        test1 = new GTest(1, "POST api/v1/getProductGroupOrderByRanking?page=0&size=30")//랭킹
        test2 = new GTest(2, "POST api/v1/getProductGroups?page=0&size=20" ) //여성
        test3 = new GTest(3, "POST api/v1/getProductGroups?page=0&size=20")//남성




        request = new HTTPRequest()

        headers=[new NVPair("Content-Type","application/json")]
        grinder.logger.info("before process.")
    }

    @BeforeThread
    void beforeThread() {
        test1.record(this, "test1")


        HTTPResponse response = request.GET("http://localhost:8089/oauth2/authorization/mock?redirect_uri=http://localhost:8089/oauth?client_os=ios")
        def jsonMsg = new JsonSlurper().parseText(response.getText())
        grinder.logger.warn("결과 값 ---> {}", jsonMsg)

        headers = [
                new NVPair("Authorization", "Bearer " + jsonMsg.data.token),
        ]
        request.setHeaders(headers)


        HTTPResponse categoryResponse = request.GET("http://localhost:8089/api/v1/category")
        def jsonCategory = new JsonSlurper().parseText(categoryResponse.getText())

        grinder.logger.info("category success {}", jsonCategory.data)


        grinder.statistics.delayReports = true
    }

    @Before
    public void before() {

        grinder.logger.info("before. init headers and cookies")
    }


    @Test
    void test1() {

        def body ='{"brandIds": [], "categoryIds":[1216,1217,1218,1219,1220,1221,1222,1223,1224,1225,1226,1227,1228,1229,1230,1231,1232,1233,1234,1235,1236,1237,1238,1239,1240,1241,1242,1243,1244,1245,1246,1247,1248,1249,1250,1251,1252,1253,1254,1255,1256,1257,1258,1259,1260,1261,1262,1263,1264,1265,1266,1267,1268,1269,1270,1271,1272,1273,1274,1275,1276,1277,1278,1279,1280,1281,1282,1283,1284,1285,1286,1287,1288,1289,1290,1291,1292,1293,1294,1295,1296,1297,1298,1299,1300,1301,1302,1303,1304,1305,1306,1307,1308,1309,1310,1311,1312,1313,1314,1315,1316,1317,1318,1319,1320,1321,1322,1323,1324,1325,1326,1327,1328,1329,1330,1331,1332,1333,1334,1335,1336,1337,1338,1339,1340,1341,1342,1343,1344,1345,1346,1347,1348,1349,1350,1351,1352,1353,1354,1355,1356,1357,1358,1359,1360,1361,1362,1363,1364,1365,1366,1367,1368,1369,1370,1371,1372,1373,1374,1375,1376,1377,1378,1379,1380,1381,1382,1383,1384,1385,1386,1387,1388,1389,1390,1391,1392,1393,1394,1395,1396,1397,1398,1399,1400,1401,1402,1403,1404,1405,1406], "minSalePrice": 0, "maxSalePrice": 999999999, "minDiscountRate": 0, "maxDiscountRate": 100}'
        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse response = request.POST("http://localhost:8089/api/v1/getProductGroupsOrderByRanking?page=0&size=30", body.getBytes(), params)

        if (response.statusCode == 401 || response.statusCode == 403 || response.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", response.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", response.getText())
            assertThat(response.statusCode, is(200))
        }
    }

    @Test
    void test2() {

        def body ='{"brandIds": [], "categoryIds": [1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1246, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288, 1289, 1290, 1291, 1292, 1293, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1350, 1351, 1352, 1353, 1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390], "minSalePrice": 0, "maxSalePrice": 999999999, "minDiscountRate": 0, "maxDiscountRate": 100}'
        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse ProductGroup = request.POST("http://localhost:8089/api/v1/getProductGroups?page=0&size=20", body.getBytes(), params)
        HTTPResponse ProductGroupCnt = request.POST("http://localhost:8089/api/v1/getProductGroupsCount", body.getBytes(), params)

        if (ProductGroup.statusCode == 401 || ProductGroup.statusCode == 403 || ProductGroup.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", ProductGroup.statusCode)
        } else {
            grinder.logger.info("get acceptAgreement success {}.", ProductGroup.getText())
            assertThat(ProductGroup.statusCode, is(200))
        }

        if (ProductGroupCnt.statusCode == 401 || ProductGroupCnt.statusCode == 403 || ProductGroupCnt.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", ProductGroupCnt.statusCode)
        } else {
            grinder.logger.info("get success {}.", ProductGroupCnt.getText())
            assertThat(ProductGroupCnt.statusCode, is(200))
        }
    }

    @Test
    void test3() {

        def body ='{"brandIds":[],"categoryIds":[1391,1392,1393,1394,1395,1396,1397,1398,1399,1400,1401,1402,1403,1404,1405,1406,1407,1408,1409,1410,1411,1412,1413,1414,1415,1416,1417,1418,1419,1420,1421,1422,1423,1424,1425,1426,1427,1428,1429,1430,1431,1432,1433,1434,1435,1436,1437,1438,1439,1440,1441,1442,1443,1444,1445,1446,1447,1448,1449,1450,1451,1452,1453,1454,1455,1456,1457,1458,1459,1460,1461,1462,1463,1464,1465,1466,1467,1468,1469,1470,1471,1472,1473,1474,1475,1476,1477,1478,1479,1480,1481,1482,1483,1484,1485,1486,1487,1488,1489,1490,1491,1492,1493,1494,1495,1496,1497,1498,1499,1500,1501,1502,1503,1504,1505,1506,1507,1508,1509,1510,1511,1512,1513,1514,1515,1516,1517,1518,1519,1520,1521,1522,1523,1524,1525,1526,1527,1528,1529,1530,1531,1532,1533,1534,1535,1536,1537,1538,1539,1540,1541,1542],"minSalePrice":0,"maxSalePrice":999999999,"minDiscountRate":0,"maxDiscountRate":100}'
        params = [
                new NVPair("Content-Type", "application/json;charset=UTF-8")
        ]
        HTTPResponse ProductGroup = request.POST("http://localhost:8089/api/v1/getProductGroups?page=0&size=20", body.getBytes(), params)
        HTTPResponse ProductGroupCnt = request.POST("http://localhost:8089/api/v1/getProductGroupsCount", body.getBytes(), params)



        if (ProductGroup.statusCode == 401 || ProductGroup.statusCode == 403 || ProductGroup.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", ProductGroup.statusCode)
        } else {
            grinder.logger.info("get success {}.", ProductGroup.getText())
            assertThat(ProductGroup.statusCode, is(200))
        }

        if (ProductGroupCnt.statusCode == 401 || ProductGroupCnt.statusCode == 403 || ProductGroupCnt.statusCode == 400) {
            grinder.logger.warn("Warning.  fail {}", ProductGroupCnt.statusCode)
        } else {
            grinder.logger.info("get success {}.", ProductGroupCnt.getText())
            assertThat(ProductGroupCnt.statusCode, is(200))
        }
    }
}
