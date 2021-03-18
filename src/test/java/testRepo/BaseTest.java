package testRepo;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjectRepo.pages.AccountHomePage;
import pageObjectRepo.pages.HomePageObject;
import serviceRepo.DriversConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static String browserName;
    protected final static String DEFAULT_SYSTEM_PROPERTIES_FILE_PATH = "system.properties";
    protected final static String DEFAULT_ACCOUNT_PROPERTIES_FILE_PATH = "account.properties";

    @BeforeClass
    public static void setup() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        loadSystemProperties();
        DriversConfig driversConfig = DriversConfig.initDriver(browserName);
        System.setProperty(driversConfig.getDriverKey(), driversConfig.getPath());
        driver = DriversConfig.create(browserName);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
//        driver.quit();
    }



    private static void loadSystemProperties() {
        try(InputStream inputStream = new FileInputStream(DEFAULT_SYSTEM_PROPERTIES_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            browserName = properties.getProperty("browserName");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
