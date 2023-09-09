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
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_id", nullable = false, length = 50)
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
    private Reservation reservation;

    private LocalDate paymentDate;
    private double amount;
    private String paymentMethod;

    // Constructors, getters, setters...
}
