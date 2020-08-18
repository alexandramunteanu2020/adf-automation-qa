package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageBase;

public class LoginPage extends PageBase {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    private static final int PAGE_LOAD_WAITING_TIME = 2000;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillInCredentials(String usernameInput, String passwordInput) {
        clearInputAndSendKeys(username, usernameInput);
        clearInputAndSendKeys(password, passwordInput);
        return this;
    }

    public HomePage userLogin(String username, String password) {
        localWait(2000);
        fillInCredentials(username, password);
        localWait(2000);
        loginButton.click();
        localWait(PAGE_LOAD_WAITING_TIME);
        return new HomePage(driver);
    }
}