package lk.ijse.D24_Hostel_Management_System.entity;

import lk.ijse.D24_Hostel_Management_System.embedded.NameIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Tables;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
@Table(name = "student")
public class Student {

    @Id
    /*@GeneratedValue(strategy = GenerationType.AUTO)*/
    @Column(name = "student_id", nullable = false, length = 50)
    private String studentId;

    private NameIdentifier nameIdentifier;

    private LocalDate dateOfBirth;

    private String nationalId;

    private String gender;

    private String address;

    private String contact;

    private String email;

    private String campusName;

    private double keymoney;

    private boolean acceptCondions;

    @CreationTimestamp
    private Timestamp createdDateTime;

    @OneToMany(mappedBy = "student")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "student")
    private List<PaymentHistory> paymentHistories;

    public String getFirstName() {
        if (nameIdentifier != null) {
            return nameIdentifier.getFirstName();
        } else {
            return null; // or a default value if needed
        }
    }

    public String getLastName() {
        if (nameIdentifier != null) {
            return nameIdentifier.getLastName();
        } else {
            return null; // or a default value if needed
        }
    }

    public void setFirstName(String firstName) {
        if (nameIdentifier == null) {
            nameIdentifier = new NameIdentifier();
        }
        nameIdentifier.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        if (nameIdentifier == null) {
            nameIdentifier = new NameIdentifier();
        }
        nameIdentifier.setLastName(lastName);
    }

}
