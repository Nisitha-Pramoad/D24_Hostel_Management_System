package lk.ijse.D24_Hostel_Management_System.repository;

import lk.ijse.D24_Hostel_Management_System.entity.Payment;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import org.hibernate.Session;

public interface PaymentRepository extends CrudRepository<Payment, String>{

    void setSession(Session session);

    Student findStudentByIdOrNationalId(String studentIdOrNationalId);


}
