package lk.ijse.D24_Hostel_Management_System.repository;

import lk.ijse.D24_Hostel_Management_System.entity.Room;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import org.hibernate.Session;

public interface RoomRepository extends CrudRepository<Room, String>{

    void setSession(Session session);
}
