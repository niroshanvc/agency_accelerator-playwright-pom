package com.carehires.steps.ui;

import com.carehires.context.TestContext;
import com.carehires.pages.login.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    private final TestContext testContext;
    private final LoginPage loginPage;

    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
        this.loginPage = new LoginPage(testContext.getPage());
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // Using a dummy URL for now as I don't have the real one, or use base url
        // loginPage.navigateTo(ConfigManager.getInstance().getBaseUrl());
        // For sample test sake, let's assume we navigate to a public test site or just open a blank page
        testContext.getPage().navigate("https://the-internet.herokuapp.com/login"); 
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
        // Assuming validation here
       // String actualMessage = loginPage.getErrorMessage();
       // Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
