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
import java.util.concurrent.TimeUnit; // Import the TimeUnit class

public class FileUploadStepDefinitions {
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

    @Given("User navigates through File Upload Page")
    public void user_navigates_through_file_upload_page() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 1000);");
        WebElement fileUploadButton = driver.findElement(By.id("fileuploadbutton"));
        Assert.assertTrue(fileUploadButton.isDisplayed());
        fileUploadButton.click();
    }

    @When("User goes through file upload page should verify the current URL")
    public void user_goes_through_file_upload_page_should_verify_the_current_url() {
        String verifyUrl = driver.getCurrentUrl();
        String expectedUrl = "http://georgiapapadopoulou.eu.185-25-20-64.linux11.name-servers.gr/file_upload.html";
        Assert.assertTrue(verifyUrl.contains(expectedUrl));
    }

    @Then("User evaluates the existance of file upload web elements")
    public void user_evaluates_the_existance_of_file_upload_web_elements() {
        WebElement title = driver.findElement(By.cssSelector("h2"));
        Assert.assertTrue(title.isDisplayed());
        String textTitle = title.getText();
        Assert.assertEquals(textTitle, "File Upload");
        WebElement chooseFileButton = driver.findElement(By.id("fileInput"));
        WebElement uploadButton = driver.findElement(By.id("uploadButton"));
        Assert.assertTrue(chooseFileButton.isDisplayed());
        Assert.assertTrue(uploadButton.isDisplayed());
    }
    @When("User clicks the Choose File Button")
    public void user_clicks_the_choose_file_button() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement chooseFileButton = driver.findElement(By.id("fileInput"));
        chooseFileButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @And("User selects a file from the file explorer")
    public  void user_selects_a_file_from_the_file_explorer() {
        // Attach the file
        WebElement inputFile = driver.findElement(By.cssSelector("input[type=file]"));
        inputFile.sendKeys("/src/test/resources/test.json");
    }
    @When("User clicks the upload button")
    public void user_clicks_the_upload_button() {
        // Click on the upload button
        WebElement uploadButton = driver.findElement(By.id("uploadButton"));
        uploadButton.click();
    }
    @Then("User verifies the success uploading message")
    public  void user_verifies_the_success_uploading_message() {
        // Wait for the upload simulation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Verify the status message during upload
        WebElement uploadStatus = driver.findElement(By.id("uploadStatus"));
        Assert.assertTrue(uploadStatus.isDisplayed());
        Assert.assertTrue(uploadStatus.getText().contains("Uploading file..."));
        // Wait for the upload to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Verify the success status message
        Assert.assertTrue(uploadStatus.isDisplayed());
        Assert.assertTrue(uploadStatus.getText().contains("File uploaded successfully!"));
    }
    @Then("User verifies that no uploading message exists")
    public void user_verifies_that_no_uploading_message_exists() {
        WebElement uploadButton = driver.findElement(By.id("uploadButton"));
        uploadButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement uploadStatus = driver.findElement(By.id("uploadStatus"));
        Assert.assertNull(uploadStatus, "Element should not exist");
    }
}
