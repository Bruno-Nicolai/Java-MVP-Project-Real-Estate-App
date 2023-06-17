package co.imob.version1.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.GoogleMap;

public class MapPresenter implements MapContract.Presenter {

    private final MapContract.View view;

    public MapPresenter(MapContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewCreated(@NonNull GoogleMap googleMap) {
        if (view.hasLocationPermissions()) {
            view.onMapReady(googleMap);
        } else {
            view.requestLocationPermissions();
        }
    }

    @Override
    public void onLocationPermissionGranted(@NonNull GoogleMap googleMap) {
        view.onMapReady(googleMap);
    }

    @Override
    public void onLocationPermissionDenied() {
        view.showLocationPermissionDenied();
    }

    @Override
    public void onMapReady() {
        view.showLastKnownLocation(null);
        if (view.hasLocationPermissions()) {
            view.getCurrentLocation();
        } else {
            view.requestLocationPermissions();
            view.getCurrentLocation();
        }
    }

    @Override
    public LocationCallback getLocationCallback() {
        return new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                view.showLocationUpdatesResult(locationResult);
            }
        };
    }

}
