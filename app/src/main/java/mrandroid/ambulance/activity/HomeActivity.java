package mrandroid.ambulance.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mrandroid.ambulance.R;
import mrandroid.ambulance.databinding.ActivityHomeBinding;
import mrandroid.ambulance.databinding.ActivitySplashBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}