package utils;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.FilePage;

import java.util.ArrayList;
import java.util.Optional;

public class TestBase {

    protected WebDriver driver;
    protected ReadProperties properties;

    @BeforeSuite
    public void beforeSuiteImplementation() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        DesiredCapabilities cap = Utility.getCapabilities();
        properties = new ReadProperties();
        this.driver = new ChromeDriver(cap);
        this.driver.manage().deleteAllCookies();
        goToLoginPage();
        maximizeBrowserWindow();
    }

    @BeforeMethod
    public void beforeMethodImplementation() {

    }

    @AfterMethod
    public void afterMethodImplementation() {
        goToLoginPage();
    }

    @AfterSuite()
    public void SuiteTearDown() {
        closeBrowser();
        quitWebDriver();
    }

    protected void maximizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    protected void closeBrowser() {
        try {
            driver.close();
        } catch (UnreachableBrowserException | NoSuchWindowException e) {
            System.out.println("Cannot close WebDriver. Browser is unreachable");
        }
    }

    protected void quitWebDriver() {
        try {
            driver.quit();
        } catch (UnreachableBrowserException | NoSuchWindowException e) {
            System.out.println("Cannot quit WebDriver. Browser is unreachable");
        }
    }

    public void goToLoginPage() {
        driver.get(properties.getUrl());
    }

    public FilePage goToFilePage() {
        driver.get(properties.getUrlFiles());
        return new FilePage(driver);
    }

    public void switchLastTabOrEnterOptional(Optional<Integer> tab) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        if (tab.isPresent()) {
            driver.switchTo().window(tabs.get(tab.get()));
        } else {
            driver.switchTo().window(tabs.get(tabs.size() - 1));
        }
    }

    public void closeFirstTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0)).close();
    }
}
