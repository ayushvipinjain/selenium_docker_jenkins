package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserMethods extends BaseTest {


    @Test
    public void demoBrowserMethods() throws MalformedURLException {
        driver.navigate().to(new URL("https://www.google.com"));

    }

}
