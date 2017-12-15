package DomainClasses;

import java.util.Date;

/**
 * Created by arnav
 */
public class SavingsAccount extends Account {
    private double intrestRate;

    public SavingsAccount(){

    }
    public SavingsAccount (int accountID, double balance, String accountType,double intrestRate){
        super(accountID, balance, accountType);
        this.intrestRate = intrestRate;
    }

    public void setIntrestRate(double intrestRate) {
        this.intrestRate = intrestRate;
    }

    public double getIntrestRate() {
        return intrestRate;
    }
}
