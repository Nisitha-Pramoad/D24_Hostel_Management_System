package lk.ijse.D24_Hostel_Management_System.service.impl;

import lk.ijse.D24_Hostel_Management_System.config.SessionFactoryConfiguration;
import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import lk.ijse.D24_Hostel_Management_System.repository.StudentRepository;
import lk.ijse.D24_Hostel_Management_System.repository.impl.StudentRepositoryImpl;
import lk.ijse.D24_Hostel_Management_System.service.StudentService;
import lk.ijse.D24_Hostel_Management_System.tdm.StudentTM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static StudentServiceImpl studentService;
    private final StudentRepository studentRepository;
    
    public StudentServiceImpl(){
        studentRepository = (StudentRepository) new StudentRepositoryImpl();
    }

    public static StudentServiceImpl getInstance() {
        return null == studentService ? studentService = new StudentServiceImpl() : studentService;
    }

    @Override
    public String saveStudent(StudentDto student) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepository.setSession(session);
            String isSavedStudentId = studentRepository.saveStudent(student.toEntity());
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



}
