import java.io.Serializable;

public class Entry implements Serializable{
    private String firstName, lastName, phoneNumber, userEmail;

    public Entry() {

    }

    public Entry(String firstName, String lastName, String phoneNumber, String userEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
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


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getUserEmail() {
        return userEmail;
    }


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    @Override
    public String toString() {
        return "Entry [firstName=" + firstName + ", lastName=" + lastName + ", userEmail=" + userEmail + ", phoneNumber="
                + phoneNumber + "]";
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
