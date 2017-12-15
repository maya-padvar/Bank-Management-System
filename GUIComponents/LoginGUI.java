package GUIComponents;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import DomainClasses.Customer;
import DomainClasses.LoginDAO;

public class LoginGUI {

	public static JFrame logInFrame = new JFrame();
	private JTextField txtEnterAcesscard;
	private JTextField textPassword;
	private JLabel lblEasyBanking;

	private static LoginDAO lg = new LoginDAO();
	private static ViewGUI viewGUI = new ViewGUI();

	/* Create the application.
	 */
	public LoginGUI() {
		initialize();

	}
	/**
	 * Initialize the contents of the logInFrame.
	 */
	private void initialize() {
		logInFrame.setTitle("Community Bank");
		logInFrame.setBounds(100, 100, 571, 341);
		logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logInFrame.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(45, 94, 108, 26);
		logInFrame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 156, 92, 26);
		logInFrame.getContentPane().add(lblPassword);
		
		txtEnterAcesscard = new JTextField();
		txtEnterAcesscard.setText("Enter AcessCard");
		txtEnterAcesscard.setBounds(272, 91, 186, 32);
		logInFrame.getContentPane().add(txtEnterAcesscard);
		txtEnterAcesscard.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(272, 153, 186, 32);
		logInFrame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// to store it as a String object and use it later
				String typUsername = new String (txtEnterAcesscard.getText());
				String typPassword = new String (textPassword.getText());

				try {
					// add the event that will occur when you click log in ass in to the enterCredentails().
					enterCredentials(typUsername,typPassword);

				}
				catch (SQLException e){
					e.printStackTrace();
				}
			}
		});
		
		btnLogin.setBounds(298, 214, 115, 35);
		logInFrame.getContentPane().add(btnLogin);
		
		lblEasyBanking = new JLabel("Easy Banking");
		lblEasyBanking.setBounds(197, 21, 149, 26);
		logInFrame.getContentPane().add(lblEasyBanking);
	}

	//Check if the username specified in the LoginGUI's textfield exists in here cause easy to pass objects this way
	public static void enterCredentials(String username, String password) throws SQLException {
		try {
			Customer loggedInUser = new Customer();
			//If the user exists and their credentials are valid, return the matching User object and store it as the loggedInUser
			if (lg.ifUserExisits(username) && lg.checkIfPasswordValid(username, password)) {
				JOptionPane.showMessageDialog(null,
						"You have logged in!.", "Login Window", JOptionPane.INFORMATION_MESSAGE);

				// Pass the information into the cusotmer object from the GUI
				loggedInUser.setPassword(password);
				loggedInUser.setUsername(username);

				MainGUI mainGUI = new MainGUI();

				// now send this information for the use of the next GUI screen MAIN GUI
				mainGUI.storeLoggedCustomer(loggedInUser.getUsername(),loggedInUser.getPassword());

				logInFrame.setVisible(false); // gets rid of the screen


				mainGUI.setVisible(true);// creates the new screen with their accounts
			}

			//Otherwise, no matching user was found
			else {
				JOptionPane.showMessageDialog(null, "The user name you entered is invalid. Please double-check it and try again.",
						"Invalid Username", JOptionPane.ERROR_MESSAGE);

			}

		} catch (SQLException e) {
			throw new SQLException();
		}
	}
}
