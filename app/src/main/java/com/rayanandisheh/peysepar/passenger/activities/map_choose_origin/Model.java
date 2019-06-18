package com.rayanandisheh.peysepar.passenger.activities.map_choose_origin;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;

import java.util.Locale;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void saveSelectedLatLng(LatLng position) {
        App.map = true;

        App.selectedPositionName = getPositionName(position);
        App.selectedPosition = position;
        presenter.saved();

//        //to show former marker on Map when user back to the map again
//        Cache.setLat("LAT" , (float) position.latitude);
//        Cache.setLng("LNG" , (float) position.longitude);
//
//        // to show selected address on Map when user back to the map again
//        Cache.setString("selectedPositionName",getPositionName(position));

    }


    private String getPositionName(LatLng position) {
        try {
            Address address = new Geocoder(App.context, new Locale("fa")).getFromLocation(position.latitude, position.longitude, 3).get(0);
            String addressName = ((address.getLocality() != null ? address.getLocality() + "، " : "") + (address.getThoroughfare() != null ? address.getThoroughfare() : ""));

//            return addressName;

            if (addressName.length() > 0) {
                return addressName;
            } else {
                return "بدون نام";
//                return "";
//                return String.valueOf(position); // show latlang if there was no placeName
            }

        } catch (Exception e) {
            Log.e("location", e.getMessage());
//            return "";
            return "بدون نام";
//            return String.valueOf(position);// show latlang if there was no placeName
        }
    }
}
