package LIS.LabtoLab;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;


public class LabtoLabExtrnalLabInvoice {
    public static final String token ="1625|1wVhJl9pyN5TmPcEM0zdCcsdrQ3Hq65oSzH4CFRHdb0ae85b";
    public static final String baseURL="http://beta.lds-stg.com";
    public static final String Endpoint ="/api/V1/reports/lab-to-lab/external-lab-invoice";

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = baseURL;
//        config = RestAssuredConfig.config().redirect(RedirectConfig.redirectConfig().followRedirects(false));
//        config = config(config().encoderConfig(encoderConfig().
//                appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }



    @Test(description = "Verify that the data is retrieved correctly with valid data ignoring optional")
    public void postExternalLabInvoice_Validdata_IgnoreOptional() {
        JSONObject formData = new JSONObject();
        Object startDate = formData.put("start_date", "2023-08-01");// Example start date
        formData.put("end_date", "2024-03-31");   // Example end date
        formData.put("lab_id", "");

        // Make POST request with JSON body and authorization header
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();
        // Assertions or further processing based on the response
        assertEquals(response.getStatusCode(), 200, "Success (200 OK)");
    }

    @Test(description = "Verify that the data is retrieved correctly with valid data")
    public void postExternalLabInvoice_Validdata() {
        JSONObject formData = new JSONObject();
        Object startDate = formData.put("start_date", "2023-08-01");// Example start date
        formData.put("end_date", "2024-03-31");   // Example end date
        formData.put("lab_id", "1");

        // Make POST request with JSON body and authorization header
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();
        // Assertions or further processing based on the response
        assertEquals(response.getStatusCode(), 200, "Success (200 OK)");
    }

    @Test(description = "Verify that the data is retrieved correctly with valid dataand has a pdf attached ")
    public void postExternalLabInvoice_Validdata_PDF() {
        JSONObject formData = new JSONObject();
        Object startDate = formData.put("start_date", "2023-08-01");// Example start date
        formData.put("end_date", "2024-03-31");   // Example end date
        formData.put("lab_id", "1");

        // Make POST request with JSON body and authorization header
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();
        String pdfUrl = response.jsonPath().getString("PDFUrl");
        Assert.assertNotNull(pdfUrl, "PDFUrl is null");
        Assert.assertFalse(pdfUrl.isEmpty(), "PDFUrl is empty");

        // Validate PDFUrl accessibility by sending a GET request
        Response pdfResponse = RestAssured.get(pdfUrl);
        int pdfStatusCode = pdfResponse.getStatusCode();
        Assert.assertEquals(pdfStatusCode, 200, "PDFUrl is  accessible");
    }


    @Test(description = "Verify that the data is retrieved correctly with Minimum date")
    public void postExternalLabInvoice_Validdata_Minimumdate() {
        JSONObject formData = new JSONObject();
        Object startDate = formData.put("start_date", "1970-01-01");// Example start date
        formData.put("end_date", "2024-03-31");   // Example end date
        formData.put("lab_id", "1");

        // Make POST request with JSON body and authorization header
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();
        // Assertions or further processing based on the response
        assertEquals(response.getStatusCode(), 200, "Success (200 OK)");
    }
    @Test(description = "Verify that the data is retrieved correctly with Maximum date")
    public void postExternalLabInvoice_Validdata_Maximumdate() {
        JSONObject formData = new JSONObject();
        Object startDate = formData.put("start_date", "1970-01-01");// Example start date
        formData.put("end_date", "9999-12-31");   // Example end date
        formData.put("lab_id", "1");

        // Make POST request with JSON body and authorization header
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();
        // Assertions or further processing based on the response
        assertEquals(response.getStatusCode(), 200, "Success (200 OK)");
    }

    @Test(description = "Verify that the data is retrieved correctly with Empty data")
    public void postExternalLabInvoice_Emptydata() {
        // Create JSONObject with empty values
        JSONObject formData = new JSONObject();
        formData.put("start_date", "");
        formData.put("end_date", "");
        formData.put("lab_id", "");

        // Make POST request with empty data
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then().extract().response();

        // Assert that the response status code is 422
        assertNotEquals(response.getStatusCode(), 200, "Expected status code 422 (Unprocessable Entity)");
    }
    @Test(description = "Verify that the data is not  retrieved  with Not completed provided data")
    public void postExternalLabInvoice_NotcompletedProvidedData() {
        // Create JSONObject with provided data
        JSONObject formData = new JSONObject();
        formData.put("start_date", "2023-11-05");
        formData.put("end_date", "");
        formData.put("lab_id", "1");

        // Make POST request with provided data
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();

        // Assert that the response status code is 422
        assertNotEquals( response.getStatusCode(), 200,"Expected status code 422 (Unprocessable Entity)");
    }
    @Test(description = "Verify that the data is not  retrieved  with Not completed provided data")
    public void postExternalLabInvoice_NotcompletedProvidedData_2() {
        // Create JSONObject with provided data
        JSONObject formData = new JSONObject();
        formData.put("start_date", "");
        formData.put("end_date", "2023-11-05");
        formData.put("lab_id", "1");

        // Make POST request with provided data
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();

        // Assert that the response status code is 422
        assertNotEquals( response.getStatusCode(), 200,"Expected status code 422 (Unprocessable Entity)");
    }
    @Test(description = "Verify that the data is not  retrieved  with invalid date format ")
    public void postExternalLabInvoice_Invaliddateformat() {
        // Create JSONObject with provided data
        JSONObject formData = new JSONObject();
        formData.put("start_date", "AA");
        formData.put("end_date", "AA");
        formData.put("lab_id", "1");

        // Make POST request with provided data
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();

        // Assert that the response status code is 422
        assertNotEquals( response.getStatusCode(), 200,"Expected status code 422 (Unprocessable Entity)");
    }
    @Test(description = "Verify that the data is not  retrieved  with invalid date format end date < start date  ")
    public void postExternalLabInvoice_Invaliddateformat_EnddatedateBeforeStartDate() {
        // Create JSONObject with provided data
        JSONObject formData = new JSONObject();
        formData.put("start_date", "2024-01-23");
        formData.put("end_date", "2023-10-10");
        formData.put("lab_id", "1");

        // Make POST request with provided data
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(formData.toString()) // Convert JSONObject to string
                .when()
                .post(Endpoint)
                .then()
                .log().body()
                .extract().response();

        // Assert that the response status code is 422
        assertNotEquals( response.getStatusCode(), 200,"Expected status code 422 (Unprocessable Entity)");
    }


}




