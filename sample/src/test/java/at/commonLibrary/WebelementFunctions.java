package at.commonLibrary;

import at.browserLibrary.BrowserFunctions;
import org.openqa.selenium.WebElement;

public class WebelementFunctions extends BrowserFunctions {

    public static void click(WebElement element){
        element.click();
    }

    public static void fillField(WebElement element, String str) {
        element.sendKeys(str);
    }

    public static String getMessage(WebElement element) {
        String message = element.getText();
        return message;
    }
}
