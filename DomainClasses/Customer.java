package DomainClasses;

/**
 * Created by arnav
 */
public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String password;
    private String username;

    public Customer(){
    }

    public Customer(int customerID, String password, String username, String firstName, String lastName) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }
    public Customer(String userName, String password){
        this.username = userName;
        this.password = password;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
