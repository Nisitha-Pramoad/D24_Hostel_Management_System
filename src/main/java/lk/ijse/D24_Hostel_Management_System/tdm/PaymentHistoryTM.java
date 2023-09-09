package lk.ijse.D24_Hostel_Management_System.tdm;

import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PaymentHistoryTM {

    private String paymentHistoryId;
    private Student student;
    private LocalDate paymentDate;
    private double amount;
    private String paymentMethod;


    // Setter method for studentId
    public void setStudentId(String studentId) {
        // Set the studentId property here
        // You can choose to set it to the student object or store it separately
        // For example, you can set it to the student object like this:
        // this.student.setStudentId(studentId);
    }

    // Getter method for studentName (if needed)
    public String getStudentName() {
        return student.getNameIdentifier().getFullName();
    }

    // Getter method for studentId (if needed)
    public String getStudentId() {
        if (student != null) {
            return student.getStudentId();
        }
        return ""; // or return null; depending on your requirements
    }

    public void setStudentName(String fullName) {

    }

    // Getter for paymentHistoryId
    public String getPaymentHistoryId() {
        return paymentHistoryId;
    }

    // Setter for paymentHistoryId
    public void setPaymentHistoryId(String paymentHistoryId) {
        this.paymentHistoryId = paymentHistoryId;
    }
}
