package pageObjectRepo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewRepositoryPageObject extends AccountPageObject {

    @FindBy(id = "repository_name")
    private WebElement repositoryNameField;

    @FindBy(id = "repository_gitignore_template_toggle")
    private WebElement gitIgnoreButton;

    @FindBy(xpath = "//*[@id=\"new_repository\"]/div[4]/button")
    private WebElement createNewRepositoryButton;

    private String repositoryName;

    private String language;

    public NewRepositoryPageObject(WebDriver driver) {
        super(driver);
    }

    public NewRepositoryPageObject(WebDriver driver, String PageURL) {
        super(driver, PageURL);
    }

    public NewRepositoryPageObject setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
        wait.until(ExpectedConditions.elementToBeClickable(repositoryNameField));
        repositoryNameField.clear();
        repositoryNameField.sendKeys(repositoryName);
        return this;
    }

    public NewRepositoryPageObject setProgramingLanguageToGitIgnoreTempale(String language) {
        this.language = language;
        wait.until(ExpectedConditions.elementToBeClickable(gitIgnoreButton));
        gitIgnoreButton.click();
        WebElement gitIgnoreDropDownList = driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/div[4]/div[2]/span[2]/details/summary"));
        wait.until(ExpectedConditions.elementToBeClickable(gitIgnoreDropDownList));
        gitIgnoreDropDownList.click();
        WebElement gitIgnoreContentFilter = driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]", language)));
        wait.until(ExpectedConditions.elementToBeClickable(gitIgnoreContentFilter));
        gitIgnoreContentFilter.click();
        return this;
    }

    public RepositoryPageObject  createRepository() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewRepositoryButton));
        createNewRepositoryButton.click();
        return PageFactory.initElements(driver, RepositoryPageObject.class);
    }




}
