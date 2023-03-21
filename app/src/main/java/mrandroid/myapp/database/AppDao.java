package mrandroid.myapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import mrandroid.myapp.model.HospitalModel;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertHospital(HospitalModel device);

    @Query("SELECT * FROM HospitalModel")
    LiveData<List<HospitalModel>> getAllHospitals();

    @Query("DELETE FROM HospitalModel")
    Completable deleteAllHospitals();

}
