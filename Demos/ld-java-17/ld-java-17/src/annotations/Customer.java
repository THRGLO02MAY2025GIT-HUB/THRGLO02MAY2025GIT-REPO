package annotations;
// Mark this model for JsonSerialization
@JsonSerializable
public class Customer {
    // JSON Element 1
    @JsonElement
    private String firstName;
    // JSON Element 2
    @JsonElement
    private String lastName;
    // JSON Element 3 // To be successfulTransactionFormatted
    // "S100"
    @JsonElement(key="successfulTransactionFormatted")
    private String noOfSuccessfulTransactions;
    // Not necessary to be serialized
    private String language;
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

    public String getNoOfSuccessfulTransactions() {
        return noOfSuccessfulTransactions;
    }

    public void setNoOfSuccessfulTransactions(String noOfSuccessfulTransactions) {
        this.noOfSuccessfulTransactions = noOfSuccessfulTransactions;
    }

    public Customer(String firstName, String lastName, String noOfSuccessfulTransactions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.noOfSuccessfulTransactions = noOfSuccessfulTransactions;
    }

    // Logic for proper casing
    // arun > Arun
@Init
    private void initProperCase() {
        this.firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
        this.lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
    }
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", noOfSuccessfulTransactions='" + noOfSuccessfulTransactions + '\'' +
                '}';
    }
}
