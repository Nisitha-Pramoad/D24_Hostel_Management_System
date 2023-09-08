package lk.ijse.D24_Hostel_Management_System.dto;

import lk.ijse.D24_Hostel_Management_System.entity.Room;
import lk.ijse.D24_Hostel_Management_System.entity.Student;

public class RoomDto {

    private String roomId;

    private String roomType;

    private double pricing;

    private double roomSize;

    private int maximumOccupency;

    private String armentiesAndFeatures;

    private String roomStatus;

    public RoomDto(String roomId, String roomType, double pricing, double roomSize, int maximumOccupency, String armentiesAndFeatures, String roomStatus) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricing = pricing;
        this.roomSize = roomSize;
        this.maximumOccupency = maximumOccupency;
        this.armentiesAndFeatures = armentiesAndFeatures;
        this.roomStatus = roomStatus;
    }

    public RoomDto() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPricing() {
        return pricing;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }

    public double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(double roomSize) {
        this.roomSize = roomSize;
    }

    public int getMaximumOccupency() {
        return maximumOccupency;
    }

    public void setMaximumOccupency(int maximumOccupency) {
        this.maximumOccupency = maximumOccupency;
    }

    public String getArmentiesAndFeatures() {
        return armentiesAndFeatures;
    }

    public void setArmentiesAndFeatures(String armentiesAndFeatures) {
        this.armentiesAndFeatures = armentiesAndFeatures;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "roomId='" + roomId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", pricing=" + pricing +
                ", roomSize=" + roomSize +
                ", maximumOccupency=" + maximumOccupency +
                ", armentiesAndFeatures='" + armentiesAndFeatures + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                '}';
    }

    public Room toEntity() {
        Room room = new Room();
        room.setRoomId(this.roomId);
        room.setRoomType(this.roomType);
        room.setPricing(this.pricing);
        room.setRoomSize(this.roomSize);
        room.setMaximumOccupency(this.maximumOccupency);
        room.setArmentiesAndFeatures(this.armentiesAndFeatures);
        room.setRoomStatus(this.roomStatus);
        return room;
    }
}
