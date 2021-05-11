package stepDefinations;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import pojo.AddPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;

import java.util.regex.Matcher;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.given;


public class StepDefination extends utils {

    RequestSpecification req;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild obj;
    static String getPlaceIdValue;

    @Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_place_payload(String name, String language, String address) throws Throwable {

        obj = new TestDataBuild();
        req=given().spec(requestSpecification())
                .body(obj.addPlacePayload(name,language,address));
    }

    @Given("^DeletePlace payload$")
    public void deletePayload() throws Throwable {
        obj = new TestDataBuild();
        req = given().spec(requestSpecification()).body(obj.deletePayload(getPlaceIdValue));


    }

    @When("^user calls \"([^\"]*)\" using \"([^\"]*)\" http request$")
    public void user_calls_http_request(String resource,String method) throws Throwable {

        APIResources getResourceObj = APIResources.valueOf(resource);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("POST")) {
            response = req.when().post(getResourceObj.getResource()).
                    then().spec(resspec).extract().response();
        }else if(method.equalsIgnoreCase("GET")){
            response = req.when().get(getResourceObj.getResource()).
                    then().spec(resspec).extract().response();
        }
    }


    @Then("^API call is \"([^\"]*)\" with status code \"([^\"]*)\"$")
    public void validateApiCallAndCode(String strArg1, String strArg2) throws Throwable {
        assertEquals(response.getStatusCode(),Integer.parseInt(strArg2));

    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void verifyStatus(String key, String expectedValue) throws Throwable {
        String getActualValue = getJsonPath(response,key);
        assertEquals(getActualValue,expectedValue);
    }

    @And("^verify place_id created that maps \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verifyPlaceidCreatedMapsName(String expectedValue, String resource) throws Throwable {
        getPlaceIdValue = getJsonPath(response,"place_id");
        req=given().spec(requestSpecification()).queryParam("place_id",getPlaceIdValue);
        user_calls_http_request(resource,"GET");
        Assert.assertEquals(getJsonPath(response,"name"),expectedValue);
    }

}
