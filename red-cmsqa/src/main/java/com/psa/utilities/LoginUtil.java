package com.psa.utilities;

import static com.psa.data.GenericData.timeout;
import static com.psa.pages.LoginPage.LOGIN_FIELD;
import static com.psa.pages.LoginPage.PASSWORD_FIELD;
import static com.psa.pages.LoginPage.PROJECT_SERVICE;
import static com.psa.pages.LoginPage.STAY_LOGIN_BUTTON;
import static com.psa.pages.LoginPage.SUBMIT_BUTTON;

import com.psa.library.TestInitReference;
import com.psa.data.UserAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginUtil extends TestInitReference {

    /**
     * Use this function to verify all elements in Login Page
     */
    public void verifyLoginPage() {
        String titleLoginPage = "Log in to your account";

        try {
            Assert.assertTrue(isElementVisible(IMG_LOGIN_LOGO));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_LOGIN_TITLE), titleLoginPage));
            Assert.assertTrue(isElementVisible(TXT_EMAIL));
            Assert.assertTrue(isElementVisible(TXT_PASSWORD));
            Assert.assertTrue(isElementVisible(BTN_LOGIN));
            pass("All elements were verified in the Login Page. - verifyLoginPage");
        } catch (AssertionError e) {
            fail("All elements should be verified in the Login Page. - verifyLoginPage");
            throw e;
        }
    }

    /**
     * Use this function to verify login successful
     */
    public void verifyLoginSuccessful() {
        String titleMyCasesPage = "My Cases";

        try {
            Assert.assertTrue(isElementNotVisible(BTN_LOGIN));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_MY_CASES), titleMyCasesPage));
            pass("User logged in successfully. - verifyLoginSuccessful");
        } catch (AssertionError e) {
            fail("User should be logged in successfully. - verifyLoginSuccessful");
            throw e;
        }
    }

    /**
     * Use this function to verify login invalid
     */
    public void verifyLoginInvalid() {

        try {
            Assert.assertTrue(isElementVisible(ALERT_LOGIN));
            pass("User was not able to login. - verifyLoginInvalid");
        } catch (AssertionError e) {
            fail("User should not be able to login. - verifyLoginInvalid");
            throw e;
        }
    }

    /**
     * Use this function to login any user
     * @param email any email
     * @param password any password
     */
    public void loginUser(String email, String password) {
        type(TXT_EMAIL, email);
        type(TXT_PASSWORD, password);
        click(BTN_LOGIN);
    }

    /**
     * Use this function to login any user account
     * @param userAccount account to log in
     */
    public void loginUser(UserAccount userAccount) {
        type(LOGIN_FIELD, userAccount.getEmail());
        click(STAY_LOGIN_BUTTON);
        type(PASSWORD_FIELD, userAccount.getPassword());
        click(SUBMIT_BUTTON);
        click(STAY_LOGIN_BUTTON);
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe[id = 'AppLandingPage']")));
        driver().switchTo().frame(frame);
        click(PROJECT_SERVICE);
    }
}