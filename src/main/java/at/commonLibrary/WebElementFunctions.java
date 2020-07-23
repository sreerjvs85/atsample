package at.commonLibrary;

import at.browserLibrary.BrowserFunctions;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebElementFunctions extends BrowserFunctions {

    public static void click(WebElement element) throws IOException {
        if (isWebelementPresent(element)) {
            element.click();
        }
    }

    public static void fillField(WebElement element, String str) throws IOException {
        if (isWebelementPresent(element)){
            element.sendKeys(str);
        }
    }

    public static String getMessage(WebElement element) throws IOException {
        if (isWebelementPresent(element)) {
            return element.getAttribute("innerText");
        } else {
            return null;
        }
    }

    public static boolean isWebelementPresent(WebElement ele) throws IOException {
        try {
            return ele.isDisplayed();
        } catch (Exception e) {
            takesScreenshot(driver);
            return false;
        }
    }

    public static void takesScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(setFilePath()+ new SimpleDateFormat("HHmmss").format(new Date()) +".png");
        FileUtils.copyFile(srcFile,destFile);

    }

    private static String setFilePath(){
        String path = "src/test/";
        String screenshotsFolder = path +"screenshots/";
        String todayFolder = screenshotsFolder + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) +"/";
        File file = new File(todayFolder);
        if (!file.exists()) {
            file.mkdir();
            return todayFolder;
        }
        return todayFolder;
    }

}
