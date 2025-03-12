package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By addCart = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeButton = By.id("remove-sauce-labs-backpack");
    private final By shoppingCartButton = By.cssSelector("[id=\"shopping_cart_container\"]>a");
    private final By checkoutButton = By.cssSelector("[data-test=\"checkout\"]");
    private final By firstNameCheckout = By.id("first-name");
    private final By lastNameCheckout = By.id("last-name");
    private final By postalCodeCheckout = By.id("postal-code");
    private final By continueCheckout = By.id("continue");
    private final By finishCheckout = By.id("finish");
    private final By orderConfirmationMessage = By.cssSelector("[data-test=\"complete-header\"]");
    private final By errorMessageContainer = By.cssSelector("[class=\"error-message-container error\"]>h3");
    private final By cartItem = By.cssSelector("[class=\"cart_item\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-second timeout
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addCart)).click();
    }

    public String verifyItemIsAdded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton)).getText();
    }

    public void clickCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButton)).click();
    }

    public boolean verifyCartPageItem() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartItem)).isDisplayed();
    }

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void addValueToFirstNameOnCheckoutScreen(String firstName) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameCheckout));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void addValueToLastNameOnCheckoutScreen(String lastName) {
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameCheckout));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void addValueToPostalCodeOnCheckoutScreen(String postalCode) {
        WebElement postalCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeCheckout));
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinueButtonOnCheckoutScreen() {
        wait.until(ExpectedConditions.elementToBeClickable(continueCheckout)).click();
    }

    public void clickFinishButtonOnCheckoutScreen() {
        wait.until(ExpectedConditions.elementToBeClickable(finishCheckout)).click();
    }

    public String getOrderConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationMessage)).getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageContainer)).isDisplayed();
        } catch (Exception e) {
            return false; // Return false if the error message is not found
        }
    }
}