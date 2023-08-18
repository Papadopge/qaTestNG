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

public class ToDoListStepDefinitions {
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("User navigates through To Do List Page")
    public void user_navigates_through_to_do_list_page() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 1500);");
        WebElement toDoListButton = driver.findElement(By.id("todolistbutton"));
        Assert.assertTrue(toDoListButton.isDisplayed());
        toDoListButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("User goes through to do list page should verify the current URL")
    public void user_goes_through_to_do_list_page_should_verify_the_current_url() {
        String verifyUrl = driver.getCurrentUrl();
        String expectedUrl = "http://georgiapapadopoulou.eu.185-25-20-64.linux11.name-servers.gr/todo_list.html";
        Assert.assertTrue(verifyUrl.contains(expectedUrl));
    }

    @Then("User evaluates the existance of to do list web elements")
    public void user_evaluates_the_existance_of_to_do_list_web_elements() {
        WebElement title = driver.findElement(By.cssSelector("h2"));
        Assert.assertTrue(title.isDisplayed());
        String textTitle = title.getText();
        Assert.assertEquals(textTitle, "My To-Do List");
        WebElement title2 = driver.findElement(By.cssSelector("h3"));
        Assert.assertTrue(title.isDisplayed());
        String textTitle2 = title2.getText();
        Assert.assertEquals(textTitle2, "Completed Tasks");
        WebElement inputNewTask = driver.findElement(By.id("newTask"));
        Assert.assertTrue(inputNewTask.isDisplayed());
        WebElement addTaskButton = driver.findElement(By.id("addTask"));
        Assert.assertTrue(addTaskButton.isDisplayed());
        String textAddTaskButton = addTaskButton.getText();
        Assert.assertEquals(textAddTaskButton, "Add Task");
        WebElement taskList = driver.findElement(By.cssSelector(".container > .task-list"));
        WebElement completedTaskList = driver.findElement(By.cssSelector(".container > .completed-tasks"));
        Assert.assertTrue(taskList.isDisplayed());
        Assert.assertTrue(completedTaskList.isDisplayed());
    }
    @When("User enters a new task")
    public void user_enters_a_new_task() {
        WebElement inputNewTask = driver.findElement(By.id("newTask"));
        inputNewTask.sendKeys("new test");
    }

    @When("User clicks on Add Task button")
    public  void user_clicks_on_add_task_button() {
        WebElement addTaskButton = driver.findElement(By.id("addTask"));
        Assert.assertTrue(addTaskButton.isDisplayed());
        addTaskButton.click();
    }
    @Then("User verifies that the task has been created successfully")
    public void user_verifies_that_the_task_has_been_created_successfully() {
        WebElement addedTask = driver.findElement(By.cssSelector("ul#tasks > li"));
        Assert.assertTrue(addedTask.isDisplayed());
        String textAddedTask = addedTask.getText();
        Assert.assertEquals(textAddedTask, "new test");
    }
    @When("User clicks on Complete button")
    public  void user_clicks_on_complete_button() {
        WebElement completeButton = driver.findElement(By.cssSelector("ul#tasks  button"));
        Assert.assertTrue(completeButton.isDisplayed());
        String textCompleteButton = completeButton.getText();
        Assert.assertEquals(textCompleteButton, "Complete");
        completeButton.click();
    }
    @Then("User verifies that the task exists in completed tasks list")
    public void user_verifies_that_the_task_exists_in_tasks_list() {
        WebElement completedTask = driver.findElement(By.cssSelector("ul#completedTasks > li"));
        Assert.assertTrue(completedTask.isDisplayed());
        String textCompletedTask = completedTask.getText();
        Assert.assertEquals(textCompletedTask, "new test");
    }
    @When("User clicks on Remove button")
    public void user_clicks_on_remove_button() {
        WebElement removeButton = driver.findElement(By.cssSelector("ul#completedTasks  button"));
        Assert.assertTrue(removeButton.isDisplayed());
        String textRemoveButton = removeButton.getText();
        Assert.assertEquals(textRemoveButton, "Remove");
        removeButton.click();
    }
    @Then("User verifies that the task does not exist in completed tasks list")
    public void user_verifies_that_the_task_does_not_exist_in_completed_tasks_list() {
        WebElement completedTask = driver.findElement(By.cssSelector("ul#completedTasks > li"));
        Assert.assertNull(completedTask, "Element should not exist");
    }
}
