package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverManager implements DriverManager{

    private  WebDriver webDriver = null;

    public ChromeDriverManager(String execType) throws MalformedURLException {
        this.webDriver = setDriver(execType);
    }

    private WebDriver setDriver(String exectype) throws MalformedURLException {

        if(exectype.equals("grid")){
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,"chrome");
            ChromeOptions capabilities = new ChromeOptions();
            capabilities.merge(desiredCapabilities);
            webDriver = new RemoteWebDriver(new URL(GRID_URL),capabilities);
        }
        else{
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }
        return webDriver;
    }

    public WebDriver getDriver() {
        return webDriver;
    }
}
