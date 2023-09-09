package lk.ijse.D24_Hostel_Management_System.repository.impl;

import lk.ijse.D24_Hostel_Management_System.entity.Payment;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lk.ijse.D24_Hostel_Management_System.repository.PaymentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {
    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String save(Payment entity) {
        try {
            return (String) session.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Payment findById(String s) {
        return session.get(Payment.class, s);
    }

    @Override
    public List<Payment> findAll() {
        return session.createQuery("FROM Payment", Payment.class).list();
    }

    @Override
    public boolean update(Payment entity) {
        try {
            session.merge(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(Payment entity) {
        try {
            session.delete(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student findStudentByIdOrNationalId(String studentIdOrNationalId) {
        try {
            Query<Student> query = session.createQuery(
                    "FROM Student WHERE studentId = :id OR nationalId = :id",
                    Student.class
            );
            query.setParameter("id", studentIdOrNationalId);
            return query.uniqueResult();
        } catch (Exception ex) {
            // Handle exceptions, log, or rethrow as needed
            ex.printStackTrace();
            return null; // Return null if not found or an error occurs
        }
    }
}
