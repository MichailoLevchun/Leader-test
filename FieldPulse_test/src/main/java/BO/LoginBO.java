package BO;

import driver.DriverManager;
import PO.LoginPage;
import parser.Model.User;

import org.openqa.selenium.WebDriver;


public class LoginBO {

    private LoginPage loginPage;

    public static final String LOGIN_URL = "https://webapp.fieldpulse.com/auth/login";

    public LoginBO() {
        WebDriver driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        driver.get(LOGIN_URL);
    }

    public void logIn(User user) {
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickOnLoginButton();
    }

    public boolean verifyUserLogin() {
        return loginPage.getMyProfileIcon().isDisplayed();
    }
}
