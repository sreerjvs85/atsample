package at.browserLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class BrowserFunctions {

    public static WebDriver driver;
    private static String lBrowser;
    private static String osName = System.getProperty("os.name");
    private static String chromePath = "chromedriver";
    private static String firefoxPath = "geckodriver";

    public static WebDriver getDriver(String browser, String URL) {
        lBrowser = browser;
        setlBrowser(lBrowser);
        driver.manage().window().maximize();
        driver.get(URL);
        return driver;
    }

    private static void setlBrowser (String browser) {
        lBrowser = browser;
        if (lBrowser.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPath()+chromePath);
            driver = new ChromeDriver();
        } else if (lBrowser.contains("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverPath()+firefoxPath);
            driver = new FirefoxDriver();
        }
    }

    private static String driverPath() {
        String driverPath = "src/test/resources/";
        if (osName.toLowerCase().contains("mac")) {
            driverPath = driverPath+"mac/";
        } else {
            driverPath = driverPath+"windows/";
        }
        return driverPath;
    }

    public static void quitDriver() throws IOException {
        driver.quit();
    }
}
