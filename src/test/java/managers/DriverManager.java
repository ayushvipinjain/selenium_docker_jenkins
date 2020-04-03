package managers;

import org.openqa.selenium.WebDriver;

public interface DriverManager {

    /*
    GRID_URL -
    [Local Selenium Grid - When Running Local Selenium Grid - Use the Hub Url which you get while registering a Hub

    [Grid Using Docker-Compose]  - The Grid Url much be set to the machine on which your grid infra is setup using docker compose
    Example : If the setup is done on local then it should be set to localhost i.e. 127.0.0.1

    [Execute tests inside Container using Dockerfile] - The Grid url should be set with ip address of the machine on which your infra is set up
     Example : If you are setting up infra using docker compose on your local machine then ip address of your machine should be set to Grid Url
     */

    //public String GRID_URL ="http://192.168.43.16:4444/wd/hub";
    //public String GRID_URL ="http://127.0.0.1:4446/wd/hub";
     String GRID_URL = System.getenv("HUB_HOST").isEmpty() || System.getenv("HUB_HOST")==null ?
            "http://127.0.0.1:4446/wd/hub" : System.getenv("HUB_HOST") ;

     WebDriver getDriver();
}