package pageObjectRepo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePageObject extends PageObject {
    @FindBy(linkText = "Sign in")
    private WebElement signInButton;

    public HomePageObject(WebDriver driver, String homePageUrl) {
        super(driver);
        driver.get(homePageUrl);
    }

    public SignInPageobject clickOnSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
        return PageFactory.initElements(driver, SignInPageobject.class);
    }

}





