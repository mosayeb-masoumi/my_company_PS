package com.rayanandisheh.peysepar.passenger.services;

import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(App.ServerURL + "/Passenger/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (Exception e) {
                Toaster.shorter("آدرس سرور اشتباه است");
            }

        }
        return retrofit;
    }
}