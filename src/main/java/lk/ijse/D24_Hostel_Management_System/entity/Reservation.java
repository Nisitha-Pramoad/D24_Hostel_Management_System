package lk.ijse.D24_Hostel_Management_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "reservation_id", nullable = false, length = 50)
    private String reservationId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

    private LocalDate reservationDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private String status;

    @OneToMany(mappedBy = "reservation")
    private List<Payment> payments;

    // Constructors, getters, setters...
}
