package stepDefinations;

import io.cucumber.java.en.*;


public class StepDefination {

    @Given("^Add Place Payload$")
    public void add_place_payload() throws Throwable {
    }

    @When("^user calls \"([^\"]*)\" using post http request$")
    public void user_calls_something_using_post_http_request(String strArg1) throws Throwable {
    }

    @Then("^API call is \"([^\"]*)\" with status code \"([^\"]*)\"$")
    public void api_call_is_something_with_status_code_something(String strArg1, String strArg2) throws Throwable {
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String strArg1, String strArg2) throws Throwable {
    }

}
