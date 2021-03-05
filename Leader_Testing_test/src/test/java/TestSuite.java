import static io.restassured.RestAssured.given;
import static java.lang.Integer.parseInt;
import static org.openqa.selenium.Keys.ENTER;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import BO.LoginBO;
import driver.DriverManager;


public class TestSuite {

    @Test
    public void test1() {
        LoginBO loginBO = new LoginBO(DriverManager.getDriver());
        DriverManager.getDriver().get("https://passport.yandex.by/");
        loginBO.logIn("w4ll.j0hn@yandex.by", "qwerty12345");

    }

    @Test
    public void test2() {
        LoginBO loginBO = new LoginBO(DriverManager.getDriver());
        DriverManager.getDriver().get("https://www.google.com/");
        DriverManager.getDriver().findElement(By.cssSelector("input[maxlength=\"2048\"]")).sendKeys("купить кофемашину bork c804" + ENTER);
        Assert.assertTrue(parseInt(DriverManager.getDriver().findElement(By.id("result-stats")).getText().replaceAll("\\D+",""))> 10);

    }

    @Test
    public void test3() {
       given().when().get("https://reqres.in/api/single_user").then().log().all().assertThat().statusCode(200);
       Assert.assertEquals(given().when().get("https://reqres.in/api/single_user").getBody().jsonPath().get("data[0].name"), "cerulean");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.exit();
    }
}
