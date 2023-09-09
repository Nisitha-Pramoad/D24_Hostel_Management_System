package lk.ijse.D24_Hostel_Management_System.service.impl;

import lk.ijse.D24_Hostel_Management_System.config.SessionFactoryConfiguration;
import lk.ijse.D24_Hostel_Management_System.dto.RoomDto;
import lk.ijse.D24_Hostel_Management_System.entity.Room;
import lk.ijse.D24_Hostel_Management_System.repository.RoomRepository;
import lk.ijse.D24_Hostel_Management_System.repository.impl.RoomRepositoryImpl;
import lk.ijse.D24_Hostel_Management_System.service.RoomService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    private static RoomServiceImpl roomService;
    private final RoomRepository roomRepository;

    private RoomServiceImpl() {
        roomRepository = new RoomRepositoryImpl();
    }

    public static RoomServiceImpl getInstance() {
        if (roomService == null) {
            roomService = new RoomServiceImpl();
        }
        return roomService;
    }

    @Override
    @Transactional
    public boolean isRoomAvailable(String roomType, LocalDate startDate, LocalDate endDate) {
        // Implement logic to check room availability
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        try {
            roomRepository.setSession(session);

            // Check if there are any reservations that overlap with the specified date range
            List<Room> rooms = roomRepository.findAvailableRooms(roomType, startDate, endDate);

            // If no overlapping reservations are found, the room is available
            return !rooms.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // An error occurred while checking availability
        } finally {
            session.close();
        }
    }

    @Override
    public String saveRoom(RoomDto roomDto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            String isSavedRoomId = roomRepository.save(roomDto.toEntity());
            transaction.commit();
            session.close();
            return isSavedRoomId;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateRoom(RoomDto roomDto) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            Room existingRoom = roomRepository.findById(roomDto.getRoomId());
            if (existingRoom == null) {
                // Handle the case where the room with the given ID doesn't exist
                transaction.rollback();
                return false;
            }

            // Update the room entity with the new data from the DTO
            existingRoom.setRoomType(roomDto.getRoomType());
            existingRoom.setPricing(roomDto.getPricing());
            existingRoom.setRoomSize(roomDto.getRoomSize());
            existingRoom.setMaximumOccupancy(roomDto.getMaximumOccupency());
            existingRoom.setAmenitiesAndFeatures(roomDto.getArmentiesAndFeatures());
            existingRoom.setRoomStatus(roomDto.getRoomStatus());

            // Save the updated room entity
            roomRepository.update(existingRoom);

            transaction.commit();
            session.close();
            return true; // Update successful
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false; // Update failed
        }
    }

    @Override
    public boolean deleteRoom(String roomId) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roomRepository.setSession(session);
            Room existingRoom = roomRepository.findById(roomId);
            if (existingRoom == null) {
                // Handle the case where the room with the given ID doesn't exist
                transaction.rollback();
                return false;
            }

            // Delete the room entity
            roomRepository.delete(existingRoom);

            transaction.commit();
            session.close();
            return true; // Deletion successful
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false; // Deletion failed
        }
    }


    @Override
    public RoomDto findRoomById(String roomId) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        try {
            roomRepository.setSession(session);
            Room room = roomRepository.findById(roomId);
            return RoomDto.fromEntity(room);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<RoomDto> getAllRooms() {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        try {
            roomRepository.setSession(session);
            List<Room> rooms = roomRepository.findAll();
            List<RoomDto> roomDtos = new ArrayList<>();
            for (Room room : rooms) {
                roomDtos.add(RoomDto.fromEntity(room));
            }
            return roomDtos;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }


    @Override
    public String generateRoomId() {
        // Implement the generateRoomId method
        return null;
    }
}

