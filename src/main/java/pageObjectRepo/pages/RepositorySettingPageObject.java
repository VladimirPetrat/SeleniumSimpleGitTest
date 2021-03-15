package pageObjectRepo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RepositorySettingPageObject extends AccountPageObject {

    @FindBy(xpath = "//*[@id=\"options_bucket\"]/div[10]/ul/li[4]/details")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[@id=\"options_bucket\"]/div[10]/ul/li[4]/details/details-dialog/div[3]/form/p/input")
    private WebElement repositoryNameField;

    private String repositoryName;

    public RepositorySettingPageObject(WebDriver driver) {
        super(driver);
    }

    public RepositorySettingPageObject(WebDriver driver, String PageURL) {
        super(driver, PageURL);
    }

    public RepositorySettingPageObject pressDeleteButton(String repositoryName) {
        this.repositoryName = repositoryName;
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(repositoryNameField));
        repositoryNameField.sendKeys(repositoryName);
        return this;
    }


    public AccountHomePage deleteRepository() {
        repositoryNameField.sendKeys(Keys.ENTER);
        return PageFactory.initElements(driver, AccountHomePage.class);
    }
}
