package at.browserLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFunctions {

    private static WebDriver driver;
    private String lBrowser;
    private String chromePath = "../../resources/chromedriver";

    public WebDriver getDriver(String browser, String URL) {
        lBrowser = browser;
        setlBrowser(lBrowser);
        driver.manage().window().maximize();
        driver.get(URL);
        return driver;
    }

    private void setlBrowser (String browser) {
        if (browser.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver",chromePath);
            driver = new ChromeDriver();
        }
    }
}
