package testRepo;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjectRepo.pages.AccountHomePage;
import pageObjectRepo.pages.HomePageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static String pathToChromeDriver;
    private static String webDriverKey;
    private final static String DEFAULT_SYSTEM_PROPERTIES_FILE_PATH = "system.properties";
    private final static String DEFAULT_ACCOUNT_PROPERTIES_FILE_PATH = "account.properties";
    private static String login;
    private static String password;
    private static String repositoryName;
    private static String gitIgnoreLanguageTemplate;
    private static String readMeText;
    private static String commitMessage;
    private static String homePageUrl;




    @BeforeClass
    public static void setup() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        loadSystemProperties();
        System.setProperty(webDriverKey, pathToChromeDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loadAccountProperties();
    }

    @AfterClass
    public static void tearDown() {
//        driver.quit();
    }

    @Test
    public void signInSignOutTest() {
        HomePageObject homePageObject = new HomePageObject(driver, homePageUrl);
        homePageObject.clickOnSignInButton()
                .setLogin(login)
                .setPassword(password)
                .applySingInForms()
                .pressNewButton()
                .setRepositoryName(repositoryName)
                .setProgramingLanguageToGitIgnoreTempale(gitIgnoreLanguageTemplate)
                .createRepository()
                .pressAddReadMe()
                .addReadMeText(readMeText)
                .addCommitMessage(commitMessage)
                .createNewCommit()
                .pressSettingButton()
                .pressDeleteButton(String.format("%s/%s",login,repositoryName))
                .deleteRepository()
                .signOut();
    }

    private static void loadSystemProperties() {
        try(InputStream inputStream = new FileInputStream(DEFAULT_SYSTEM_PROPERTIES_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            pathToChromeDriver = properties.getProperty("pathToChromeWebDriver");
            webDriverKey = properties.getProperty("webDriverKey");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private static void loadAccountProperties() {
        try(InputStream inputStream = new FileInputStream(DEFAULT_ACCOUNT_PROPERTIES_FILE_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            repositoryName = properties.getProperty("repositoryName");
            gitIgnoreLanguageTemplate = properties.getProperty("gitIgnoreLanguageTemplate");
            readMeText = properties.getProperty("readMeText");
            commitMessage = properties.getProperty("commitMessage");
            homePageUrl=properties.getProperty("homePageUrl");

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
