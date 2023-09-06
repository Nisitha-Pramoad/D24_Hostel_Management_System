package lk.ijse.D24_Hostel_Management_System.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class NameIdentifier {

    private String firstName;
    private String lastName;

    public NameIdentifier() {
    }

    public NameIdentifier(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFullName() {
        // Combine the first name, middle name (if available), and last name
        StringBuilder fullName = new StringBuilder(firstName);
        fullName.append(" ").append(lastName);
        return fullName.toString();
    }

    @Override
    public String toString() {
        return "NameIdentifier{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
