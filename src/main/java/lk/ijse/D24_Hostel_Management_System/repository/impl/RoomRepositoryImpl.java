package lk.ijse.D24_Hostel_Management_System.repository.impl;

import lk.ijse.D24_Hostel_Management_System.entity.Room;
import lk.ijse.D24_Hostel_Management_System.repository.RoomRepository;
import org.hibernate.Session;

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
    public Room findById(String s) {
        return null;
    }

    @Override
    public List<Room> findAll() {
        return null;
    }

    @Override
    public boolean update(Room room) {
        //return (String) session.save(room);
        return false;
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






}
