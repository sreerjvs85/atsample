package at.pages;

import at.commonLibrary.WebElementFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.List;

public class MyTransactionsPageObjects {
    public final WebDriver driver;

    public MyTransactionsPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindAll
            (@FindBy(how = How.XPATH,using = "//table[@class='transactions-table ng-scope']"))
    List<WebElement> listTableTransactions;

    @FindAll(
            @FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table[2]/tbody[2]/tr[1]/td"))
    List<WebElement> tableColumnElements;

    @FindBy(how = How.XPATH, using = "//pagination[@class='hidden-small-dwn ng-isolate-scope']/div[@class='pagination']/div[@class='page ng-scope current-page']")
    WebElement txtCurrentPage;

    @FindAll(
            @FindBy(how = How.XPATH, using = "//pagination[@class='hidden-small-dwn ng-isolate-scope']/div[@class='pagination']/div[@class='page ng-scope']"))
    List<WebElement> linkNextPages;

    public String getStringDestination(){
        return tableColumnElements.get(0).getAttribute("innerText");
    }

    private String[] transactions(WebElement element) {
        String[] transactions;
        transactions = element.getText().split("\n");
        return transactions;
    }

    public String[] targettedTransactions(String arg0) {
        String[] targetStringList;
        int index = 0;
        switch (arg0.toLowerCase()) {
            case "first":
                index = 0;
                break;
            case "second":
                index = 1;
                break;
            case "third":
                index = 2;
                break;
            case "fourth":
                index = 3;
                break;
            case "fifth":
                index = 4;
                break;
        }
        targetStringList = transactions(listTableTransactions.get(index));
        return targetStringList;
    }

    private String getTxtCurrentPage() throws IOException {
        return WebElementFunctions.getMessage(txtCurrentPage);
    }

    public void navigateToPage(String page) throws IOException {
        int pageNumber = Integer.parseInt(page.substring(page.length()-1));
        for (WebElement element: linkNextPages) {
            String nextPageNumber;
            nextPageNumber = element.getText();
            if (nextPageNumber.equals(page.substring(page.length()-1))){
                WebElementFunctions.click(element);
            }
        }
    }
}
