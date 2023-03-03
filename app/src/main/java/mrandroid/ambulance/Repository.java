package mrandroid.ambulance;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import mrandroid.ambulance.database.AppDB;
import mrandroid.ambulance.database.AppDao;
import mrandroid.ambulance.model.HospitalModel;

public class Repository {
    private final AppDao appDao;

    public Repository(Application application) {
        this.appDao = AppDB.getInstance(application).appDao();
    }

    public void insertHospital(HospitalModel hospital) {
        appDao.insertHospital(hospital)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<List<HospitalModel>> getAllHospitals() {
        return appDao.getAllHospitals();
    }

    public void deleteAllHospitals() {
        appDao.deleteAllHospitals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
