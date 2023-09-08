package lk.ijse.D24_Hostel_Management_System.repository.impl;

import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lk.ijse.D24_Hostel_Management_System.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String save(Student student) {
        return (String) session.save(student);
    }

    @Override
    public Student findById(String studentId) {
        return session.get(Student.class, studentId);
    }

    @Override
    public List<Student> findAll() {
        String hqlQuery = "FROM Student"; // Assuming "Student" is your entity class name
        Query<Student> query = session.createQuery(hqlQuery, Student.class);
        List<Student> studentList = query.list();
        session.close();

        // Print the student data
        for (Student student : studentList) {
            System.out.println("Student ID: " + student.getStudentId());
            // Add more fields as needed
            System.out.println("----------------------------------");
        }

        return studentList;
    }



    @Override
    public boolean update(Student student) {
        try {
            session.merge(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Student student) {
        try {
            session.delete(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getMaxStudentId() {
        try {
            Query<String> query = session.createQuery("SELECT MAX(studentId) FROM Student", String.class);
            List<String> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch the last student ID");
        }
    }
}
