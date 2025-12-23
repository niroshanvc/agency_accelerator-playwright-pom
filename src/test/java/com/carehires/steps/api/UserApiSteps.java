package com.carehires.steps.api;

import com.carehires.api.clients.UserApiClient;
import com.carehires.context.TestContext;
import com.carehires.factories.ApiClientFactory;
import com.microsoft.playwright.APIResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserApiSteps {
    private final TestContext testContext;
    private final UserApiClient userApiClient;
    private APIResponse response;

    public UserApiSteps(TestContext testContext) {
        this.testContext = testContext;
        this.userApiClient = ApiClientFactory.createInstance(UserApiClient.class);
    }

    @Given("I have a valid user payload")
    public void iHaveAValidUserPayload() {
        // Setup payload logic
    }

    @When("I send a POST request to create a user")
    public void iSendAPOSTRequestToCreateAUser() {
        // Mocking the call for the sample structure
        // response = userApiClient.createUser(payload);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        // Assertions.assertEquals(statusCode, response.status());
    }
}
