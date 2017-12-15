package DomainClasses;

import javax.swing.*;
import java.sql.*;

/**
 * Created by arnav
 */
public class AccountDAO {

    // declare the commands
    private static ResultSet result = null;
    private static Statement myStat = null;
    private static Connection myCon = null;

    // DB credential
    private static String url = "jdbc:mysql://localhost:3306/bankdata";
    private static String password = "Arnav7629";
    private static String user = "root";


    // Stub objects used to return values for the called GUI SCREEN
    private static Customer loggedInUser = new Customer();
    private static CheckingAccount ckAcct = new CheckingAccount();
    private static SavingsAccount svAcct = new SavingsAccount();
    private static CreditAccount crAcct = new CreditAccount();

    public AccountDAO() {
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

    public Customer getCustomer(String userName) throws SQLException {

        // extract all the information and store it into the customer object
        try {

            // Excecute the query based on what they have typed
            myStat = myCon.createStatement();

            // Excecute the SQL Querry
            String query = "SELECT * FROM customer WHERE username = " + "'" + userName + "'";

            result = myStat.executeQuery(query);

            //If we can move within the result set, a record exists. Thus, a matching user was found
            if (result.next()) {
                //Create a new user object with the data from the record

                Customer newUser = new Customer(result.getInt("customerID"), result.getString("password")
                                   ,result.getString("username"), result.getString("firstName"),
                                    result.getString("lastName"));

                //Return the newly-created user object
                loggedInUser = newUser;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedInUser;
    }

    // viewChecking: account details and store that as an object with the select query that is based
    // pass in items that will be needed to make the JOIN QUERRY

    public static CheckingAccount viewChecking(int customerID) {
        // store the values in the variables
        int accountID = 0;
        double balance = 0;
        String accType = " ";
        String checking = "Checking";

        try {
            // use the call to get customer here then proceed with extracting the remaning information

            myStat = myCon.createStatement();

            // excecute the querry to get all the account ID using the customerID Passed in then obtain rest of information
            String sQuery = "SELECT a.accountID, a.balance, a.accountType FROM account a INNER JOIN customer b ON "
                    + "a.customerID " + "= b.customerID AND a.accountType ='" + checking + "'" + " AND a.customerID = " + customerID;

            result = myStat.executeQuery(sQuery);

            if (result.next()) { // move the cursor to the object's value
                accountID = result.getInt("accountID");
                balance = result.getDouble("balance");
                accType = result.getString("accountType");
            }

            // Excecute the SQL Querry To now join the values of accunt and get service charge
            String query = "SELECT c.serviceCharge " + "FROM account a INNER JOIN checkingaccount c ON a.accountID = c.CAccountID" + " AND "
                            + "a.accountID = " + accountID;

            result = myStat.executeQuery(query);

            if (result.next()) {
                double serviceCharge = result.getDouble("serviceCharge");
                // now fill in the CheckingAccount object for later to use
                CheckingAccount ckAcct = new CheckingAccount(accountID, balance, accType, serviceCharge);

                AccountDAO.ckAcct = ckAcct;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ckAcct;
    }

    // this method will return the savings account details to the user when they interect with GUI
    // pass in the customerID inorder to make the  SQL querry.

    public static SavingsAccount viewSavingsAccount(int customerID){
        // store the values in the variables
        int accountID = 0;
        double balance = 0;
        String accType = " ";
        String savings = "Savings";

        try {
            // use the call to get customer here then proceed with extracting the remaning information

            myStat = myCon.createStatement();

            // excecute the querry to get all the account ID using the customerID Passed in then obtain rest of information
            String sQuery = "SELECT a.accountID, a.balance, a.accountType FROM account a INNER JOIN customer b ON "
                    + "a.customerID " + "= b.customerID AND a.accountType ='" + savings + "'" + " AND a.customerID = " + customerID;

            result = myStat.executeQuery(sQuery);

            if (result.next()) { // move the cursor to the object's value

                accountID = result.getInt("accountID");
                balance = result.getDouble("balance");
                accType = result.getString("accountType");
            }

            // Excecute the SQL Querry To now join the values of accunt and get service charge
            String query = "SELECT c.intrestRate " + "FROM account a INNER JOIN savingsaccount c ON a.accountID = c.SAccountID" + " AND "
                    + "a.accountID = " + accountID;
            result = myStat.executeQuery(query);

            if (result.next()) {
                double interestRate = result.getDouble("intrestRate");
                // now fill in the CheckingAccount object for later to use
                SavingsAccount loggedSv = new SavingsAccount(accountID, balance, accType, interestRate);

                svAcct = loggedSv;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return svAcct;
    }


    public static CreditAccount viewCredit(int customerID){
        // store the values in the variables
        int accountID = 0;
        double balance = 0;
        String accType = " ";
        String loan = "Loan";

        try {
            // use the call to get customer here then proceed with extracting the remaning information

            myStat = myCon.createStatement();

            // excecute the querry to get all the account ID using the customerID Passed in then obtain rest of information
            String sQuery = "SELECT a.accountID, a.dateOfRegistration, a.balance, a.accountType FROM account a INNER JOIN customer b ON "
                    + "a.customerID " + "= b.customerID AND a.accountType ='" + loan + "'" + " AND a.customerID = " + customerID;

            result = myStat.executeQuery(sQuery);

            if (result.next()) { // move the cursor to the object's value

                accountID = result.getInt("accountID");
                balance = result.getDouble("balance");
                accType = result.getString("accountType");
            }

            // Excecute the SQL Querry To now join the values of accunt and get service charge
            String query = "SELECT c.intrestRate, c.creditLimt " + "FROM account a INNER JOIN creditaccount c ON a.accountID = c.LAccountID" + " AND "
                    + "a.accountID = " + accountID;

            result = myStat.executeQuery(query);

            if (result.next()) {
                double interestRate = result.getDouble("intrestRate");
                double creditLimit = result.getDouble("creditLimt");

                // now fill in the CheckingAccount object for later to use
                CreditAccount loggedCrd = new CreditAccount(accountID, balance, accType, creditLimit ,interestRate);

                crAcct = loggedCrd;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crAcct;
    }
}


