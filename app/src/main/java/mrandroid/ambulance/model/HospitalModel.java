package mrandroid.ambulance.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HospitalModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private int bedsNumber;
    private int roomsNumber;
    private double roomPrice;
    private double lat;
    private double lng;

    public HospitalModel(String name, String phone, int bedsNumber, int roomsNumber, Double roomPrice, Double lat, Double lng) {
        this.name = name;
        this.phone = phone;
        this.bedsNumber = bedsNumber;
        this.roomsNumber = roomsNumber;
        this.roomPrice = roomPrice;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(int bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public double getDistanceFromLocation(double lat, double lng) {
        return 0.0;
    }
}
