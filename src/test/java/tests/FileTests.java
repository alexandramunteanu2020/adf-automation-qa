package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FilePage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestBase;

import static utils.enums.LoginCredentials.VALID_PASSWORD;
import static utils.enums.LoginCredentials.VALID_USERNAME;

public class FileTests extends TestBase {

    private LoginPage loginPage;
    private HomePage homePage;
    private FilePage filePage;

    private static final int LENGTH = 5;
    private static final boolean USE_LETTERS = true;
    private static final boolean USE_NUMBERS = false;
    private static final String GENERATED_NAME = RandomStringUtils.random(LENGTH, USE_LETTERS, USE_NUMBERS);

    private final String ERROR_MESSAGE = "There's already a folder with this name. Try a different name.";
    private final String DELETE_FOLDER_MESSAGE = GENERATED_NAME + " deleted";

    @BeforeClass
    public void openLoginPage() {
        loginPage = new LoginPage(driver);
    }

    @Test()
    public void validateCreateAndDeleteFolderFunctionalities() {
        homePage = loginPage.userLogin(VALID_USERNAME.toString(), VALID_PASSWORD.toString());
        filePage = goToFilePage();
        filePage.clickOnNewFolder(GENERATED_NAME);
        Assert.assertTrue(filePage.isFolderPresent(GENERATED_NAME));
        filePage.clickOnNewFolder(GENERATED_NAME);
        Assert.assertEquals(filePage.getErrorMessage(), ERROR_MESSAGE);
        Assert.assertTrue(filePage.isDialogDisplayed());
        filePage.clickOnCancelButton();
        filePage.clickOnRightMenuAndDeleteFolder(GENERATED_NAME);
        Assert.assertEquals(filePage.getDeletedFolderMessage(GENERATED_NAME), DELETE_FOLDER_MESSAGE);
        Assert.assertTrue(filePage.isFolderDeleted(GENERATED_NAME));
    }
}