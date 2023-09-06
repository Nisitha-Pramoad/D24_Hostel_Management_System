package lk.ijse.D24_Hostel_Management_System.dto;

import lk.ijse.D24_Hostel_Management_System.embedded.NameIdentifier;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

public class StudentDto {

    private String studentId;

    private NameIdentifier nameIdentifier;

    private LocalDate dateOfBirth;

    private String nationalId;

    private String gender;

    private String address;

    private String contact;

    private String email;

    private String campusName;

    private double keymoney;

    private boolean acceptCondions;

    private Timestamp createdDateTime;

    public StudentDto() {
    }

    public StudentDto(String studentId, NameIdentifier nameIdentifier, LocalDate dateOfBirth, String nationalId, String gender, String address, String contact, String email, String campusName, double keymoney, boolean acceptCondions, Timestamp createdDateTime) {
        this.studentId = studentId;
        this.nameIdentifier = nameIdentifier;
        this.dateOfBirth = dateOfBirth;
        this.nationalId = nationalId;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.campusName = campusName;
        this.keymoney = keymoney;
        this.acceptCondions = acceptCondions;
        this.createdDateTime = createdDateTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public NameIdentifier getNameIdentifier() {
        return nameIdentifier;
    }

    public void setNameIdentifier(NameIdentifier nameIdentifier) {
        this.nameIdentifier = nameIdentifier;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public double getKeymoney() {
        return keymoney;
    }

    public void setKeymoney(double keymoney) {
        this.keymoney = keymoney;
    }

    public boolean isAcceptCondions() {
        return acceptCondions;
    }

    public void setAcceptCondions(boolean acceptCondions) {
        this.acceptCondions = acceptCondions;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "studentId='" + studentId + '\'' +
                ", nameIdentifier=" + nameIdentifier +
                ", dateOfBirth=" + dateOfBirth +
                ", nationalId='" + nationalId + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", campusName='" + campusName + '\'' +
                ", keymoney=" + keymoney +
                ", acceptCondions=" + acceptCondions +
                ", createdDateTime=" + createdDateTime +
                '}';
    }

    public Student toEntity() {
        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setNameIdentifier(this.nameIdentifier);
        student.setDateOfBirth(this.dateOfBirth);
        student.setNationalId(this.nationalId);
        student.setGender(this.gender);
        student.setAddress(this.address);
        student.setContact(this.contact);
        student.setEmail(this.email);
        student.setCampusName(this.campusName);
        student.setKeymoney(this.keymoney);
        student.setAcceptCondions(this.acceptCondions);
        student.setCreatedDateTime(this.createdDateTime);
        return student;
    }
}
