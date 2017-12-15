package DomainClasses;

import java.util.Date;

/**
 * Created by arnav
 */
public class Transaction {
    private Date dateOfTransaction;
    private String transactionDescription;
    private double withdraw;
    private double deposit;
    private double balance;

    public Transaction() {
    }

    public Transaction(Date dateOfTransaction, String transactionDescription,
                       double withdraw, double deposit, double balance) {
        this.dateOfTransaction = dateOfTransaction;
        this.transactionDescription = transactionDescription;
        this.withdraw = withdraw;
        this.deposit = deposit;
        this.balance = balance;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
