package lk.ijse.D24_Hostel_Management_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
@Table(name = "payment_history")
public class PaymentHistory {
    @Id
    @Column(name = "payment_history_id", nullable = false, length = 50)
    private String paymentHistoryId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    private LocalDate paymentDate;
    private double amount;
    private String paymentMethod;

    // Constructors, getters, setters...
}
