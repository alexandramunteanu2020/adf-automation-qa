package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageBase;


public class FilePage extends PageBase {

    @FindBy(xpath = "//*[text()='create_new_folder']")
    private WebElement createNewFolderButton;

    @FindBy(id = "adf-folder-name-input")
    private WebElement nameFolderTextBox;

    @FindBy(xpath = "//button/span[contains(text(),'Create')]")
    private WebElement createButton;

    @FindBy(xpath = "//simple-snack-bar/span")
    private WebElement popUpMessage;

    @FindBy(xpath = "//*[@role='dialog']")
    private WebElement dialog;

    @FindBy(id = "adf-folder-cancel-button")
    private WebElement cancelButton;

    @FindBy(id = "action_menu_right_0")
    private WebElement rightMenu;

    @FindBy(xpath = "//*[@id=\"document-list-container\"]/adf-upload-drag-area/div/div/adf-document-list/adf-datatable/div/div[2]")
    WebElement rowTableFiles;

    @FindBy(xpath = "//div[@class='adf-full-width adf-datatable-list ng-star-inserted']/div[@class='adf-datatable-body' and @role='rowgroup']")
    private WebElement foldersTable;

    @FindBy(xpath = "//*[@aria-label='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane']")
    private WebElement deletePopUpMessage;

    public FilePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnNewFolder(String name) {
        waitForElement(createNewFolderButton);
        createNewFolderButton.click();
        clearInputAndSendKeys(nameFolderTextBox, name);
        createButton.click();
        localWait(2000);
    }

    public String getErrorMessage() {
        return popUpMessage.getText();
    }

    public boolean isDialogDisplayed() {
        return dialog.isDisplayed();
    }

    public void clickOnCancelButton() {
        cancelButton.click();
    }

    public void clickOnRightMenuAndDeleteFolder(String folderName) {
        foldersTable.findElement(By.xpath("//span[text()='" + folderName + "']")).findElement(By.xpath("//*[@aria-label='Actions']")).click();
        deleteButton.click();
        localWait(1000);
    }

    public boolean isFolderPresent(String folderName) {
        return rowTableFiles.findElement(By.xpath("//adf-datatable-row[@aria-label='" + folderName + "']")).isDisplayed();
    }

    public boolean isFolderDeleted(String folderName) {
        return findElements(By.xpath("//div[@data-automation-id='" + folderName + "']")).isEmpty();
    }

    public String getDeletedFolderMessage(String folderName) {
        return deletePopUpMessage.findElement(By.xpath("//span[text()='" + folderName + " deleted']")).getText();
    }
}