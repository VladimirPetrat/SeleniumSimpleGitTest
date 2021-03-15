package pageObjectRepo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountHomePage extends AccountPageObject {
    @FindBy(xpath = "//*[@id=\"repos-container\"]/h2/a")
    private WebElement newButton;

    public AccountHomePage(WebDriver driver) {
        super(driver);
    }

    public AccountHomePage(WebDriver driver, String PageURL) {
        super(driver, PageURL);
    }

    public NewRepositoryPageObject pressNewButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newButton));
        newButton.click();
        return PageFactory.initElements(driver, NewRepositoryPageObject.class);
    }


}
