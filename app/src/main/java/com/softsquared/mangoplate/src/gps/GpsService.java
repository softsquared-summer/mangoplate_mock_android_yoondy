package com.softsquared.mangoplate.src.gps;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.softsquared.mangoplate.src.ApplicationClass;

public class GpsService extends Service implements LocationListener {
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private final Context context;

    private Location location;
    private double latitude, longitude;
    protected LocationManager locationManager;

    public GpsService(Context context) {
        this.context = context;
        location = getLocation();
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            if(locationManager != null) {
                boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                if (!isGpsEnabled && !isNetworkEnabled) {
                    Log.d(ApplicationClass.TAG, "can't get location. GPS and network are all prevented.");
                } else {
                    int hasFineLocationPermission = ContextCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_FINE_LOCATION);
                    int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_COARSE_LOCATION);

                    if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
                        Log.d(ApplicationClass.TAG, "fine and coarse location permission are granted.");
                    } else {
                        Log.d(ApplicationClass.TAG, "can't get location. FineLocationPermission: "
                                + (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED ? "true" : "false")
                                + "CoarseLocationPermission: "
                                + (hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED ? "true" : "false"));
                        return null;
                    }

                    if (isNetworkEnabled) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }

                    if (isGpsEnabled) {
                        if (location == null) {
                            assert locationManager != null;
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            if (locationManager != null) {
                                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            Log.d(ApplicationClass.TAG, "GpsService::getLocation() exception: " + e.toString());
        }

        return location;
    }

    public double getLatitude() {
        if (location != null) {
            if (latitude <= 0.0) location = getLocation();

            latitude = location.getLatitude();
        }

        return latitude;
    }

    public double getLongitude() {
        if (location != null) {
            if (longitude <= 0.0) location = getLocation();

            longitude = location.getLongitude();
        }

        return longitude;
    }

    public void stopUsingGPS() {
        if (locationManager != null)
            locationManager.removeUpdates(GpsService.this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}
