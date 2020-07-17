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

public class WebelementFunctions extends BrowserFunctions {

    public static void click(WebElement element) throws IOException {
        if (isWebelementPresent(element)) {
            element.click();
        } else {
            takesScreenshot(driver, element);
        }
    }

    public static void fillField(WebElement element, String str) throws IOException {
        if (isWebelementPresent(element)){
        element.sendKeys(str);
        } else {
            takesScreenshot(driver,element);
        }
    }

    public static String getMessage(WebElement element) throws IOException {
        if (isWebelementPresent(element)) {
            return element.getText();
        } else {
            takesScreenshot(driver,element);
            return new Exception().getMessage();
        }
    }

    public static boolean isWebelementPresent(WebElement ele) {
        return ele.isDisplayed();
    }

    public static void takesScreenshot(WebDriver driver, WebElement ele) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(setFilePath()+ele.getText()+ new SimpleDateFormat("HHmmss").format(new Date()) +".png");
        FileUtils.copyFile(srcFile,destFile);

    }

    private static String setFilePath(){
        String path = "src";
        String screenshotsFolder = path +"/screenshots";
        String todayFolder = screenshotsFolder + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        File file = new File(todayFolder);
        if (!file.exists()) {
            file.mkdir();
            return todayFolder;
        }
        return screenshotsFolder;
    }

}
