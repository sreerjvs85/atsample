package at.browserLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFunctions {

    public static WebDriver driver;
    private static String lBrowser;

    public static WebDriver getDriver(String browser, String URL) {
        lBrowser = browser;
        setlBrowser(lBrowser);
        driver.manage().window().maximize();
        driver.get(URL);
        return driver;
    }

    private static void setlBrowser (String browser) {
        lBrowser = browser;
        driverPath(lBrowser);
        if (lBrowser.toLowerCase().contains("chrome")) {
            driver = new ChromeDriver();
        } else if (lBrowser.toLowerCase().contains("firefox")) {
            driver = new FirefoxDriver();
        }
    }

    private static void driverPath(String browser) {
        String driverPath = "src/test/resources/";
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("mac") && browser.toLowerCase().contains("chrome")) {
            driverPath = driverPath+"mac/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else if (osName.toLowerCase().contains("windows") && browser.toLowerCase().contains("chrome")){
            driverPath = driverPath+"windows/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);
        }  else if (osName.toLowerCase().contains("mac") && browser.toLowerCase().contains("firefox")){
            driverPath = driverPath+"mac/geckodriver";
            System.setProperty("webdriver.gecko.driver", driverPath);
        } else if (osName.toLowerCase().contains("windows") && browser.toLowerCase().contains("firefox")) {
            driverPath = driverPath+"windows/geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", driverPath);
        } else if (osName.toLowerCase().contains("linux") && browser.toLowerCase().contains("chrome")) {
            driverPath = driverPath+"linux/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else {
            driverPath = driverPath+"linux/geckodriver";
            System.setProperty("webdriver.gecko.driver", driverPath);
        }
    }

    public static void quitDriver(){
        driver.quit();
    }
}
