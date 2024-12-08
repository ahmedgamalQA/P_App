package APIs_Tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import static APiController.PetroAPP_APIsController.*;
import static org.hamcrest.Matchers.*;

public class PetroApp_APIsTests {
    private Response response;
    private static String session_id;


    @Given("the API is available")
    public void theAPIIsAvailable() {
    }
    @When("I send a POST request to {string} with parameters username is {string} and password is {string}")
    public void iSendAPOSTRequestToWithParametersUsernameIsAndPasswordIs(String endPoint, String UserNameValue, String PasswordValue) {
        HashMap<String, String> queries = new HashMap<>();
        queries.put("username", UserNameValue);
        queries.put("password", PasswordValue);
        response =AuthenticateUser(queries, endPoint);
        response
                .then()
                .log().body();
    }
    @Then("the response should have a {int} status code")
    public void theResponseShouldHaveAStatusCode(int ExpectedStatusCode) {
        response.then()
                .assertThat()
                .statusCode(ExpectedStatusCode);
    }
    @And("Verify that a valid Session Id is returned and user_id is {int} and status is {string}")
    public void verifyThatAValidSessionIdIsReturnedAndUser_idIsUser_id(int user_idValue, String ExpectedStatus) {
        response.then()
                .assertThat()
                .body("status", equalTo( ExpectedStatus))
                .body("session_id", not(emptyOrNullString()))
                .body("user_id", equalTo(user_idValue));

        session_id = response.jsonPath().getString("session_id");
        System.out.println("Extracted session_id: " + session_id);
    }
    @When("I send a GET request to {string} with header Session-Id")
    public void iSendAGETRequestToWithHeaderSessionId(String endPoint) {
        response =GetCars(session_id, endPoint);
        response
                .then()
                .log().body();
    }

    @And("Verify that a List of Cars are returned and user_id is {int} and status is {string}")
    public void verifyThatAListOfCarsAreReturnedAndUser_idIsUser_idAndStatusIs(int user_idValue, String ExpectedStatus) {
        response.then()
                .assertThat()
                .body("status", equalTo( ExpectedStatus))
                .body("user_id", equalTo(user_idValue))
                .body("cars", not(empty()))
                .body("cars.car_id", everyItem(notNullValue()))  // Check car_id exists
                .body("cars.make", everyItem(notNullValue()))  // Check make exists
                .body("cars.model", everyItem(notNullValue()))  // Check model exists
                .body("cars.year", everyItem(notNullValue()));  // Check year exists

    }

    @And("Verify the response have status is {string} and the expected message is {string}")
    public void verifyTheResponseHaveStatusIsAndTheExpectedMessageIsMessage(String ExpectedStatus, String ExpectedMessage) {
        response.then()
                .assertThat()
                .body("status", equalTo( ExpectedStatus))
                .body("message", equalTo( ExpectedMessage));
    }

    @And("Assert the IDs of cars is {string}")
    public void assertTheIDsOfCarsIs(String Cars_IDs) {
        // Get the list of car IDs from the response
        List<Integer> carIds = response.jsonPath().getList("cars.car_id");

        // Convert the list of car IDs to a string list for easy comparison
        List<String> carIdsString = carIds.stream()
                .map(String::valueOf)  // Convert each car_id to a string
                .collect(Collectors.toList());

        // Split the expected Cars_IDs string into a list of strings
        List<String> expectedCarIds = Arrays.asList(Cars_IDs.split(","));

        // Assert that the actual car IDs match the expected ones from the Examples table
        assertEquals(expectedCarIds, carIdsString);

        // Optionally, print the result
        System.out.println("Expected car IDs: " + Cars_IDs);
        System.out.println("Actual car IDs: " + carIdsString);
    }

    @When("I send a GET request to {string} with header {string}")
    public void iSendAGETRequestToWithHeader(String endPoint, String session_idValue) {
        response =GetCars(session_idValue, endPoint);
        response
                .then()
                .log().body();
    }
}
