package pageObjectRepo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReadMePageObject extends AccountPageObject {

    @FindBy(id = "submit-file")
    private WebElement submitFileButton;

    @FindBy(xpath = "//pre[@class=' CodeMirror-line ']")
    private WebElement readMeTextField;

    @FindBy(id = "commit-summary-input")
    private WebElement commitMessageField;

    private String readMeText;

    private String commitMessageText;

    public ReadMePageObject(WebDriver driver) {
        super(driver);
    }

    public ReadMePageObject(WebDriver driver, String PageURL) {
        super(driver, PageURL);
    }

    public ReadMePageObject addReadMeText(String text) {
        this.readMeText = text;
        wait.until(ExpectedConditions.elementToBeClickable(readMeTextField));
        readMeTextField.click();
        readMeTextField.click();
        readMeTextField.sendKeys(text);
        return this;
    }

    public ReadMePageObject addCommitMessage(String commitMessageText) {
        this.commitMessageText = commitMessageText;
        wait.until(ExpectedConditions.elementToBeClickable(commitMessageField));
        commitMessageField.sendKeys(commitMessageText);
        return this;
    }

    public RepositoryPageObject  createNewCommit() {
        submitFileButton.click();
        return PageFactory.initElements(driver, RepositoryPageObject.class);

    }
}
