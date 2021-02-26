import BO.LoginBO;

import org.testng.Assert;
import org.testng.annotations.*;


public class TestSuite extends BaseTest {

    @Test
    public void testLogin() {
        LoginBO loginBO = new LoginBO();
        loginBO.logIn(user);
        Assert.assertTrue(loginBO.verifyUserLogin());
    }
}
