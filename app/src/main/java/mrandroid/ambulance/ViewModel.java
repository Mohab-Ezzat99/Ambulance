package mrandroid.ambulance;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import mrandroid.ambulance.model.HospitalModel;

public class ViewModel extends AndroidViewModel {

    private final Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insertHospital(HospitalModel hospital) {
        repository.insertHospital(hospital);
    }

    public LiveData<List<HospitalModel>> getAllHospitals() {
        return repository.getAllHospitals();
    }

    public void deleteAllHospitals() {
        repository.deleteAllHospitals();
    }

}
