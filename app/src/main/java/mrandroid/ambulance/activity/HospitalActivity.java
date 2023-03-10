package mrandroid.ambulance.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import mrandroid.ambulance.databinding.ActivityHospitalBinding;
import mrandroid.ambulance.model.HospitalModel;

public class HospitalActivity extends AppCompatActivity {

    private ActivityHospitalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HospitalModel hospital = (HospitalModel) getIntent().getSerializableExtra("hospital");
        binding.tvName.setText(hospital.getName());
        binding.tvRooms.setText(hospital.getRoomsNumber() + "  Rooms");
        binding.tvBeds.setText(hospital.getBedsNumber() + "  Beds");
        binding.tvBlood.setText(hospital.getBloodsNumber() + "  Bloods");
        binding.btnCallAmbulance.setText("Call  " + hospital.getPhone());
        binding.btnCallAmbulance.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + hospital.getPhone()));
            startActivity(intent);
        });
        binding.btnGoToHospital.setOnClickListener(view -> {
            String uri = "http://maps.google.com/maps?q=loc:" + hospital.getLat() + "," + hospital.getLng() + " (" + hospital.getName() + ")";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        });
    }
}