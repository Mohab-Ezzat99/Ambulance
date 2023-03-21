package mrandroid.myapp.util;

import java.util.ArrayList;

import mrandroid.myapp.model.HospitalModel;

public class Info {

    public static ArrayList<HospitalModel> getHospitals() {
        ArrayList<HospitalModel> hospitals = new ArrayList<>();
        hospitals.add(new HospitalModel("مستشفى ميت غمر العام", "01201111111", 17, 34, 50, 80.0, 30.75972703415985, 31.259824075744465));
        hospitals.add(new HospitalModel("مستشفي دماص المركزي", "01025555555", 13, 38, 26, 84.0, 30.849184476409633, 31.326313082832776));
        hospitals.add(new HospitalModel("مستشفي السنبلاوين العام", "01025254444", 10, 30, 8, 78.0, 30.931678803483123, 31.469135339208435));
        hospitals.add(new HospitalModel("مستشفى الدلتا", "01026224000", 9, 42, 5, 76.0, 31.09175010105062, 31.364765228780072));
        hospitals.add(new HospitalModel("مستشفى نبروه المركزي", "01551203333", 16, 27, 16, 75.0, 31.152882725713646, 31.30983359171251));
        hospitals.add(new HospitalModel("مستشفى بلقاس المركزي", "01025462349", 14, 24, 38, 90.0, 31.26798706808982, 31.359272065073316));
        hospitals.add(new HospitalModel("مستشفى الخير", "01553002255", 5, 20, 44, 85.0, 31.095696314137435, 31.419397114651854));
        return hospitals;
    }

}
