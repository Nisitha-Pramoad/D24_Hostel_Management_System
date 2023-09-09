package lk.ijse.D24_Hostel_Management_System.repository;

import lk.ijse.D24_Hostel_Management_System.entity.Room;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends CrudRepository<Room, String>{

    void setSession(Session session);

    List<Room> findAvailableRooms(String roomType, LocalDate startDate, LocalDate endDate);



}
