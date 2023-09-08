package lk.ijse.D24_Hostel_Management_System.tdm;

import lk.ijse.D24_Hostel_Management_System.embedded.NameIdentifier;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

public class StudentTM implements Comparable<StudentTM> {

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

    // Constructor
    public StudentTM(String studentId, NameIdentifier nameIdentifier, LocalDate dateOfBirth, String nationalId, String gender, String address, String contact, String email, String campusName, double keymoney, boolean acceptCondions, Timestamp createdDateTime) {
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

    public StudentTM() {

    }

    // Getters and setters
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

    public boolean isAcceptConditions() {
        return acceptCondions;
    }

    public void setAcceptConditions(boolean acceptConditions) {
        this.acceptCondions = acceptConditions;
    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    // Additional getter methods for firstName and lastName
    public String getFirstName() {
        return nameIdentifier != null ? nameIdentifier.getFirstName() : null;
    }

    public String getLastName() {
        return nameIdentifier != null ? nameIdentifier.getLastName() : null;
    }

    public String getNameIdentifierLastName() {
        return nameIdentifier.getLastName();
    }

    public String getNameIdentifierFirstName() {
        return nameIdentifier.getFirstName();
    }

    @Override
    public String toString() {
        return "StudentTM{" +
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
                ", acceptConditions=" + acceptCondions +
                ", createdDateTime=" + createdDateTime +
                '}';
    }

    @Override
    public int compareTo(StudentTM o) {
        return studentId.compareTo(o.getStudentId());
    }
}
