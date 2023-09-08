package lk.ijse.D24_Hostel_Management_System.service.impl;

import lk.ijse.D24_Hostel_Management_System.config.SessionFactoryConfiguration;
import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import lk.ijse.D24_Hostel_Management_System.dto.YourMapperClass;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lk.ijse.D24_Hostel_Management_System.repository.StudentRepository;
import lk.ijse.D24_Hostel_Management_System.repository.impl.StudentRepositoryImpl;
import lk.ijse.D24_Hostel_Management_System.service.StudentService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static StudentServiceImpl studentService;
    private final StudentRepository studentRepository;

    public StudentServiceImpl() {
        studentRepository = new StudentRepositoryImpl();
    }

    public static StudentServiceImpl getInstance() {
        return null == studentService ? studentService = new StudentServiceImpl() : studentService;
    }

    @Override
    public String saveStudent(StudentDto studentDto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentRepository.setSession(session);
            String isSavedStudentId = studentRepository.save(studentDto.toEntity());
            transaction.commit();
            session.close();
            return isSavedStudentId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentRepository.setSession(session);

            // Check if the student exists
            Student existingStudent = studentRepository.findById(studentDto.getStudentId());

            if (existingStudent != null) {
                // Update the student entity with the new data
                Student updatedStudent = studentDto.toEntity(); // Convert the DTO to an entity
                updatedStudent.setStudentId(existingStudent.getStudentId()); // Set the ID to the existing student's ID
                studentRepository.update(updatedStudent);

                transaction.commit();
                session.close();
                return true;
            } else {
                transaction.rollback();
                session.close();
                return false; // Student not found
            }
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false; // Update failed
        }
    }


    @Override
    public boolean deleteStudent(String studentId) {
        // This is a basic implementation. You should decide whether you want to soft delete or hard delete
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentRepository.setSession(session);

            // Check if the student exists
            Student student = studentRepository.findById(studentId);

            if (student != null) {
                // Call the repository method to delete the student
                boolean isDeleted = studentRepository.delete(student);
                transaction.commit();
                session.close();
                return isDeleted;
            } else {
                transaction.rollback();
                session.close();
                return false; // Student not found
            }
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false; // Deletion failed
        }
    }

    @Override
    public String getGenerateStudentId() {
        try {
            // Fetch the maximum student ID from the repository
            String maxStudentId = studentRepository.getMaxStudentId();

            if (maxStudentId == null) {
                // No existing student IDs, return the first one
                return "S0001";
            }

            // Increment the numeric part of the student ID
            int numericPart = Integer.parseInt(maxStudentId.substring(1)); // Extract numeric part
            numericPart++; // Increment
            String newNumericPart = String.format("%04d", numericPart); // Format to 4 digits

            return "S" + newNumericPart;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate student ID");
        }
    }


    @Override
    public List<StudentDto> getAllStudents() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        studentRepository.setSession(session);

        // Fetch student data from your StudentRepository
        List<Student> studentList = studentRepository.findAll();

        // Convert Student entities to DTOs
        List<StudentDto> studentDtoList = new ArrayList<>();

        for (Student student : studentList) {
            studentDtoList.add(YourMapperClass.mapToDto(student)); // Implement YourMapperClass
        }

        session.close();
        return studentDtoList;
    }

}
