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
import org.openqa.selenium.JavascriptExecutor;

public class ContactUsStepDefinitions {
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
        Assert.assertTrue(title.isDisplayed(), "QA Test Environment");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("User navigates through Contact Us Page")
    public void user_navigates_through_contact_us_page() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 500);");
        WebElement contactUsButton = driver.findElement(By.id("contactusbutton"));
        Assert.assertTrue(contactUsButton.isDisplayed(), "Go\n" +
                "                to Contact Us\n" +
                "                Page");
        contactUsButton.click();
    }

    @When("User goes through contact us page should verify the current URL")
    public void user_goes_through_contact_us_page_should_verify_the_current_url() {
        String verifyUrl = driver.getCurrentUrl();
        String expectedUrl = "http://georgiapapadopoulou.eu.185-25-20-64.linux11.name-servers.gr/contact_us.html";
        Assert.assertTrue(verifyUrl.contains(expectedUrl));
    }

    @Then("User evaluates the existance of contact us web elements")
    public void user_evaluates_the_existance_of_contact_us_web_elements() {
        WebElement title = driver.findElement(By.cssSelector("form#contactUsForm > .font-weight-normal.h3.mb-3"));
        Assert.assertTrue(title.isDisplayed());
        WebElement firstNameField = driver.findElement(By.id("inputFirstName"));
        WebElement lastNameField = driver.findElement(By.id("inputLastName"));
        WebElement emailField = driver.findElement(By.id("inputEmail"));
        WebElement passwordField = driver.findElement(By.id("inputPassword"));
        WebElement commentsField = driver.findElement(By.cssSelector("form#contactUsForm > textarea[name='message']"));
        WebElement submitButton = driver.findElement(By.id("submitFormButton"));
        Assert.assertTrue(firstNameField.isDisplayed());
        Assert.assertTrue(lastNameField.isDisplayed());
        Assert.assertTrue(emailField.isDisplayed());
        Assert.assertTrue(passwordField.isDisplayed());
        Assert.assertTrue(commentsField.isDisplayed());
        Assert.assertTrue(submitButton.isDisplayed());
    }
    @When("User fills in the first name field")
    public void user_fills_in_the_first_name_field() {
        WebElement firstNameField = driver.findElement(By.id("inputFirstName"));
        firstNameField.sendKeys("Georgia");
    }

    @When("User fills in the last name field")
    public  void user_fills_in_the_last_name_field() {
        WebElement lastNameField = driver.findElement(By.id("inputLastName"));
        lastNameField.sendKeys("Papadopoulou");
    }
    @When("User fills in the email field in form")
    public void user_fills_in_the_email_field_in_form() {
        WebElement emailField = driver.findElement(By.id("inputEmail"));
        emailField.sendKeys("test@example.com");
    }
    @When("User fills in the password field in form")
    public  void user_fills_in_the_password_field_in_form() {
        WebElement passwordField = driver.findElement(By.id("inputPassword"));
        passwordField.sendKeys("password123");
    }
    @When("User fills in the comments field")
    public  void user_fills_in_the_comments_field() {
        WebElement commentsField = driver.findElement(By.cssSelector("form#contactUsForm > textarea[name='message']"));
        commentsField.sendKeys("This is a comment");
    }
    @And("User clicks the submit button")
    public void user_clicks_the_submit_button() {
        WebElement submitButton = driver.findElement(By.id("submitFormButton"));
        submitButton.click();
    }
    @Then("User verifies the success submission message")
    public void user_verifies_the_success_login_message() {
        WebElement successMessage1 = driver.findElement(By.cssSelector("div#successAlert > .alert-heading"));
        Assert.assertTrue(successMessage1.isDisplayed());
        String textSuccessMessage1 = successMessage1.getText();
        Assert.assertEquals(textSuccessMessage1, "Well done!");
        WebElement successMessage2 = driver.findElement(By.cssSelector("div#successAlert > p"));
        Assert.assertTrue(successMessage2.isDisplayed());
        String textSuccessMessage2 = successMessage2.getText();
        Assert.assertEquals(textSuccessMessage2, "You successfully submited in Contact Us Page");
    }
    @Then("User verifies all the validation error messages for the first name, last name, email and password fields")
    public void user_verifies_all_the_validation_error_messages() {
        WebElement firstNameErrorValidationMessage = driver.findElement(By.id("firstNameError"));
        Assert.assertTrue(firstNameErrorValidationMessage.isDisplayed());
        String textFirstNameErrorValidationMessage = firstNameErrorValidationMessage.getText();
        Assert.assertEquals(textFirstNameErrorValidationMessage, "First Name is required.");
        WebElement lastNameErrorValidationMessage = driver.findElement(By.id("lastNameError"));
        Assert.assertTrue(lastNameErrorValidationMessage.isDisplayed());
        String textLastNameErrorValidationMessage = lastNameErrorValidationMessage.getText();
        Assert.assertEquals(textLastNameErrorValidationMessage, "Last Name is required.");
        WebElement emailErrorValidationMessage = driver.findElement(By.id("emailError"));
        Assert.assertTrue(emailErrorValidationMessage.isDisplayed());
        String textEmailErrorValidationMessage = emailErrorValidationMessage.getText();
        Assert.assertEquals(textEmailErrorValidationMessage, "Email is required.");
        WebElement passwordErrorValidationMessage = driver.findElement(By.id("passwordError"));
        Assert.assertTrue(passwordErrorValidationMessage.isDisplayed());
        String textPasswordErrorValidationMessage = passwordErrorValidationMessage.getText();
        Assert.assertEquals(textPasswordErrorValidationMessage, "Password is required.");
    }

}
