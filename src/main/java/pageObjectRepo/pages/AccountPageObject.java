package pageObjectRepo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public  abstract class AccountPageObject extends PageObject {

    @FindBy(xpath = "/html/body/div[1]/header/div[7]/details/summary")
    private WebElement viewProfileButton;
    public AccountPageObject(WebDriver driver) {
        super(driver);
    }

    public AccountPageObject(WebDriver driver, String PageURL) {
        super(driver, PageURL);
    }

    public HomePageObject signOut() {
        wait.until(ExpectedConditions.visibilityOf(viewProfileButton));
        viewProfileButton.click();
        WebElement signOutButton = driver
                .findElement(By.xpath("/html/body/div[1]/header/div[7]/details/details-menu/form/button"));
        wait.until(ExpectedConditions.visibilityOf(signOutButton));
        signOutButton.click();
        return PageFactory.initElements(driver, HomePageObject.class);
    }
}
