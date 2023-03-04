package mrandroid.ambulance.model;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class HospitalModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private int bedsNumber;
    private int roomsNumber;
    private int bloodsNumber;
    private double roomPrice;
    private double lat;
    private double lng;

    public HospitalModel(String name, String phone, int bedsNumber, int roomsNumber, int bloodsNumber, double roomPrice, double lat, double lng) {
        this.name = name;
        this.phone = phone;
        this.bedsNumber = bedsNumber;
        this.roomsNumber = roomsNumber;
        this.bloodsNumber = bloodsNumber;
        this.roomPrice = roomPrice;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBloodsNumber() {
        return bloodsNumber;
    }

    public void setBloodsNumber(int bloodsNumber) {
        this.bloodsNumber = bloodsNumber;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getDistanceFromLocation(double lat, double lng) {
        return 0.0;
    }
}
