import static parser.UserParser.parseUser;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import driver.DriverManager;
import parser.Model.User;


public class BaseTest {
    protected User user;

    @BeforeTest
    public void setUp() {
        user = parseUser();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.exit();
    }
}
