package lk.ijse.D24_Hostel_Management_System.dto;

import lk.ijse.D24_Hostel_Management_System.entity.Payment;
import lk.ijse.D24_Hostel_Management_System.entity.Reservation;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class PaymentDto {

    private String paymentId;
    private Reservation reservation;
    private LocalDate paymentDate;
    private double amount;
    private String paymentMethod;
    private String month;
    private StudentDto student;

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public Payment toEntity() {
        Payment payment = new Payment();
        payment.setPaymentId(this.paymentId);
        payment.setReservation(this.reservation);
        payment.setPaymentDate(this.paymentDate);
        payment.setAmount(this.amount);
        payment.setPaymentMethod(this.paymentMethod);
        payment.setMonth(this.month);
        return payment;
    }

}
