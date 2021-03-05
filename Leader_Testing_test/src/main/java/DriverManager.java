import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverManager {

    private DriverManager() {
    }

    private static final ThreadLocal<WebDriver> pool = new ThreadLocal<WebDriver>();
    private static final DriverManager driverManager = new DriverManager();


    public static WebDriver getDriver() {
        return pool.get() != null ? pool.get() : createAndGetDriver();
    }

    private static WebDriver createAndGetDriver() {
        WebDriver driver = driverManager.getDriverInstance();
        pool.set(driver);
        return driver;
    }

    public WebDriver getDriverInstance() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void exit() {
        WebDriver driver = getDriver();
        pool.remove();
        if (driver != null) {
            driver.quit();
        }
    }
}
