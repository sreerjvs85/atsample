package at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;

public class MyTransactionsPageObjects {
    public WebDriver driver;

    String tableKey = null;
    TableColumnRow tableColumnRow = new TableColumnRow();
    LinkedHashMap<String, Object> tableColumnKeys = new LinkedHashMap<>();

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

    public String getStringDestination() {
        for (String key: printTransactionTable().keySet()){
            System.out.println(key);
            for (Object value: printTransactionTable().values()){

            }
        }
        return tableColumnElements.get(0).getAttribute("innerText");
    }

    private int getTableTransactionsSize() {
        return listTableTransactions.size();
    }

    public LinkedHashMap<String, Object> printTransactionTable(){

        for (int i=0;i<listTableTransactions.size();i++){
            List<WebElement> tableBody = listTableTransactions.get(i).findElements(By.xpath(".//tbody"));
            for (int j=0;j<tableBody.size();j++){
                List<WebElement> tableRow = tableBody.get(j).findElements(By.xpath(".//tr"));
                for (int k=0;k<tableRow.size();k++) {
                    List<WebElement> tableColumn = tableRow.get(k).findElements(By.xpath(".//td"));
                    if (tableColumn.size()==0){
                        break;
                    }else if (tableColumn.size()==1){
                        tableKey = tableColumn.get(0).getText();
                        tableColumnKeys.put(tableKey, tableColumnRow);
                    } else {
                        String trans = tableColumn.get(0).getText();
                        String  jID = tableColumn.get(1).getText();
                        String time = tableColumn.get(2).getText();
                        String credit = tableColumn.get(3).getText();
                        String debit = tableColumn.get(4).getText();
                        String action = tableColumn.get(5).getText();
                        String hopBal = tableColumn.get(6).getText();

                        tableColumnRow = new TableColumnRow(trans, jID, time, credit, debit, action, hopBal);
                        tableColumnKeys.replace(tableKey, tableColumnRow);
                    }
                }
            }
        }
        return tableColumnKeys;
    }

    public static class TableColumnRow {
        public String transaction;
        public String  journeyID;
        public String time;
        public String credit;
        public String debit;
        public String action;
        public String hopBalance;

        public TableColumnRow(String tran, String jID, String time, @Nullable String credit, @Nullable String debit, @Nullable String action, String hopBalance){
            setTransaction(tran);
            setJourneyID(jID);
            setTime(time);
            setCredit(credit);
            setDebit(debit);
            setAction(action);
            setHopBalance(hopBalance);
        }

        public TableColumnRow() {

        }
        public String getTransaction(){
            return transaction;
        }
        public void setTransaction(String tran){
            transaction=tran;
        }
        public String getJourneyID() {
            return journeyID;
        }
        public void setJourneyID(String jID){
            journeyID=jID;
        }
        public String getTime() {
            return time;
        }
        public void setTime(String time){
            this.time=time;
        }
        public String getCredit() {
            return credit;
        }
        public void setCredit(String credit) {
            this.credit = credit;
        }
        public String getDebit() {
            return debit;
        }
        public void setDebit(String debit){
            this.debit=debit;
        }
        public String getAction() {
            return action;
        }
        public void setAction(String action) {
            this.action=action;
        }
        public String getHopBalance() {
            return hopBalance;
        }
        public void setHopBalance(String hopBalance){
            this.hopBalance=hopBalance;
        }

    }

}
