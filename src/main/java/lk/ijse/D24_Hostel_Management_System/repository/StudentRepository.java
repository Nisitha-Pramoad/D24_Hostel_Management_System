package lk.ijse.D24_Hostel_Management_System.repository;

import lk.ijse.D24_Hostel_Management_System.entity.Student;
import org.hibernate.Session;

public interface StudentRepository extends CrudRepository<Student, String>{

    void setSession(Session session);

    String getMaxStudentId();
}
