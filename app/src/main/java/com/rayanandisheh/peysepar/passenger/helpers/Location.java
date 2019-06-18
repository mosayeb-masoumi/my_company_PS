package com.rayanandisheh.peysepar.passenger.helpers;

import android.Manifest;
        import android.app.Activity;
        import android.content.Context;
        import android.content.IntentSender;
        import android.content.pm.PackageManager;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.support.v4.app.ActivityCompat;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.common.api.PendingResult;
        import com.google.android.gms.common.api.Status;
        import com.google.android.gms.location.*;

public class Location {

    public static boolean getLocation(Context context) {
        LocationManager locationManager;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            return false;
//            context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

        }  else {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                    (context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                return false;
            }
            if (locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
                App.lastLat = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).
                        getLatitude();
                App.lastLng = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).
                        getLongitude();
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    10000, 70, locationListenerGPS);
            return true;
        }

    }

    private static LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            App.lastLat = location.getLatitude();
            App.lastLng = location.getLongitude();


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
    };

    //to show current position as Dot
    public static void displayLocationSettingsRequest(Context context, int requestCode) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(result1 -> {
            final Status status = result1.getStatus();
            if (status.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED)
                try {
                    status.startResolutionForResult((Activity) context, requestCode);
                } catch (IntentSender.SendIntentException ignored) {
                }
        });
    }
}