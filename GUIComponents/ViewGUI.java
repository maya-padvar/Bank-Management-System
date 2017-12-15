package GUIComponents;

import DomainClasses.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ViewGUI extends JFrame {

	//Display the InBetween
	private static InBetweenGUI inBetweenGUI = new InBetweenGUI();

	private static JPanel contentPane;
	private static JTextField balanceTextFeild;
	private static JTable table;

	private static JLabel lblAccountName;
	private static JLabel lblAccountId;
	private static JLabel lblCurrentBalance;

	// the objects that will be needed to work with in this GUI
	private static Customer loggedInUser = new Customer();

	private static Transaction transaction = new Transaction();
	private static List<Transaction> listTransactions; // will store the transactions here

	private static CheckingAccount loggedCkAcct = new CheckingAccount();
	private static SavingsAccount loggedSvAcct = new SavingsAccount();
	private static CreditAccount loggedCrAcct = new CreditAccount();

	// the table model created here
	private static TransactionTableModel transactionTable;

	/**
	 * Create the frame.
	 */
	public ViewGUI() {
		setTitle("Community Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAccountName = new JLabel("Account Name :");
		lblAccountName.setBounds(253, 21, 159, 26);
		contentPane.add(lblAccountName);

		lblAccountId = new JLabel("Account ID");
		lblAccountId.setBounds(411, 21, 114, 26);
		contentPane.add(lblAccountId);

		JLabel lblCurrentBalance = new JLabel("Current Balance");
		lblCurrentBalance.setBounds(253, 59, 159, 26);
		contentPane.add(lblCurrentBalance);

		balanceTextFeild = new JTextField();
		balanceTextFeild.setBounds(411, 56, 152, 32);
		contentPane.add(balanceTextFeild);
		balanceTextFeild.setColumns(10);

		// TODO THIS IS TO BE IMPLEMENTED AS AN SIDEBAR TO NAVIGATE FOR THE USER (CREATE THE GUI SCREEN AND MAKE it visible

		JButton btnViewAccount = new JButton("View Account");
		btnViewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnViewAccount.setBounds(0, 132, 141, 32);
		contentPane.add(btnViewAccount);

		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// call the method to send information in to inBetweenGUI
				sendAccountInfo();

				inBetweenGUI.setVisible(true);
			}
		});

		btnTransfer.setBounds(0, 187, 141, 35);
		contentPane.add(btnTransfer);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 132, 695, 249);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnViewStatment = new JButton("View Statement");
		btnViewStatment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnViewStatment.setBounds(669, 94, 176, 32);
		contentPane.add(btnViewStatment);
	}

	// Any other methods needed to be passed into this class by the user selecting this options
	// Start by passing in the values of the chekingAccount
	public static void storeCheckingInfo(int accountID, double balance, String accountType, double serviceCharge) {

		// Now set these for the checking account
		loggedCkAcct.setAccountID(accountID);
		loggedCkAcct.setBalance(balance);
		loggedCkAcct.setAccountType(accountType);
		loggedCkAcct.setServiceCharge(serviceCharge);

	}

	//NOW DO THE SAVINGS ACCOUNT INFORMATION make that acessable to this object
	public static void storeSavingsInfo(int accountID, double balance, String accountType, double intrestRate) {

		// now set the values for this screen to use and display to the screen
		loggedSvAcct.setAccountID(accountID);
		loggedSvAcct.setBalance(balance);
		loggedSvAcct.setAccountType(accountType);
		loggedSvAcct.setIntrestRate(intrestRate);
	}

	public static void storeCreditInfo(int accountID, double balance, String accountType,
									   double creditLimit, double intrestRate) {

		loggedCrAcct.setAccountID(accountID);
		loggedCrAcct.setBalance(balance);
		loggedCrAcct.setAccountType(accountType);
		loggedCrAcct.setCreditLimt(creditLimit);
		loggedCrAcct.setIntrestRate(intrestRate);

	}

	// invoke method based on the call
	public static void clickChecking(){

		// set the text of labes to the new retrived vales
		lblAccountName.setText(loggedCkAcct.getAccountType() + " ID:");
		lblAccountId.setText(String.valueOf(loggedCkAcct.getAccountID())); //String.valueOf returns the string value of int

		// set the textfield to view this and not allow user to change the screen
		balanceTextFeild.setText(String.valueOf(loggedCkAcct.getBalance()));
		balanceTextFeild.setEditable(false);

	}
	public static void clickSavings(){
		// and apply this change to the lables
		lblAccountName.setText(loggedSvAcct.getAccountType() + " ID:");
		lblAccountId.setText(String.valueOf(loggedSvAcct.getAccountID())); //String.valueOf returns the string value of int

		// set the textfield to view this and not allow user to change the screen
		balanceTextFeild.setText(String.valueOf(loggedSvAcct.getBalance()));
		balanceTextFeild.setEditable(false);
	}

	public static void clickCredit(){
		// now display that material
		// and apply this change to the lables
		lblAccountName.setText(loggedCrAcct.getAccountType() + " ID:");
		lblAccountId.setText(String.valueOf(loggedCrAcct.getAccountID())); //String.valueOf returns the string value of int

		// set the textfield to view this and not allow user to change the screen
		balanceTextFeild.setText(String.valueOf(loggedCrAcct.getBalance()));
		balanceTextFeild.setEditable(false);

		// Now add the extra code for the credit limit view
		JLabel lblNewLabel = new JLabel("Credit Limit");
		lblNewLabel.setBounds(253, 97, 92, 26);
		contentPane.add(lblNewLabel);

		JTextField creditLimitTextFeild = new JTextField();
		creditLimitTextFeild.setColumns(10);
		creditLimitTextFeild.setBounds(411, 94, 152, 32);

		creditLimitTextFeild.setText(String.valueOf(loggedCrAcct.getCreditLimt()));
		creditLimitTextFeild.setEditable(false);

		contentPane.add(creditLimitTextFeild);
	}

	public static void sendAccountInfo(){

		// send the accounts information to the next GUI to use
		inBetweenGUI.storeCheckingInfo(loggedCkAcct.getAccountID(),loggedCkAcct.getBalance(),
				loggedCkAcct.getAccountType(),loggedCkAcct.getServiceCharge());


		inBetweenGUI.storeCreditInfo(loggedCrAcct.getAccountID(),loggedCrAcct.getBalance(),
				loggedCrAcct.getAccountType(),loggedCrAcct.getCreditLimt(),loggedCrAcct.getIntrestRate());

		inBetweenGUI.storeSavingsInfo(loggedSvAcct.getAccountID(),loggedSvAcct.getBalance(),
				loggedSvAcct.getAccountType(),loggedSvAcct.getIntrestRate());

	}
}
