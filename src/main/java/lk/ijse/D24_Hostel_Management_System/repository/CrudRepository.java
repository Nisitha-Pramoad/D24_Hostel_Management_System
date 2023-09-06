package lk.ijse.D24_Hostel_Management_System.repository;

import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lk.ijse.D24_Hostel_Management_System.tdm.StudentTM;
import org.hibernate.Session;

import java.util.List;

public interface CrudRepository<T, ID> {

    ID saveStudent(T student);
    T findById(ID id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);


}
