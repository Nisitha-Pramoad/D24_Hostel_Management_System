package lk.ijse.D24_Hostel_Management_System.service.impl;

import lk.ijse.D24_Hostel_Management_System.config.SessionFactoryConfiguration;
import lk.ijse.D24_Hostel_Management_System.dto.RoomDto;
import lk.ijse.D24_Hostel_Management_System.entity.Room;
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
            String isUpdateStudentId = roomRepository.update(roomDto.toEntity());
            transaction.commit();
            session.close();
            return isUpdateStudentId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteRoom(String roomId) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomRepository.setSession(session);

            // Check if the room exists
            Room room = roomRepository.findById(roomId);

            if (room != null) {
                // Call the repository method to delete the room
                boolean isDeleted = roomRepository.delete(room);
                transaction.commit();
                session.close();
                return isDeleted;
            } else {
                transaction.rollback();
                session.close();
                return false; // Room not found
            }
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false; // Deletion failed
        }
    }




    @Override
    public String getGenerateRoomId() {
        return null;
    }
}
