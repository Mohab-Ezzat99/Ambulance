package mrandroid.myapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import mrandroid.myapp.R;
import mrandroid.myapp.databinding.ActivityHomeBinding;
import mrandroid.myapp.model.HospitalModel;
import mrandroid.myapp.util.Info;
import mrandroid.myapp.util.LocationPermission;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks,
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private ActivityHomeBinding binding;
    private GoogleMap mMap;
    private ArrayList<HospitalModel> hospitals = Info.getHospitals();
    private ArrayList<LatLng> allMarkers = new ArrayList<>();
    private LatLng currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.fabHospital.setOnClickListener(view -> {
            getTheNearestHospital();
        });
        binding.fabMyLocation.setOnClickListener(view -> {
            fetchCurrentLocation();
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        boolean hasLocationPermission = LocationPermission.requestLocationPermission(this);
        if (hasLocationPermission && checkGpsEnabled()) {
            fetchCurrentLocation();
            drawHospitalsMarkers();
        }
    }

    private boolean checkGpsEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("Your GPS seems to be disabled, do you want to enable it?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        startActivityForResult(
                                new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                                200
                        );
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.cancel();
                    })
                    .create();
            dialog.show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (checkGpsEnabled()) {
                fetchCurrentLocation();
                drawHospitalsMarkers();
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (checkGpsEnabled()) {
            fetchCurrentLocation();
            drawHospitalsMarkers();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms))
            new AppSettingsDialog.Builder(this).build().show();
        else LocationPermission.requestLocationPermission(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @SuppressLint("MissingPermission")
    private void fetchCurrentLocation() {
        LocationServices.getFusedLocationProviderClient(this)
                .getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f));
                    }
                });
    }

    private void drawHospitalsMarkers() {
        for (HospitalModel hospital : hospitals) {
            LatLng markerPosition = new LatLng(hospital.getLat(), hospital.getLng());
            allMarkers.add(markerPosition);
            mMap.addMarker(new MarkerOptions().position(markerPosition));
        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        int index = allMarkers.indexOf(marker.getPosition());
        Intent intent = new Intent(this, HospitalActivity.class);
        intent.putExtra("hospital", hospitals.get(index));
        startActivity(intent);
        return false;
    }

    private void getTheNearestHospital() {
        if(currentLocation==null) return;
        ArrayList<Double> allDistances = new ArrayList<>();
        HashMap<Double, HospitalModel> distanceWithHospital = new HashMap<>();
        for (HospitalModel hospital : hospitals) {
            double distance = hospital.getDistanceFromLocation(currentLocation);
            allDistances.add(distance);
            distanceWithHospital.put(distance, hospital);
        }
        double minimumDistance = Collections.min(allDistances);
        HospitalModel targetHospital = distanceWithHospital.get(minimumDistance);
        LatLng targetLatLng = new LatLng(targetHospital.getLat(), targetHospital.getLng());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(targetLatLng, 15f));
    }
}