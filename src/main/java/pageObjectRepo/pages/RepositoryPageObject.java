package pageObjectRepo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RepositoryPageObject extends AccountPageObject {

    @FindBy(xpath = "//a[contains(text(),'Add a README')]")
    private WebElement addReadMeButton;

    @FindBy(xpath = "//span[contains(text(),'Settings')]")
    private WebElement settingsButton;


    public RepositoryPageObject(WebDriver driver) {
        super(driver);
    }

    public RepositoryPageObject(WebDriver driver, String PageURL) {
        super(driver, PageURL);
    }

    public ReadMePageObject   pressAddReadMe() {
        wait.until(ExpectedConditions.elementToBeClickable(addReadMeButton));
        addReadMeButton.click();
        return PageFactory.initElements(driver, ReadMePageObject.class);
    }

    public RepositorySettingPageObject pressSettingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsButton));
        settingsButton.click();
        return PageFactory.initElements(driver, RepositorySettingPageObject.class);
    }


}
