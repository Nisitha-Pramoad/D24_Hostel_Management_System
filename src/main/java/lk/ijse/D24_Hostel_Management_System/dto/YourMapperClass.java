package lk.ijse.D24_Hostel_Management_System.dto;

import lk.ijse.D24_Hostel_Management_System.embedded.NameIdentifier;
import lk.ijse.D24_Hostel_Management_System.entity.Student;

public class YourMapperClass {
    public static StudentDto mapToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(student.getStudentId());

        // Map NameIdentifier to the DTO
        NameIdentifier nameIdentifier = new NameIdentifier();
        nameIdentifier.setFirstName(student.getFirstName()); // Use the actual field name from your entity
        nameIdentifier.setLastName(student.getLastName());   // Use the actual field name from your entity
        studentDto.setNameIdentifier(nameIdentifier);

        // Map other fields from entity to DTO
        studentDto.setDateOfBirth(student.getDateOfBirth());
        studentDto.setNationalId(student.getNationalId());
        studentDto.setGender(student.getGender());
        studentDto.setAddress(student.getAddress());
        studentDto.setContact(student.getContact());
        studentDto.setEmail(student.getEmail());
        studentDto.setCampusName(student.getCampusName());
        studentDto.setKeymoney(student.getKeymoney());
        studentDto.setAcceptCondions(student.isAcceptCondions());
        studentDto.setCreatedDateTime(student.getCreatedDateTime());

        return studentDto;
    }

    public static Student mapToEntity(StudentDto studentDto, Student existingStudent) {
        // Map relevant fields from studentDto to existingStudent entity
        existingStudent.setStudentId(studentDto.getStudentId());

        // Map NameIdentifier from DTO to the entity
        NameIdentifier nameIdentifier = studentDto.getNameIdentifier();
        if (nameIdentifier != null) {
            existingStudent.setFirstName(nameIdentifier.getFirstName());
            existingStudent.setLastName(nameIdentifier.getLastName());
        }

        // Map other fields from DTO to entity
        existingStudent.setDateOfBirth(studentDto.getDateOfBirth());
        existingStudent.setNationalId(studentDto.getNationalId());
        existingStudent.setGender(studentDto.getGender());
        existingStudent.setAddress(studentDto.getAddress());
        existingStudent.setContact(studentDto.getContact());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.setCampusName(studentDto.getCampusName());
        existingStudent.setKeymoney(studentDto.getKeymoney());
        existingStudent.setAcceptCondions(studentDto.isAcceptCondions());
        existingStudent.setCreatedDateTime(studentDto.getCreatedDateTime());

        return existingStudent;
    }
}
