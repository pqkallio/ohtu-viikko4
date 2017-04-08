package ohtu;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver;
    String baseUrl = "http://localhost:4567";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/pqkallio/chromedriver/chromedriver");
        this.driver = new ChromeDriver();
    }
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();
    } 

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^a nonexistent username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void a_nonexistent_username_and_password_are_given(String usernam, String password) throws Throwable {
        logInWith(usernam, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
        @Given("^new user is selected$")
    public void new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
    }

    @When("^a valid username \"([^\"]*)\" and a valid password \"([^\"]*)\" and a correct password confirmation \"([^\"]*)\" is given$")
    public void a_valid_username_and_a_valid_password_and_a_correct_password_confirmation_is_given(String username, String password, String passwordConfirmation) throws Throwable {
        createNewUserWith(username, password, passwordConfirmation);
    }

    @Then("^a new user account is created$")
    public void a_new_user_account_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("^a too short username \"([^\"]*)\" and a valid password \"([^\"]*)\" and a correct password confirmation \"([^\"]*)\" is given$")
    public void a_too_short_username_and_a_valid_password_and_a_correct_password_confirmation_is_given(String username, String password, String passwordConfirmation) throws Throwable {
        createNewUserWith(username, password, passwordConfirmation);
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String errorMessage) throws Throwable {
        pageHasContent(errorMessage);
    }

    @When("^a valid username \"([^\"]*)\" and a too short password \"([^\"]*)\" and a correct password confirmation \"([^\"]*)\" is given$")
    public void a_valid_username_and_a_too_short_password_and_a_correct_password_confirmation_is_given(String username, String password, String passwordConfirmation) throws Throwable {
        createNewUserWith(username, password, passwordConfirmation);
    }

    @When("^a valid username \"([^\"]*)\" and a password containing only letters \"([^\"]*)\" and a correct password confirmation \"([^\"]*)\" is given$")
    public void a_valid_username_and_a_password_containing_only_letters_and_a_correct_password_confirmation_is_given(String username, String password, String passwordConfirmation) throws Throwable {
        createNewUserWith(username, password, passwordConfirmation);
    }

    @When("^a already taken username \"([^\"]*)\" and a valid password \"([^\"]*)\" and a correct password confirmation \"([^\"]*)\" is given$")
    public void a_already_taken_username_and_a_valid_password_and_a_correct_password_confirmation_is_given(String username, String password, String passwordConfirmation) throws Throwable {
        createNewUserWith(username, password, passwordConfirmation);
    }

    @When("^a valid username \"([^\"]*)\" and a valid password \"([^\"]*)\" and an incorrect password confirmation \"([^\"]*)\" is given$")
    public void a_valid_username_and_a_valid_password_and_an_incorrect_password_confirmation_is_given(String username, String password, String passwordConfirmation) throws Throwable {
        createNewUserWith(username, password, passwordConfirmation);
    }

    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 

    private void createNewUserWith(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    }
}