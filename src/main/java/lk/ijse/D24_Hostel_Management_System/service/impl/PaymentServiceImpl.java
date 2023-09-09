package lk.ijse.D24_Hostel_Management_System.service.impl;

import lk.ijse.D24_Hostel_Management_System.config.SessionFactoryConfiguration;
import lk.ijse.D24_Hostel_Management_System.dto.PaymentDto;
import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import lk.ijse.D24_Hostel_Management_System.dto.YourMapperClass;
import lk.ijse.D24_Hostel_Management_System.entity.Payment;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lk.ijse.D24_Hostel_Management_System.repository.PaymentRepository;
import lk.ijse.D24_Hostel_Management_System.repository.StudentRepository;
import lk.ijse.D24_Hostel_Management_System.repository.impl.PaymentRepositoryImpl;
import lk.ijse.D24_Hostel_Management_System.repository.impl.StudentRepositoryImpl;
import lk.ijse.D24_Hostel_Management_System.service.PaymentService;
import lk.ijse.D24_Hostel_Management_System.tdm.PaymentHistoryTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

// PaymentServiceImpl.java
//@Service
public class PaymentServiceImpl implements PaymentService {

    private static PaymentServiceImpl paymentService;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl() {
        paymentRepository = new PaymentRepositoryImpl();
    }

    public static PaymentServiceImpl getInstance() {
        return null == paymentService ? paymentService = new PaymentServiceImpl() : paymentService;
    }

    @Override
    public StudentDto findStudentByIdOrNationalId(String studentIdOrNationalId) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        try {
            paymentRepository.setSession(session);
            Student student = paymentRepository.findStudentByIdOrNationalId(studentIdOrNationalId);

            if (student != null) {
                // Map the Student entity to a StudentDto if found (you need a mapper for this)
                return YourMapperClass.mapToDto(student);
            }
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String savePayment(PaymentDto paymentDto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            paymentRepository.setSession(session);
            String isSavedPaymentId = paymentRepository.save(paymentDto.toEntity());
            transaction.commit();
            session.close();
            return isSavedPaymentId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updatePayment(PaymentDto paymentDto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            paymentRepository.setSession(session);
            boolean isUpdated = paymentRepository.update(paymentDto.toEntity());
            transaction.commit();
            session.close();
            return isUpdated;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePayment(String paymentId) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            paymentRepository.setSession(session);
            Payment payment = paymentRepository.findById(paymentId);

            if (payment != null) {
                paymentRepository.delete(payment);
                transaction.commit();
                session.close();
                return true;
            } else {
                transaction.rollback();
                session.close();
                return false; // Payment not found
            }
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false; // Error occurred during deletion
        }
    }

    @Override
    public List<PaymentHistoryTM> loadAllPayments() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        try {
            paymentRepository.setSession(session);
            List<Payment> payments = paymentRepository.findAll();

            // Map Payment entities to PaymentHistoryTM DTOs if payments are found (you need a mapper for this)
            if (!payments.isEmpty()) {
                return YourMapperClass.mapToPaymentHistoryTMList(payments);
            }
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return Collections.emptyList(); // Return an empty list if no payments are found or an error occurs
    }

}
