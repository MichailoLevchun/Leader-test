package PO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    private final Logger LOG = Logger.getLogger(LoginPage.class);

    @FindBy(css = "*[name='login']")
    private WebElement emailField;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    @FindBy(css = "*[type='submit']")
    private WebElement submitButton;

    @FindBy(css = "*[data-t='button:pseudo']")
    private WebElement notNowButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String mail) {
        LOG.info("===> Entering email...");
        emailField.sendKeys(mail);
    }

    public void enterPassword(String password) {
        LOG.info("===> Entering password...");
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton() {
        LOG.info("===> Click on Login button...");
        submitButton.click();
    }

    public WebElement getNotNowButton() {
        return notNowButton;
    }
}
