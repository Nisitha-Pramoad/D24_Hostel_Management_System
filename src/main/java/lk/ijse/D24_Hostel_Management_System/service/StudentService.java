package lk.ijse.D24_Hostel_Management_System.service;

import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lk.ijse.D24_Hostel_Management_System.tdm.StudentTM;

import java.util.List;

public interface StudentService {

    String saveStudent(StudentDto student);


    String getGenerateStudentId();
}
