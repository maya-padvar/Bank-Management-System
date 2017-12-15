package DomainClasses;
import java.util.Date;
/**
 * Created by arnav
 */
public class CheckingAccount extends Account {
    private double serviceCharge; // set the default vaule here

   public CheckingAccount(){

   }

    public CheckingAccount(int accountID, double balance, String accountType, double serviceCharge) {
        super(accountID, balance, accountType); // inherent the vlaues form superclass default
        this.serviceCharge = serviceCharge;
    }

    public double getServiceCharge() {

        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
}