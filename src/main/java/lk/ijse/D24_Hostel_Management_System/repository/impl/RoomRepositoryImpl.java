package lk.ijse.D24_Hostel_Management_System.repository.impl;

import lk.ijse.D24_Hostel_Management_System.entity.Room;
import lk.ijse.D24_Hostel_Management_System.repository.RoomRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String save(Room room) {
        return (String) session.save(room);
    }

    @Override
    public Room findById(String roomId) {
        return session.get(Room.class, roomId);
    }

    @Override
    public List<Room> findAll() {
        Query<Room> query = session.createQuery("FROM Room", Room.class);
        return query.list();
    }

    @Override
    public boolean update(Room room) {
        try {
            session.update(room);
            return true; // Update successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Update failed
        }
    }

    @Override
    public boolean delete(Room room) {
        try {
            session.delete(room);
            return true; // Deletion successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Deletion failed
        }
    }

    @Override
    public List<Room> findAvailableRooms(String roomType, LocalDate startDate, LocalDate endDate) {
        Query<Room> query = session.createQuery("SELECT r FROM Room r WHERE r.roomType = :roomType " +
                "AND r.roomId NOT IN (SELECT res.room.roomId FROM Reservation res " +
                "WHERE res.checkInDate <= :endDate AND res.checkOutDate >= :startDate)", Room.class);
        query.setParameter("roomType", roomType);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return query.getResultList();
    }







}
