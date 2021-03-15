package pageObjectRepo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPageobject extends PageObject {
    @FindBy(id = "login_field")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement signInButton;

    private String login;

    private String password;


    public SignInPageobject(WebDriver driver) {
        super(driver);
    }

    public SignInPageobject(WebDriver driver, String PageURL) {
        super(driver, PageURL);
    }

    public SignInPageobject setLogin(String login) {
        this.login = login;
        wait.until(ExpectedConditions.elementToBeClickable(loginField));
        loginField.clear();
        loginField.sendKeys(login);
        return this;
    }

    public SignInPageobject setPassword(String password) {
        this.password = password;
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }



    public AccountHomePage applySingInForms() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
        return PageFactory.initElements(driver, AccountHomePage.class);
    }

}


