package base;

import managers.ChromeDriverManager;
import managers.DriverManager;
import managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import utils.Properties;

import java.net.MalformedURLException;

public class DriverFactory {

    public static WebDriver getDriver(String browser) throws MalformedURLException {

        DriverManager driverManager = null;
        WebDriver webDriver = null;
        String execType = IsNullOrEmpty(Properties.execType) != true ? Properties.execType : "local";
        switch (browser) {
            case "chrome":
                driverManager = new ChromeDriverManager(execType);
                webDriver = driverManager.getDriver();
                break;
            case "firefox":
                driverManager = new FirefoxDriverManager(execType);
                webDriver = driverManager.getDriver();
                break;
            default:
                driverManager = new ChromeDriverManager(execType);
                webDriver = driverManager.getDriver();
                break;
        }
        return webDriver;
    }

    public static boolean IsNullOrEmpty(String input) {
        if (input.isEmpty() || input == null)
            return true;
        else
            return false;
    }

}
