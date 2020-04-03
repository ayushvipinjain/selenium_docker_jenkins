package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxDriverManager implements DriverManager {

    private  WebDriver webDriver = null;

    public FirefoxDriverManager(String execType) throws MalformedURLException {
        this.webDriver = setDriver(execType);
    }

    private WebDriver setDriver(String exectype) throws MalformedURLException {
        if(exectype.equals("grid")){
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,"firefox");
            FirefoxOptions capabilities = new FirefoxOptions();
            capabilities.merge(desiredCapabilities);
            webDriver = new RemoteWebDriver(new URL(GRID_URL),capabilities);
        }
        else {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }
        return webDriver;
    }

    public WebDriver getDriver() {
        return webDriver;
    }
}
