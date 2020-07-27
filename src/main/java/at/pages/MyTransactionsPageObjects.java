package at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class MyTransactionsPageObjects {
    public WebDriver driver;

    int tableTransactionsSize;
    int tableRowCount;
    int tableColumnCount;

    public MyTransactionsPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindAll
            (@FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table"))
    List<WebElement> listTableTransactions;

    @FindAll(
            @FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table[2]/tbody[2]/tr[1]/td"))
    List<WebElement> tableColumnElements;

    public String getStringDestination() throws IOException {
        printTransactionTable();
        return tableColumnElements.get(0).getAttribute("innerText");
    }

    private int getTableTransactionsSize() {
        return listTableTransactions.size();
    }

    private void printTransactionTable(){
        for (int i=0;i<listTableTransactions.size();i++){
            List<WebElement> tableBody = listTableTransactions.get(i).findElements(By.xpath(".//tbody"));
            for (int j=0;j<tableBody.size();j++){
                List<WebElement> tableRow = tableBody.get(j).findElements(By.xpath(".//tr"));
                for (int k=0;k<tableRow.size();k++) {
                    List<WebElement> tableColumn = tableRow.get(k).findElements(By.xpath(".//td"));
                    LinkedHashMap<String, String> tableColumnKeys = new LinkedHashMap<>();
                    LinkedHashMap<String, String> tableColumnValues = new LinkedHashMap<>();
                    if (tableColumn.size()==0){
                        break;
                    }else if (tableColumn.size()==1){
                        tableColumnKeys.put("Transaction Date", tableColumn.get(0).getText());
                        System.out.println(tableColumnKeys);
                    } else {
                        tableColumnValues.put("Transaction", tableColumn.get(0).getText());
                        tableColumnValues.put("Journey ID", tableColumn.get(1).getText());
                        tableColumnValues.put("Time", tableColumn.get(2).getText());
                        tableColumnValues.put("Credit", tableColumn.get(3).getText());
                        tableColumnValues.put("Debit", tableColumn.get(4).getText());
                        tableColumnValues.put("Action", tableColumn.get(5).getText());
                        tableColumnValues.put("HOP Balance", tableColumn.get(6).getText());
                        System.out.println(tableColumnValues);
                    }
                }
            }
        }
    }
}
