package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.HomePage;
import page.LoginPage;
import utils.BaseClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddToCartSteps extends BaseClass {
    private static final Logger logger = LoggerFactory.getLogger(AddToCartSteps.class);
    private final String removeButtonTextToBeMatched = "Remove";
    private LoginPage login;
    private HomePage home;

    private void loginUser() {
        logger.info("Logging in user...");
        login = launchApplication();
        login.enterUserName();
        login.enterPassword();
        home = login.clickLoginButton();
    }

    @Given("User is logged In")
    public void user_is_logged_in() {
        loginUser();
    }

    @When("User clicks on add to cart button")
    public void user_clicks_on_add_to_cart_button() {
        logger.info("Clicking on add to cart button...");
        home.clickAddToCart();
    }

    @And("product is added to the cart successfully")
    public void product_is_added_to_the_cart_successfully() {
        String removeButtonText = home.verifyItemIsAdded();
        Assert.assertEquals(removeButtonText, removeButtonTextToBeMatched,
                "Expected 'Remove' button text but found: " + removeButtonText);
    }

    @Then("User clicks on Cart")
    public void user_clicks_on_cart() {
        logger.info("Clicking on cart button...");
        home.clickCartButton();
    }

    @Then("Item should be shown in the cart")
    public void item_should_be_shown_in_the_cart() {
        Assert.assertTrue(home.verifyCartPageItem(), "Item is not displayed in the cart");
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        logger.info("Proceeding to checkout...");
        home.clickCheckoutButton();
    }

    @And("User fills out the checkout form with first name {string}, last name {string}, and postal code {string}")
    public void user_fills_out_the_checkout_form(String firstName, String lastName, String postalCode) {
        logger.info("Filling out checkout form...");
        home.addValueToFirstNameOnCheckoutScreen(firstName);
        home.addValueToLastNameOnCheckoutScreen(lastName);
        home.addValueToPostalCodeOnCheckoutScreen(postalCode);
    }

    @And("User clicks continue on the checkout screen")
    public void user_clicks_continue_on_the_checkout_screen() {
        logger.info("Clicking continue on checkout screen...");
        home.clickContinueButtonOnCheckoutScreen();
    }

    @And("User completes the order")
    public void user_completes_the_order() {
        logger.info("Completing the order...");
        home.clickFinishButtonOnCheckoutScreen();
    }

    @Then("Order confirmation should be displayed")
    public void order_confirmation_should_be_displayed() {
        try {
            String confirmationMessage = home.getOrderConfirmationMessage();
            logger.info("Order confirmation message: {}", confirmationMessage);
            Assert.assertEquals(confirmationMessage, "Thank you for your order!",
                    "Expected confirmation message not found");
        } catch (Exception e) {
            logger.error("Order confirmation message not displayed: {}", e.getMessage());
            Assert.fail("Order confirmation message not displayed: " + e.getMessage());
        }
    }

    @When("User attempts to checkout without filling out the form")
    public void user_attempts_to_checkout_without_filling_out_the_form() {
        logger.info("Attempting to checkout without filling out the form...");
        home.clickCheckoutButton();
        home.clickContinueButtonOnCheckoutScreen();
    }

    @Then("An error message should be displayed")
    public void an_error_message_should_be_displayed() {
        try {
            Assert.assertTrue(home.isErrorMessageDisplayed(), "Error message is not displayed");
        } catch (Exception e) {
            logger.error("Error message not displayed: {}", e.getMessage());
            Assert.fail("Error message not displayed: " + e.getMessage());
        }
    }

    @After
    public void afterScenario() {
        logger.info("Tearing down...");
        tearDown();
    }
}