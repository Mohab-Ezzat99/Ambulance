package mrandroid.ambulance.util;

import java.util.ArrayList;

import mrandroid.ambulance.model.HospitalModel;

public class Info {

    public static ArrayList<HospitalModel> getHospitals() {
        ArrayList<HospitalModel> hospitals = new ArrayList<>();
        hospitals.add(new HospitalModel("test1", "01205186367", 17, 34, 80.0, 0.0, 0.0));
        hospitals.add(new HospitalModel("test2", "01205186367", 17, 34, 80.0, 0.0, 0.0));
        return hospitals;
    }

}
