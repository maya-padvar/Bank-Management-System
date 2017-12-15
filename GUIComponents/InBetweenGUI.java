package GUIComponents;

import DomainClasses.CheckingAccount;
import DomainClasses.CreditAccount;
import DomainClasses.SavingsAccount;
import DomainClasses.TransactionDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InBetweenGUI extends JFrame {

	private JPanel contentPane;
	private JTextField withdrawAmt;
	private JComboBox fromAccount;
	private JComboBox ToAccount;

	// create the Array that will hold the OPTIONS FOR THE USER TO SELECT FROM
	String [] fromList = new String[]{"Checking", "Savings", "Loan"};
	String [] toList = new String[]{"Savings", "Checking", "Loan"};

	// Stub objects used to return values for the called GUI SCREEN
	private static CheckingAccount loggedCkAcct = new CheckingAccount();
	private static SavingsAccount loggedSvAcct = new SavingsAccount();
	private static CreditAccount loggedCrAcct = new CreditAccount();

	// variables to use as get account types
	private static String checking;
	private static String savings;
	private static String credit;

	// DAO classes used
	private static TransactionDAO transactionDAO = new TransactionDAO();

	/**
	 * Create the frame.
	 */
	public InBetweenGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewAccounts = new JButton("View Accounts");
		btnViewAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MainGUI mainGUI = new MainGUI();
				mainGUI.setVisible(true);

			}
		});
		btnViewAccounts.setBounds(0, 74, 141, 35);
		contentPane.add(btnViewAccounts);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(0, 130, 141, 35);
		contentPane.add(btnTransfer);

		
		JLabel lblTransferInBetween = new JLabel("Transfer In Between Accounts");
		lblTransferInBetween.setBounds(262, 21, 284, 26);
		contentPane.add(lblTransferInBetween);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(251, 78, 56, 26);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(251, 134, 35, 26);
		contentPane.add(lblTo);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(251, 192, 92, 26);
		contentPane.add(lblAmount);


		// DO the Transaction setup here based on the selected account FROM list
		fromAccount = new JComboBox();

		fromAccount.setModel(new DefaultComboBoxModel(fromList)); // fill the JcomoboBox with the string list
		fromAccount.setBounds(344, 75, 132, 32);
		contentPane.add(fromAccount);


		// DO the Transaction setup here based on the selected account TO list
		ToAccount = new JComboBox();

		ToAccount.setModel(new DefaultComboBoxModel(toList));
		ToAccount.setBounds(344, 131, 132, 32);
		contentPane.add(ToAccount);


		withdrawAmt = new JTextField();
		withdrawAmt.setBounds(344, 189, 132, 32);
		contentPane.add(withdrawAmt);
		withdrawAmt.setColumns(10);


		JButton btnTransfer_1 = new JButton("Transaction");

		// TRANSFER BUTTON ACTION LISTNER!!
		btnTransfer_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String fromSelectedItem = (String)fromAccount.getSelectedItem(); // now we can acess the items
				String toSelectedItem = (String)ToAccount.getSelectedItem(); // now we can acess the items

				// When the two selected ACCOUNTS are not the same then we can perform the transaction!
				if(fromSelectedItem != toSelectedItem) {

					// make a call to decide which option to conduct the transfer on
					setupTransaction(fromSelectedItem,toSelectedItem,withdrawAmt);

				}

				else{
					// show message error both from and to cannot be the same
					JOptionPane.showMessageDialog(null,
							"ERROR: Can not transfer inbetween same account!.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnTransfer_1.setBounds(428, 241, 141, 35);
		contentPane.add(btnTransfer_1);
	}


	// set the objects make them void it is a Helper function for creating object when the user selcts the option
	public static void storeCheckingInfo(int accountID, double balance, String accountType, double serviceCharge){

		// Now set these for the checking account
		loggedCkAcct.setAccountID(accountID);
		loggedCkAcct.setBalance(balance);
		loggedCkAcct.setAccountType(accountType);
		loggedCkAcct.setServiceCharge(serviceCharge);
	}


	public static void storeSavingsInfo(int accountID, double balance, String accountType, double intrestRate) {
		// now set the values for this screen to use and display to the screen
		loggedSvAcct.setAccountID(accountID);
		loggedSvAcct.setBalance(balance);
		loggedSvAcct.setAccountType(accountType);
		loggedSvAcct.setIntrestRate(intrestRate);
	}

	public static void storeCreditInfo(int accountID, double balance, String accountType,
									   double creditLimt, double intrestRate) {
		loggedCrAcct.setAccountID(accountID);
		loggedCrAcct.setBalance(balance);
		loggedCrAcct.setAccountType(accountType);
		loggedCrAcct.setCreditLimt(creditLimt);
		loggedCrAcct.setIntrestRate(intrestRate);
	}

	// use this method to perform the transactions based on the items selected in the combox
	public static void setupTransaction(String fromSelectedItem, String toSelectedItem, JTextField withdrawAmt){

		credit = loggedCrAcct.getAccountType();
		savings =  loggedSvAcct.getAccountType();
		checking = loggedCkAcct.getAccountType();

		int withdraw = Integer.parseInt(withdrawAmt.getText());

		boolean result = false;
		// ---------------------------FROM_CHECKING---------------
		if(fromSelectedItem.equals(checking)) {

			if(toSelectedItem.equals(savings)){
				 result = transactionDAO.performTransaction("account",withdraw,loggedCkAcct.getBalance(),loggedCkAcct.getServiceCharge()
						                          ,loggedCkAcct.getAccountID(),"Inbetween Accounts",0.0,"account"
													,loggedSvAcct.getBalance(),loggedSvAcct.getAccountID(),loggedSvAcct.getAccountType());

			}

			if(toSelectedItem.equals(credit)){
				 result = transactionDAO.performTransaction("account",withdraw,loggedCkAcct.getBalance(),loggedCkAcct.getServiceCharge()
						,loggedCrAcct.getAccountID(),"Paid Credit loan",loggedCrAcct.getCreditLimt(),"account"
						,loggedCrAcct.getBalance(),loggedCrAcct.getAccountID(),loggedCrAcct.getAccountType());
			}

			if( result == true){
				JOptionPane.showMessageDialog(null,
						"Transaction Successful!.", "Transaction Window", JOptionPane.INFORMATION_MESSAGE);
			}

			else{
					JOptionPane.showMessageDialog(null,
							"Insufficient Funds! ", "Transaction Window", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		//--------------------------FROM SAVINGS------------------
		else if(fromSelectedItem.equals(savings)){

			if(toSelectedItem.equals(checking)){
				result = transactionDAO.performTransaction("account",withdraw,loggedSvAcct.getBalance(),loggedSvAcct.getIntrestRate()
						,loggedSvAcct.getAccountID(),"Inbetween Accounts",0.0,"account"
						,loggedCkAcct.getBalance(),loggedCkAcct.getAccountID(),loggedCkAcct.getAccountType());

			}

			if (toSelectedItem.equals(credit)){
				result = transactionDAO.performTransaction("account",withdraw,loggedSvAcct.getBalance(),loggedSvAcct.getIntrestRate()
						,loggedSvAcct.getAccountID(),"Paid Credit loan",loggedCrAcct.getCreditLimt(),"account"
						,loggedCrAcct.getBalance(),loggedCrAcct.getAccountID(),loggedCrAcct.getAccountType());

			}

			if( result == true){
				JOptionPane.showMessageDialog(null,
						"Transaction Successful!.", "Transaction Window", JOptionPane.INFORMATION_MESSAGE);
			}

			else{
				JOptionPane.showMessageDialog(null,
						"Insufficient Funds! ", "Transaction Window", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		// now it is credit since noe of the other expressions were true
		// ---------------------------FROM LOAN------------------------
		else if(fromSelectedItem.equals(credit)) {

			if(toSelectedItem.equals(checking)){
				result = transactionDAO.performTransaction("account",withdraw,loggedCrAcct.getBalance(),loggedCrAcct.getIntrestRate()
						,loggedCrAcct.getAccountID(),"Credit Account Transaction To Checking",0.0,"account"
						,loggedCkAcct.getBalance(),loggedCkAcct.getAccountID(),loggedCkAcct.getAccountType());

			}

			if (toSelectedItem.equals(savings)){
				result = transactionDAO.performTransaction("account",withdraw,loggedCrAcct.getBalance(),loggedCrAcct.getIntrestRate()
						,loggedCrAcct.getAccountID(),"Credit Account Transaction To Savings",0.0,"account"
						,loggedSvAcct.getBalance(),loggedSvAcct.getAccountID(),loggedSvAcct.getAccountType());

			}

			if( result == true){
				JOptionPane.showMessageDialog(null,
						"Transaction Successful!.", "Transaction Window", JOptionPane.INFORMATION_MESSAGE);
			}

			else{
				JOptionPane.showMessageDialog(null,
						"Insufficient Funds! ", "Transaction Window", JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}
}


