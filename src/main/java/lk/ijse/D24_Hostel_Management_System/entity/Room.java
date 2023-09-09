package lk.ijse.D24_Hostel_Management_System.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString


@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id", nullable = false, length = 50)
    private String roomId;

    private String roomType;

    private double pricing;

    private double roomSize;

    private int maximumOccupancy; // Corrected the attribute name

    private String amenitiesAndFeatures; // Corrected the attribute name

    private String roomStatus;

    @CreationTimestamp
    private Timestamp createdDateTime;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

}
