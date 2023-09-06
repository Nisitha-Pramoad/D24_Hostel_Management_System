package lk.ijse.D24_Hostel_Management_System.repository.impl;

import lk.ijse.D24_Hostel_Management_System.config.SessionFactoryConfiguration;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lk.ijse.D24_Hostel_Management_System.repository.StudentRepository;
import lk.ijse.D24_Hostel_Management_System.tdm.StudentTM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.Serializable;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private Session session;

    public StudentRepositoryImpl(){

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String saveStudent(Student student) {
        return (String) session.save(student);
    }

    @Override
    public Student findById(String s) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(Student entity) {

    }

    @Override
    public String getMaxStudentId() {
        try {
            Query<String> query = session.createQuery("SELECT MAX(studentId) FROM Student", String.class);
            List<String> result = query.getResultList();
            System.out.println(result.get(0));
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the last student ID");
        }
    }





}
