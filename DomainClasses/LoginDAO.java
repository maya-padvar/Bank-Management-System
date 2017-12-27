package DomainClasses;

import GUIComponents.LoginGUI;// now be able to acess the GUI SCREEN
import javax.swing.*;
import java.sql.*;

/*
 * Created by arnav
 */
public class LoginDAO {
    // Have the variables used in the class

    // DB credential
    private static String url = "jdbc:mysql://localhost:3306/bankdata";
    private static String password = "Arnav7629";
    private static String user = "root";

    // declare the commands
    private static ResultSet result = null;
    private static Statement myStat = null;
    private static Connection myCon = null;

    private static LoginGUI loginGUI = new LoginGUI();

    public LoginDAO() {

        loginGUI.logInFrame.setVisible(true); // will show the screen now

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

    // Method Description : Checks if the entered user name is valid and exists in the DB

    public static boolean ifUserExisits(String userName) throws SQLException {
        boolean userExists = false;
        String userNameRs = " ";
        try {
            // Excecute the query based on what they have typed
            myStat = myCon.createStatement();

            // Excecute the SQL Querry
            String query = "SELECT username FROM customer WHERE username = " + "'" + userName + "'";
            result = myStat.executeQuery(query);

            // make the check to see if the user name will match the database
            if (result.next()) {
                System.out.println("User Exists");
                 userExists = true;

            } else {
                System.out.println("THE USERNAME IS INCORRECT"); // will change this to a JDialogs box
                 userExists = false;
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return userExists;
    }

    //Checks that the passed-in user's password is valid
    public static boolean checkIfPasswordValid(String username, String password) throws SQLException {
        //Before querying, hash the password to some arbitrary value
        boolean checkValues = false;
        PreparedStatement myStatement = null;
        myStatement = myCon.prepareStatement("SELECT username, password FROM customer WHERE username='?' AND password='?'");
        myStatement.setString(1, username);
        myStatement.setString(2, password);

        try {
            // Excecute the query based on what they have typed
           result = myStatement.execute();

            //If we can move within the result set, a record exists. Thus, a matching user was found
            if (result.next()) {
                 checkValues = true;
            }

            //Otherwise, no matching user was found
            else {
                 checkValues = false;
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return checkValues;
    }

}
