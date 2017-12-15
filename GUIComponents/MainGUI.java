package GUIComponents;

import DomainClasses.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class MainGUI extends JFrame {

	private static JPanel contentPane;

	private static Customer cust;
	private static AccountDAO ag = new AccountDAO();

	// customer information is stored in here when called getCustomer from DB
	private static Customer customer = new Customer();
	private static CheckingAccount ckAcct = new CheckingAccount();
	private static SavingsAccount svAcct = new SavingsAccount();
	private static CreditAccount crAcct = new CreditAccount();

	// GUI screen object
	private static ViewGUI viewGUI = new ViewGUI();
	/*
	 * Create the logInFrame.
	 */
	public MainGUI() {

		// remaning operations
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCommunityBank = new JLabel("Community Bank - Perosonal Accounts");
		lblCommunityBank.setBounds(247, 21, 402, 39);
		contentPane.add(lblCommunityBank);
		
		JButton btnViewAccounts = new JButton("View Accounts");
		btnViewAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// no need to add anything since already at this screen currently for this GUI
			}
		});
		btnViewAccounts.setBounds(0, 118, 125, 39);
		contentPane.add(btnViewAccounts);
		
		JButton btnTransfers = new JButton("Transfers");
		btnTransfers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnTransfers.setBounds(0, 178, 125, 35);
		contentPane.add(btnTransfers);

		
		JButton btnLoanCreditCard = new JButton("Loan: Credit Card");
		btnLoanCreditCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					customer = ag.getCustomer(cust.getUsername());

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				sendInformation(); // this call will send all the information regarding the accounts

				viewGUI.clickCredit(); // only displays the code changed when called on click

				// now allow the information to change in the viewGUi screen based on Checking Account
				viewGUI.setVisible(true);
			}
		});
		btnLoanCreditCard.setBounds(274, 258, 257, 35);
		contentPane.add(btnLoanCreditCard);
		
		JButton btnSavingsAccount = new JButton("Savings Account");
		btnSavingsAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					customer = ag.getCustomer(cust.getUsername());

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				sendInformation();

				viewGUI.clickSavings();

				// now allow the information to change in the viewGUi screen based on Checking Account
				viewGUI.setVisible(true);
			}
		});
		btnSavingsAccount.setBounds(274, 188, 257, 39);
		contentPane.add(btnSavingsAccount);
		
		JButton btnCheckingAccount = new JButton("Checking Account");
		btnCheckingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					customer = ag.getCustomer(cust.getUsername());

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				sendInformation();

				// now we can view the information
				viewGUI.clickChecking();

				// now allow the information to change in the viewGUi screen based on Checking Account
				viewGUI.setVisible(true);
			}
		});

		btnCheckingAccount.setBounds(274, 118, 257, 39);
		contentPane.add(btnCheckingAccount);
	}

	// Now we have acess to the values that user typed and use it specifly for this Main GUI object
	public void storeLoggedCustomer(String username, String password){
		cust = new Customer(username,password);

	}

	// helper method that passes all the account information when requested
	public static void sendInformation(){

		// now call the view checking Account
		ckAcct = ag.viewChecking(customer.getCustomerID());

		viewGUI.storeCheckingInfo(ckAcct.getAccountID(),ckAcct.getBalance(),
				ckAcct.getAccountType(),ckAcct.getServiceCharge());

		svAcct = ag.viewSavingsAccount(customer.getCustomerID());

		viewGUI.storeSavingsInfo(svAcct.getAccountID(),svAcct.getBalance(),
				svAcct.getAccountType(),svAcct.getIntrestRate());

		crAcct = ag.viewCredit(customer.getCustomerID());

		// pass the information into the visibleGUI
		viewGUI.storeCreditInfo(crAcct.getAccountID(),crAcct.getBalance(),
				crAcct.getAccountType(),crAcct.getCreditLimt(), crAcct.getIntrestRate());

	}

}
