package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBootClass {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String login;
    private static String password;
    private static String pathToChromeDriver;

    @BeforeClass
    public static void setup() throws IOException {
        loadProperties();
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void runTest() {
        userLogin();
        pressButtonNewRepository();
        addReadMeCommit();
        deleteRepository();
        logOut();
    }

    @AfterClass
    public static void tearDown() {
        /*driver.quit();*/
    }

    private static void userLogin() {
        driver.get("https://github.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("login_field")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();
    }

    private static void pressButtonNewRepository() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("New")));
        driver.findElement(By.linkText("New")).click();
        driver.findElement(By.id("repository_name")).sendKeys("TestRepository");
        driver.findElement(By.id("repository_gitignore_template_toggle")).click();
        driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/div[4]/div[2]/span[2]/details/summary")).click();
        driver.findElement(By.id("context-ignore-filter-field")).sendKeys("Java");
        driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/div[4]/div[2]/span[2]/details/details-menu/div[3]/div[1]/label[52]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button")).click();
    }

    private static void addReadMeCommit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div[2]/div[1]/div[4]/a")));
        driver.findElement(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div[2]/div[1]/div[4]/a")).click();
        WebElement readMeForm = driver.findElement(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/form[2]/div/file-attachment/div/div[2]/div/div/div[5]/div[1]/div/div/div/div[5]/div/pre/span"));
        readMeForm.click();
        readMeForm.click();
        readMeForm.sendKeys("My Test Repository");
        driver.findElement(By.xpath("//*[@id=\"commit-description-textarea\"]")).sendKeys("Add readme file");
        driver.findElement(By.xpath("//*[@id=\"submit-file\"]")).click();
    }

    private static void deleteRepository() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"js-repo-pjax-container\"]/div[2]/nav/ul/li[9]/a")));
        driver.findElement(By.xpath("//*[@id=\"js-repo-pjax-container\"]/div[2]/nav/ul/li[9]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"options_bucket\"]/div[10]/ul/li[4]/details/summary")).click();
        driver.findElement(By.xpath("//*[@id=\"options_bucket\"]/div[10]/ul/li[4]/details/details-dialog/div[3]/form/p/input")).sendKeys("VladimirPetrat/TestRepository");
        driver.findElement(By.xpath("//*[@id=\"options_bucket\"]/div[10]/ul/li[4]/details/details-dialog/div[3]/form/button")).click();

    }

    private static void logOut() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/header/div[7]/details/summary/span[2]")));
        driver.findElement(By.xpath("/html/body/div[1]/header/div[7]/details/summary/span[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/header/div[7]/details/details-menu/form/button")).click();
    }

    private static void loadProperties() throws IOException {
        try(InputStream inputStream = new FileInputStream("conf.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            pathToChromeDriver = properties.getProperty("pathToChromeWebDriver");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
