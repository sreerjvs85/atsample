package at.pages;

import at.commonLibrary.WebElementFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MyTransactionsPageObjects {
    public WebDriver driver;

    String tableKey = null;
    TableColumnRow tableColumnRow = new TableColumnRow();
    LinkedHashMap<String, TableTransaction> tableColumnKeys = new LinkedHashMap<>();

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

    @FindAll(
            @FindBy(how = How.XPATH, using = "//div[@class='transactions-tools']/pagination/div/div[@class='page ng-scope']"))
    List<WebElement> linkTransactionHistoryPages;

    public String getStringDestination() throws IOException {
        for (String key: tableJourneyDetails().keySet()){
            System.out.println(key);
            System.out.println(tableColumnKeys.get(key));
        }
        return tableColumnElements.get(0).getAttribute("innerText");
    }

    private int getTableTransactionsSize() {
        return listTableTransactions.size();
    }


    public LinkedHashMap<String, TableTransaction> tableJourneyDetails() throws IOException {
        for (int pageCount = 0; pageCount <= linkTransactionHistoryPages.size(); pageCount++) {
            for (WebElement element : listTableTransactions) {
                List<WebElement> tBody = element.findElements(By.xpath(".//tbody"));
                TableTransaction tableTransaction = new TableTransaction();
                for (WebElement body : tBody) {
                    List<WebElement> tRow = body.findElements(By.xpath(".//tr"));
                    if (tRow.size() == 1 && !tRow.get(0).getText().startsWith("Auto")) {
                        tableKey = tRow.get(0).getText();
                        tableTransaction.setDate(tableKey);
                    } else {
                        for (WebElement data : tRow) {
                            List<WebElement> tCol = data.findElements(By.xpath(".//td"));

                            String transaction = tCol.get(0).getText();
                            String journeyID = tCol.get(1).getText();
                            String time = tCol.get(2).getText();
                            String credit = tCol.get(3).getText();
                            String debit = tCol.get(4).getText();
                            String action = tCol.get(5).getText();
                            String hopBalance = tCol.get(6).getText();

                            tableColumnRow = new TableColumnRow(transaction, journeyID, time, credit, debit, action, hopBalance);
                            if (tableColumnKeys.containsKey(tableKey)) {
                                tableTransaction = tableColumnKeys.get(tableKey);
                            }
                            tableTransaction.getTableColumnRows().add(tableColumnRow);
                        }
                    }
                }
                tableColumnKeys.put(tableKey, tableTransaction);
            }
            if (pageCount<linkTransactionHistoryPages.size()) {
                WebElementFunctions.click(linkTransactionHistoryPages.get(pageCount));
            }
        }
        WebElementFunctions.click(linkTransactionHistoryPages.get(0));
        return tableColumnKeys;
    }

    public static class TableTransaction{

        public List<TableColumnRow> getTableColumnRows() {
            return tableColumnRows;
        }

        public void setTableColumnRows(List<TableColumnRow> tableColumnRows) {
            this.tableColumnRows = tableColumnRows;
        }

        public TableTransaction() {
        }

        @Override
        public String toString() {
            return "TableTransaction{" +
                    "date='" + date + '\'' +
                    ", tableColumnRows=" + tableColumnRows +
                    '}';
        }

        private String date;
        private List<TableColumnRow> tableColumnRows= new ArrayList();

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

    }

    public static class TableColumnRow {

        @Override
        public String toString() {
            return "TableColumnRow{" +
                    "transaction='" + transaction + '\'' +
                    ", journeyID='" + journeyID + '\'' +
                    ", time='" + time + '\'' +
                    ", credit='" + credit + '\'' +
                    ", debit='" + debit + '\'' +
                    ", action='" + action + '\'' +
                    ", hopBalance='" + hopBalance + '\'' +
                    '}';
        }

        private String transaction;
        private String journeyID;
        private String time;
        private String credit;
        private String debit;
        private String action;
        private String hopBalance;

        public TableColumnRow(String transaction, String journeyID, String time, String credit, String debit, String action, String hopBalance) {
            this.transaction = transaction;
            this.journeyID = journeyID;
            this.time = time;
            this.credit = credit;
            this.debit = debit;
            this.action = action;
            this.hopBalance = hopBalance;
        }


        public TableColumnRow() {

        }

        public String getTransaction() {
            return transaction;
        }

        public void setTransaction(String transaction) {
            this.transaction = transaction;
        }

        public String getJourneyID() {
            return journeyID;
        }

        public void setJourneyID(String journeyID) {
            this.journeyID = journeyID;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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

        public void setDebit(String debit) {
            this.debit = debit;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getHopBalance() {
            return hopBalance;
        }

        public void setHopBalance(String hopBalance) {
            this.hopBalance = hopBalance;
        }


    }

}
