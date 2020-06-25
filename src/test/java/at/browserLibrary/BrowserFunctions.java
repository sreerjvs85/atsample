package at.browserLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFunctions {

    private static WebDriver driver;
    private static String lBrowser;
    private static String chromePath = "src/test/resources/chromedriver";

    public static WebDriver getDriver(String browser, String URL) {
        lBrowser = browser;
        setlBrowser(lBrowser);
        driver.manage().window().maximize();
        driver.get(URL);
        return driver;
    }

    private static void setlBrowser (String browser) {
        if (browser.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver",chromePath);
            driver = new ChromeDriver();
        }
    }
}
