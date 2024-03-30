package LIS.QuestionsChoices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class QuestionsChoicesPostOperation {
    public static final String token = "1625|1wVhJl9pyN5TmPcEM0zdCcsdrQ3Hq65oSzH4CFRHdb0ae85b";
    public static final String baseURL = "http://beta.lds-stg.com";
    public static final String endpoint = "/api/V1/tests/QuestionChoice";

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = baseURL;

    }

    @Test(description = "Add a new valid choice ")
    public void postChoices() {
        JSONObject Qchoicedata = new JSONObject();
        Qchoicedata.put("choice", "Choice 2");
        Qchoicedata.put("question_id", 1);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(Qchoicedata.toString()) // Convert JSONObject to string
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .extract().response();
        // Assertions or further processing based on the response
        assertEquals(response.getStatusCode(), 200, "Success (200 OK)");
    }


}
