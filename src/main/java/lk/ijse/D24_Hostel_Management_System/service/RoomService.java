package lk.ijse.D24_Hostel_Management_System.service;

import lk.ijse.D24_Hostel_Management_System.dto.RoomDto;
import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    String saveRoom(RoomDto roomDto);

    boolean updateRoom(RoomDto roomDto);

    boolean deleteRoom(String roomId);

    RoomDto findRoomById(String roomId);

    List<RoomDto> getAllRooms();

    String generateRoomId();

    boolean isRoomAvailable(String roomType, LocalDate startDate, LocalDate endDate);
}