package lk.ijse.D24_Hostel_Management_System.repository;

import java.util.List;

public interface CrudRepository<T, ID> {
    ID save(T entity);
    T findById(ID id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(T entity);
}
