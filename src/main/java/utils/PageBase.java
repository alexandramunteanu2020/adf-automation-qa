package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageBase {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    private ReadProperties properties;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
        actions = new Actions(driver);
        properties = new ReadProperties();
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitUntilElementIsClickable(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement waitUntilElementIsVisible(By byType) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byType));
    }

    public List<WebElement> waitUntilElementsAreDisplayed(By byType) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byType));
    }

    protected List<WebElement> waitAndFindElementsFromRoot(By byLocator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return driver.findElements(byLocator);
    }

    protected boolean checkIfElementIsDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean checkIfElementIsSelected(WebElement webElement) {
        try {
            return webElement.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> findElements(By byType) {
        return driver.findElements(byType);
    }

    public void selectOptionFromDropdownList(WebElement dropdownName, String option) {
        Select dropdown = new Select(dropdownName);
        dropdown.selectByVisibleText(option);
    }

    public void moveToElement(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

    public String retrieveURL() {
        return properties.getUrl();
    }

    public void localWait(int waitingTime) {
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void goToPreviousPage() {
        driver.navigate().back();
    }

    protected void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean checkIfElementIsEnabled(WebElement webElement) {
        try {
            return webElement.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    protected void clearInputAndSendKeys(WebElement element, String textToSend) {
        element.click();
        element.clear();
        if (!element.getAttribute("value").isEmpty()) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        }
        element.sendKeys(textToSend);
    }
}
