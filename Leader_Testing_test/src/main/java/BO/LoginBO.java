package BO;

import org.openqa.selenium.WebDriver;

import PO.LoginPage;

public class LoginBO {

    private LoginPage loginPage;

    public LoginBO(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public void logIn(String name, String password) {
        loginPage.enterEmail(name);
        loginPage.clickOnLoginButton();
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        loginPage.getNotNowButton().click();
    }
}
