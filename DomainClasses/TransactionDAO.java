package DomainClasses;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arnav
 */
public class TransactionDAO {
    // declare the commands
    private static Statement myStat = null;
    private static Connection myCon = null;

    // DB credential
    private static String url = "jdbc:mysql://localhost:3306/bankdata";
    private static String password = "Arnav7629";
    private static String user = "root";

    public TransactionDAO() {
        // use it to acess other method in the class without lauching the app
        try {

            // Step 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Step 2: get a connection , from with connection object open DB for queries
            myCon = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error connecting. Please check your internet connection and try again.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------PERFORM TRANSACTION------------

    public static boolean performTransaction( String fromAccountName,double withdrawAmt, double fromBalance,
                                              double fromCharge, int fromAcctID, String description, double creditLimit
                                             ,String toAccountName, double toBalance, int toAcctID, String toAcctType ) {

        // store the values in the variables that are need before checking if transaction is valid
        boolean transcation = false;
        double newFromBalance = fromBalance - withdrawAmt;
        double newToBalance= 0.0;

        System.out.println(fromCharge);

        // check if there is enough funds avalible in the account then proceed with the overall
        if(newFromBalance >= 0) {

            double chargeValue = (newFromBalance * fromCharge);
            newFromBalance = (newFromBalance) - (chargeValue);
            newToBalance =  toBalance + withdrawAmt;

            System.out.println(chargeValue);
            System.out.println(newFromBalance);

            try {

                myStat = myCon.createStatement();

                // QUERRY 1 STARTS this is to update the from account with new balance
               String querryUpdateFrom = "UPDATE " + fromAccountName + " SET balance = " + newFromBalance +" WHERE accountID = " + fromAcctID;
                myStat.executeUpdate(querryUpdateFrom);


                // QUERRY 2 STARTS this is to update the To account with new balance
                String querryUpdateTo = "UPDATE " + toAccountName + " SET balance = " + newToBalance +" WHERE accountID = " + toAcctID;
                myStat.executeUpdate(querryUpdateTo);


                // Now setup the information for the Transaction Table to insert the TWO records (FromAccount) and (ToAccount)
                int transactionID = generateID();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String todayDate = dateFormat.format(date);

                // PERFORM THE QUERY for recording the transaction for the FROM Account
                String querryTransactionFrom = "INSERT INTO transaction (transactionID, billID, accountID, transactionDate, withdraw, deposit, transctionDescription)"
                                                                                 + " VALUES " + "(" + transactionID + ", "
                                                                                 + 0 + ", " + fromAcctID + ", "
                                                                                 + "\"" +todayDate +"\""+ ", " + withdrawAmt + ", "
                                                                                 + 0 + ", "+ "\"" + description +  "\" " + ")";


                myStat.executeUpdate(querryTransactionFrom);

                if(!toAcctType.equals("Loan")) {
                    // PERFORM THE QUERY for recording the transaction for the TO Account WHEN NOT A LOAN
                    transactionID = generateID();

                    String querryTransactionTO = "INSERT INTO transaction (transactionID, billID, accountID, transactionDate, withdraw, deposit, transctionDescription)"
                                                                                    + " VALUES " + "(" + transactionID + ", "
                                                                                    + 0 + ", " + toAcctID + ", "
                                                                                    + "\"" + todayDate + "\"" + ", " + 0 + ", "
                                                                                    + withdrawAmt + ", "+ "\"" + description +  "\" " + ")";

                    myStat.executeUpdate(querryTransactionTO);


                } else{
                    // NOW THIS IS A LOAN CASE
                    int billID = generateID();

                    // Make insert into the bill payment table first
                    String querryTransactionLoan = "INSERT INTO billpayment (billID, accountID, transactionID, dateBillPaid, amountPaid) "
                                                                                     + "VALUES " + "(" + billID + ", "
                                                                                     + fromAcctID + ", " + transactionID +", "
                                                                                     +" \""+ todayDate +"\""+ ", " + withdrawAmt + ")";
                    myStat.executeUpdate(querryTransactionLoan);

                    // QUERY for recording the data of Bill Id for the To account now which is CreditAccount
                    int newTransactionID = generateID();
                    String querryTransactionToLoan = "INSERT into transaction (transactionID, billID, accountID, transactionDate, withdraw, deposit, transctionDescription)" +
                                                                                        "VALUES " + "(" + newTransactionID + ", "
                                                                                        + billID + ", " + toAcctID + ", "
                                                                                        + " \"" +todayDate + "\"" + ", " + 0 + ", "
                                                                                        + withdrawAmt + "," + " \"" + description +  "\" " + ")";
                    myStat.executeUpdate(querryTransactionToLoan);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            transcation = true;
        }
            return transcation;
    }

    public static int generateID(){
        int ID = 0;
        String finalID = "";

        for (int i =0; i< 8; i++) {
            ID = 1 + (int) (Math.random() * 9);
            finalID += Integer.toString(ID);
        }
        ID =  Integer.parseInt(finalID);

        return ID;
    }

}
