package ListingVisits;
import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.Argument;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.apache.commons.lang3.Range.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.testng.Assert.*;

public class ListingVisits {
    public static final String token = "1625|1wVhJl9pyN5TmPcEM0zdCcsdrQ3Hq65oSzH4CFRHdb0ae85b";
    public static final String baseURL = "http://beta.lds-stg.com";
    public static final String endpoint = "/api/V1/visits";

    @Test(description = "Getting the data with the resampling status = true ")
    public void getVisitListByStatusTrue() {
        // Set base URI for RestAssured
        RestAssured.baseURI = baseURL;

        // Make a GET request to retrieve list of visits with status=true
        given()
                .header("Authorization", "Bearer " + token)
                .queryParam("resampling_request", "true") // Add query parameter for resmapling status=true
                .when()
                .get(endpoint)
                .then()
                .assertThat().statusCode(200) // Assert response status code is 200
                .log().body(); // Log response body
    }
    @Test(description = "Getting the data with the resampling status = False ")
    public void getVisitListByStatusFalse() {
        // Set base URI for RestAssured
        RestAssured.baseURI = baseURL;

        // Make a GET request to retrieve list of visits with status=true
        given()
                .header("Authorization", "Bearer " + token)
                .queryParam("resampling_request", "false") // Add query parameter for resmapling status=true
                .when()
                .get(endpoint)
                .then()
                .assertThat().statusCode(200) // Assert response status code is 200
                .log().body();
    }

    @Test (description = " check the data contains the retrieved data or not ")
    public void  ResponseValidationContainsTrue()
    {
        Response resp= given()
                .header("Authorization", "Bearer " + token)
                .queryParam("resampling_request", "true")
                .when()
                .get(baseURL+endpoint);
        ResponseBody body= resp.getBody();
       // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        assertEquals(bodyAsString.contains("resampling_request") , true , "resampling_request contains true");

    }
    @Test (description = " check the data contains the retrieved data or not ")
    public void  ResponseValidationContainsFalse()
    {
        Response resp= given()
                .header("Authorization", "Bearer " + token)
                .queryParam("resampling_request", "false")
                .when()
                .get(baseURL+endpoint);
        ResponseBody body= resp.getBody();
        // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        assertEquals(bodyAsString.contains("resampling_request") , false, "resampling_request contains true");

    }
    @Test(description = "Getting the data with the  status = Confirmed ")
    public void getVisitListByStatusConfirmed() {
       String status ="Confirmed";

        Response resp= given()
                .header("Authorization", "Bearer " + token)
                .queryParam("status", "Confirmed")
                .when()
                .get(baseURL+endpoint);
        ResponseBody body= resp.getBody();
        // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        Assert.assertTrue(bodyAsString.contains(status), "The Status is Confirmed");

    }

    @Test(description = "Getting the data with the  status = Paid ")
    public void getVisitListByStatusPaid() {
        String status ="Paid";

        Response resp= given()
                .header("Authorization", "Bearer " + token)
                .queryParam("status", "Paid")
                .when()
                .get(baseURL+endpoint);
        ResponseBody body= resp.getBody();
        // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        Assert.assertTrue(bodyAsString.contains(status), "The Status is Paid");

    }
    @Test(description = "Getting the data with the  status = Partially Paid ")
    public void getVisitListByStatusP_Paid() {
        String status ="Partially Paid";

        Response resp= given()
                .header("Authorization", "Bearer " + token)
                .queryParam("status", "Partially Paid")
                .when()
                .get(baseURL+endpoint);
        ResponseBody body= resp.getBody();
        // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        Assert.assertTrue(bodyAsString.contains(status), "The Status is Partially Paid");

    }
    @Test(description = "Getting the data with the  status = Completed")
    public void getVisitListByStatusCompleted() {
        String status ="Completed";

        Response resp= given()
                .header("Authorization", "Bearer " + token)
                .queryParam("status", "Completed")
                .when()
                .get(baseURL+endpoint);
        ResponseBody body= resp.getBody();
        // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        Assert.assertTrue(bodyAsString.contains(status), "The Status is Completed");

    }
    @Test(description = "Getting the data with the  status = Refunded")
    public void getVisitListByStatusRefunded() {
        String status = "Refunded";

        Response resp = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("status", "Refunded")
                .when()
                .get(baseURL + endpoint);
        ResponseBody body = resp.getBody();
        // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        Assert.assertTrue(bodyAsString.contains(status), "The Status is Refunded");
    }

    @Test(description = "Getting the data with the  status = Not Completed")
    public void getVisitListByStatusNotCompleted() {
        String status = "Not Completed";

        Response resp = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("status", "Not Completed")
                .when()
                .get(baseURL + endpoint);
        ResponseBody body = resp.getBody();
        // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        Assert.assertTrue(bodyAsString.contains(status), "The Status is Completed");
    }

    @Test(description = "Getting the data with the  status = Not Confirmed")
    public void getVisitListByStatusNotConfirmed() {
        String status = "Not Confirmed";

        Response resp = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("status", "Not Confirmed")
                .when()
                .get(baseURL + endpoint);
        ResponseBody body = resp.getBody();
        // System.out.println("Response Body is: " + body.asString());
        String bodyAsString = body.asString();
        Assert.assertTrue(bodyAsString.contains(status), "The Status is Not Confirmed");
    }



    }





