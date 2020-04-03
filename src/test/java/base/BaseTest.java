package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class BaseTest {

    public WebDriver driver = null;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browser ) throws InterruptedException, MalformedURLException {
        driver = DriverFactory.getDriver(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanSetup(){
        try {
            driver.close();
            driver.quit();
        }
        catch (Exception ex){
        }
    }
}
