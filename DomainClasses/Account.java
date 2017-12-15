package DomainClasses;
import java.util.Date;

/**
 * Created by arnav
 */
public class Account {
    // properties for account
    private int accountID;
    private double balance;
    private String accountType;

    // try to form the basic object where the value will be set to login each time
    public Account() {

    }

    public Account(int accountID, double balance, String accountType) {
        this.accountID = accountID;
        this.balance = balance;
        this.accountType = accountType;

    }

    public int getAccountID() {
        return accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(double balance) {

        this.balance = balance;
    }
}