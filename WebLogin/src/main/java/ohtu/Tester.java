package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/pqkallio/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
        
        clickLink(driver, element, "logout");
        sleep(1);
        
        clickLink(driver, element, "login");
        sleep(1);
        
        login(driver, element, "pekka", "ekkapp");
        sleep(1);
        
        login(driver, element, "einari", "irnajee");
        sleep(1);
        
        clickLink(driver, element, "back to home");
        sleep(1);
        
        clickLink(driver, element, "register new user");
        sleep(1);
        
        createNewUser(driver, element, "einari", "eKop09.Ju");
        sleep(1);
        
        clickLink(driver, element, "continue to application mainpage");
        sleep(1);
        
        clickLink(driver, element, "logout");
        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static void login(WebDriver driver, WebElement element, String username, String password) {
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        sleep(1);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
    }

    private static void clickLink(WebDriver driver, WebElement element, String linkText) {
        element = driver.findElement(By.linkText(linkText));
        element.click();
    }

    private static void createNewUser(WebDriver driver, WebElement element, String username, String password) {
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        sleep(1);
        element.submit();
    }
}
