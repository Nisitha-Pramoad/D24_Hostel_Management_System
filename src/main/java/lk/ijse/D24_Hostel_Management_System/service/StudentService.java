package lk.ijse.D24_Hostel_Management_System.service;

import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import java.util.List;



public interface StudentService {
    String saveStudent(StudentDto student);
    boolean updateStudent(StudentDto student);
    boolean deleteStudent(String studentId);
    String getGenerateStudentId();
    List<StudentDto> getAllStudents(); // You might want to add this method
}

