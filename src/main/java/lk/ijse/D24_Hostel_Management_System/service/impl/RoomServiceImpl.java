package lk.ijse.D24_Hostel_Management_System.service.impl;

import lk.ijse.D24_Hostel_Management_System.config.SessionFactoryConfiguration;
import lk.ijse.D24_Hostel_Management_System.dto.RoomDto;
import lk.ijse.D24_Hostel_Management_System.repository.RoomRepository;
import lk.ijse.D24_Hostel_Management_System.repository.impl.RoomRepositoryImpl;
import lk.ijse.D24_Hostel_Management_System.service.RoomService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoomServiceImpl implements RoomService {

    private static RoomServiceImpl roomService;
    private final RoomRepository roomRepository;

    public RoomServiceImpl() {
        roomRepository = (RoomRepository) new RoomRepositoryImpl();
    }

    public static RoomServiceImpl getInstance() {
        return null == roomService ? roomService = new RoomServiceImpl() : roomService;
    }

    @Override
    public String saveRoom(RoomDto roomDto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            String isSavedStudentId = roomRepository.save(roomDto.toEntity());
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
    public String updateRoom(RoomDto roomDto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            String isSavedStudentId = roomRepository.update(roomDto.toEntity());
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
    public String getGenerateRoomId() {
        return null;
    }
}
