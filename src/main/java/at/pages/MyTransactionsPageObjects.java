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
import java.util.HashMap;
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
//            System.out.println("Table Transactions: " + listTableTransactions.get(i).getText());
            for (int j=0;j<tableBody.size();j++){
                List<WebElement> tableRow = tableBody.get(j).findElements(By.xpath(".//tr"));
//                System.out.println("Table Body: "+ tableBody.get(j).getText());
                for (int k=0;k<tableRow.size();k++) {
                    List<WebElement> tableColumn = tableRow.get(k).findElements(By.xpath(".//td"));
                    HashMap<String, String> tableColumnKeys = new HashMap<>();

                    for (int l = 0; l < tableColumn.size(); l++) {
                        HashMap<String, String> tableColumnValues = new HashMap<>();
                        if (tableColumn.size()==1){
                            System.out.println("Transaction Date: " + tableColumn.get(l).getText());
                            tableColumnKeys.put("Transaction Date", tableColumn.get(l).getText());
                        } else {
                            switch (l) {
                                case 0:
                                    System.out.println("Transaction: " + tableColumn.get(l).getText());
                                    tableColumnValues.put("Transaction", tableColumn.get(l).getText());
                                    break;
                                case 1:
                                    System.out.println("Journey ID: " + tableColumn.get(l).getText());
                                    tableColumnValues.put("Journey ID", tableColumn.get(l).getText());
                                    break;
                                case 2:
                                    System.out.println("Time: " + tableColumn.get(l).getText());
                                    tableColumnValues.put("Time", tableColumn.get(l).getText());
                                    break;
                                case 3:
                                    System.out.println("Credit: " + tableColumn.get(l).getText());
                                    tableColumnValues.put("Credit", tableColumn.get(l).getText());
                                    break;
                                case 4:
                                    System.out.println("Debit: " + tableColumn.get(l).getText());
                                    tableColumnValues.put("Debit", tableColumn.get(l).getText());
                                    break;
                                case 5:
                                    System.out.println("Action: " + tableColumn.get(l).getText());
                                    tableColumnValues.put("Action", tableColumn.get(l).getText());
                                    break;
                                case 6:
                                    System.out.println("HOP Balance: " + tableColumn.get(l).getText());
                                    tableColumnValues.put("HOP Balance", tableColumn.get(l).getText());
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }
    }
