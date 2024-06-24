package org.example.testrail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author myname
 */
public class TestRailManager {

    public static int TEST_RUN_ID = 1;

    public static String TEST_RAIL_USERNAME = "hardik@crenspire.com";

    public static String TEST_RAIL_PASSWORD = "Johnwick@2023";

    public static int TEST_RAIL_PASS = 1;

    public static int TEST_RAIL_FAIL = 5;

    public static String TEST_RAIL_ENGINE_URL = "https://hardik1996.testrail.io/";

    public static void addResultForTestCase(String testCaseId,int status) throws APIException, IOException {
        APIClient apiClient = new APIClient(TEST_RAIL_ENGINE_URL);
        apiClient.setUser(TEST_RAIL_USERNAME);
        apiClient.setPassword(TEST_RAIL_PASSWORD);
        Map<String,Object> data = new HashMap<>();
        data.put("status_id",status);

        apiClient.sendPost("add_result_for_case/" + TEST_RUN_ID + "/" + testCaseId , data);
    }
}
