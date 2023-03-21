package mrandroid.myapp.database;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import mrandroid.myapp.model.HospitalModel;

public class Converters {

    @TypeConverter
    public String convertToMessageString(List<HospitalModel> messageModels){
        return new Gson().toJson(messageModels);
    }

    @TypeConverter
    public List<HospitalModel> convertToMessageList(String stringList){
        Type listType = new TypeToken<List<HospitalModel>>(){}.getType();
        return new Gson().fromJson(stringList,listType);
    }
}
