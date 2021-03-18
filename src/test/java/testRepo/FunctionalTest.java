package testRepo;

import org.junit.BeforeClass;
import org.junit.Test;
import pageObjectRepo.pages.HomePageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FunctionalTest extends BaseTest {

    private static String login;
    private static String password;
    private static String repositoryName;
    private static String gitIgnoreLanguageTemplate;
    private static String readMeText;
    private static String commitMessage;
    private static String homePageUrl;

    @Test
    public void signInSignOutTest() {
        new HomePageObject(driver, homePageUrl).clickOnSignInButton()
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

    @BeforeClass
    public static void loadAccountProperties() {
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
