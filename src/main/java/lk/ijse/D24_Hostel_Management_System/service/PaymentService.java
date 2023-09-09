package lk.ijse.D24_Hostel_Management_System.service;

import lk.ijse.D24_Hostel_Management_System.dto.PaymentDto;
import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import lk.ijse.D24_Hostel_Management_System.tdm.PaymentHistoryTM;

import java.util.List;

public interface PaymentService {
    StudentDto findStudentByIdOrNationalId(String studentIdOrNationalId);

    String savePayment(PaymentDto paymentDto);
    boolean updatePayment(PaymentDto paymentDto);
    boolean deletePayment(String paymentId);
    List<PaymentHistoryTM> loadAllPayments();
}
