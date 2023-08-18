package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class LoginStepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("http://georgiapapadopoulou.eu.185-25-20-64.linux11.name-servers.gr/home.html");
        WebElement title = driver.findElement(By.cssSelector(".display-5.fw-bold.text-white"));
        Assert.assertTrue(title.isDisplayed(),"QA Test Environment");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("User navigates through Login Page")
    public void user_navigates_through_login_page() {
        WebElement loginButton =  driver.findElement(By.id("loginbutton"));
        Assert.assertTrue(loginButton.isDisplayed(), "Go to Login\n" + "                Page");
        loginButton.click();
    }
    @When("User goes through login page should verify the current URL")
    public void user_goes_through_login_page_should_verify_the_current_url() {
        String verifyUrl = driver.getCurrentUrl();
        String expectedUrl = "http://georgiapapadopoulou.eu.185-25-20-64.linux11.name-servers.gr/login.html";
        Assert.assertTrue(verifyUrl.contains(expectedUrl));
    }
    @Then("User evaluates the existance of login web elements")
    public void user_evaluates_the_existance_of_login_web_elements() {
        WebElement title = driver.findElement(By.cssSelector("form#loginForm > .font-weight-normal.h3.mb-3"));
        Assert.assertTrue(title.isDisplayed(),"Please sign in");
        WebElement emailField = driver.findElement(By.id("inputEmail"));
        WebElement passwordField = driver.findElement(By.id("inputPassword"));
        WebElement signInButton = driver.findElement(By.id("signInButton"));
        Assert.assertTrue(emailField.isDisplayed());
        Assert.assertTrue(passwordField.isDisplayed());
        Assert.assertTrue(signInButton.isDisplayed());
    }
    @When("User fills in the email field")
    public void user_fills_in_the_email_field() {
        WebElement emailField = driver.findElement(By.id("inputEmail"));
        emailField.sendKeys("test@example.com");

        WebElement signInButton = driver.findElement(By.id("signInButton"));
    }

    @When("User fills in the password field")
    public  void user_fills_in_the_password_field() {
        WebElement passwordField = driver.findElement(By.id("inputPassword"));
        passwordField.sendKeys("password123");
    }
    @And("User clicks the sign in button")
    public void user_clicks_the_sign_in_button() {
        WebElement signInButton = driver.findElement(By.id("signInButton"));
        signInButton.click();
    }
    @Then("User verifies the success login message")
    public void user_verifies_the_success_login_message() {
        WebElement successMessage1 = driver.findElement(By.cssSelector("div#successAlert > .alert-heading"));
        Assert.assertTrue(successMessage1.isDisplayed());
        String textSuccessMessage1 = successMessage1.getText();
        Assert.assertEquals(textSuccessMessage1, "Well done!");
    }
    @Then("User verifies the email validation error message")
    public void user_verifies_the_email_validation_error_message() {
        WebElement emailErrorValidationMessage = driver.findElement(By.id("emailError"));
        Assert.assertTrue(emailErrorValidationMessage.isDisplayed());
        String textEmailErrorValidationMessage = emailErrorValidationMessage.getText();
        Assert.assertEquals(textEmailErrorValidationMessage, "Email is required.");
    }
    @Then("User verifies the password validation error message")
    public void user_verifies_the_password_validation_error_message() {
        WebElement passwordErrorValidationMessage = driver.findElement(By.id("passwordError"));
        Assert.assertTrue(passwordErrorValidationMessage.isDisplayed());
        String textPasswordErrorValidationMessage = passwordErrorValidationMessage.getText();
        Assert.assertEquals(textPasswordErrorValidationMessage, "Password is required.");
    }
}
