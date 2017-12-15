package DomainClasses;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TransactionTableModel extends AbstractTableModel{

    public static final int OBJECT_COL = -1;
    private static final int DATE = 0;
    private static final int TRANSACTION_DESCRIPTION = 1;
    private static final int WITHDRAW = 2;
    private static final int DEPOSIT = 3;
    private static final int BALANCE = 4;

    // create the array of coloumn names to display when called
    private String[] columnNames = { "Date", "Transaction Desrip", "Withdraw",
            "Deposit", "Balance" };
    private List<Transaction> transaction;

    public TransactionTableModel(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    @Override
    public int getRowCount() {
        return transaction.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Transaction tempTrans = transaction.get(row);
        String message = "ERROR: NO DATA FOUND";

        switch(col){
            case DATE:
                return tempTrans.getDateOfTransaction();
            case TRANSACTION_DESCRIPTION :
                return tempTrans.getTransactionDescription();
            case WITHDRAW :
                return tempTrans.getWithdraw();
            case DEPOSIT :
                return tempTrans.getDeposit();
            case  BALANCE :
                return tempTrans.getBalance();
            default:
                return message;
        }
    }

}
