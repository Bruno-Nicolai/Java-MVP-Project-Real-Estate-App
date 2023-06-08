package co.imob.version1.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import co.imob.version1.R;
import co.imob.version1.presenter.MapContract;
import co.imob.version1.presenter.MapPresenter;

public class MapFragment extends Fragment implements OnMapReadyCallback, MapContract.View {

    private MapContract.Presenter presenter;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    private double lat, lng;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        presenter = new MapPresenter(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity().getApplicationContext());

        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_container);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.map_container, fragment)
                    .commit();
        }
        fragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                MapFragment.this.googleMap = googleMap;
                presenter.onViewCreated(googleMap);
            }
        });

    }

    @Override
    public void requestLocationPermissions() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
    }

    @Override
    public boolean hasLocationPermissions() {
        return ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLocationPermissionDenied() {
        Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCurrentLocation(@NonNull Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        LatLng latLng = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Your location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    @Override
    public void showLocationUpdatesResult(@NonNull LocationResult locationResult) {
//        for (Location location : locationResult.getLocations()) {
//            if (location != null) {
//                Toast.makeText(requireContext(), "Current location is " + location.getLongitude() + ", " + location.getLatitude(), Toast.LENGTH_LONG).show();
//            }
//        }
    }

    @Override
    public void showLastKnownLocation(@Nullable Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            LatLng latLng = new LatLng(lat, lng);
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Your location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
//        getCurrentLocation();
        presenter.onMapReady();

//        LatLng marker = new LatLng(-30.032253651009807, -51.123031109739536);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
//        googleMap.addMarker(new MarkerOptions().title("Your position").position(marker));
    }

    @Override
    public void getCurrentLocation() {

        if (ActivityCompat
                .checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat
                .checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Request_code
            );
            return;

        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
//                Toast.makeText(
//                        requireContext(),
//                        "location result is " + locationResult,
//                        Toast.LENGTH_LONG
//                ).show();
//
//                for (Location location : locationResult.getLocations()) {
//                    if (location != null) {
//                        Toast.makeText(
//                                requireContext(),
//                                "Current location is " + location.getLatitude() + ", " + location.getLongitude(),
//                                Toast.LENGTH_LONG
//                        ).show();
//                    }
//                }
            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    LatLng latLng = new LatLng(lat, lng);
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("your location"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == Request_code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }

    }
}
