package DomainClasses;

/**
 * Created by arnav
 */
public class CreditAccount extends Account {
    private double creditLimt;
    private double intrestRate; // set the rate here by default make it once time effect on the balnce your case

    public CreditAccount(){

    }

    public CreditAccount (int accountID, double balance, String accountType ,double creditLimt,double intrestRate){
        super(accountID, balance, accountType);
        this.creditLimt = creditLimt ;
        this.intrestRate = creditLimt;

    }

    public double getCreditLimt() {
        return creditLimt;
    }

    public void setCreditLimt(double creditLimt) {
        this.creditLimt = creditLimt;
    }

    public double getIntrestRate() {
        return intrestRate;
    }

    public void setIntrestRate(double intrestRate) {
        this.intrestRate = intrestRate;
    }
}
