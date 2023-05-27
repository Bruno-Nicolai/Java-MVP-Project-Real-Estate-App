package co.imob.version1.presentation;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.GoogleMap;

public interface MapContract {

    interface View {
        void requestLocationPermissions();
        boolean hasLocationPermissions();
        void showLocationPermissionDenied();
        void showCurrentLocation(@NonNull Location location);
        void showLocationUpdatesResult(@NonNull LocationResult locationResult);
        void showLastKnownLocation(@Nullable Location location);
        void onMapReady(@NonNull GoogleMap googleMap);
        void getCurrentLocation();
    }

    interface Presenter {
        void onViewCreated(@NonNull GoogleMap googleMap);
        void onLocationPermissionGranted(@NonNull GoogleMap googleMap);
        void onLocationPermissionDenied();
        void onMapReady();
        LocationCallback getLocationCallback();
    }
}
