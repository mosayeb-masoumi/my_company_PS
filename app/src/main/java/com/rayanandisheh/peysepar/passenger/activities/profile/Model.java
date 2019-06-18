package com.rayanandisheh.peysepar.passenger.activities.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.google.firebase.iid.FirebaseInstanceId;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.helpers.Converter;
import com.rayanandisheh.peysepar.passenger.models.Register;
import com.rayanandisheh.peysepar.passenger.models.UserInfo;
import com.rayanandisheh.peysepar.passenger.services.APIClient;
import com.rayanandisheh.peysepar.passenger.services.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;

    }

    @Override
    public void requestProfile(String name, String family, String nationalID, String permanentOriginAddress, boolean rbManchecked, Bitmap bmProfile) {

        App.register.setStrName(name);
        App.register.setStrFamily(family);
        App.register.setStrNationalCode(nationalID);
        App.register.setStrMobile(Cache.getString("mobileNumber"));

        if(App.marker_existance) {
            App.register.setLat(App.selectedPosition.latitude);
            App.register.setLon(App.selectedPosition.longitude);


//            Cache.setString("market_existence" , "marker_existenceValue");


        }else{
            App.register.setLat(0);
            App.register.setLon(0);
        }

        App.register.setImg(Converter.bitmapToString(bmProfile));
        App.register.setStrToken(FirebaseInstanceId.getInstance().getToken());



        if (rbManchecked) {
            App.register.setTiSex(1);
        } else {
            App.register.setTiSex(2);
        }

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Register> call=apiService.getUpdateProfile(App.register);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.code()==200) {
                    App.register = response.body();

                    presenter.updateProfileResulr(response.body().getResult());
                }else{
                    presenter.updateProfileResulr(-2);
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                presenter.updateProfileResulr(-1);
            }
        });

    }

    @Override
    public boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1234);
        } else {
//            Toast.makeText(context, "permission has granted", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public boolean checkGPSturnOn() {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        Location.displayLocationSettingsRequest(context, 121);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//           context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }else{
           return true;
        }
        return false;
    }

    @Override
    public void saveLATLNG_AddreesName() {

//        //todo we should read data from server , dont need to use shareprefrence
//        //to show former marker on Map when user back to the map again
//        Cache.setLat("LAT" , (float) App.selectedPosition.latitude);
//        Cache.setLng("LNG" , (float) App.selectedPosition.longitude);

       // to show selected address on Map when user back to the map again
        Cache.setString("selectedPositionName",App.selectedPositionName);


    }

    //to refresh profile
    @Override
    public void requestRefresh() {

        App.userInfo.setStrMobile(Cache.getString("mobileNumber"));
        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<UserInfo> call=apiService.refresh(App.userInfo);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                if(response.code() == 200 && response.body()!=null){
                    App.userInfo=response.body();
                    presenter.refreshResult(1);
                }else{
                    presenter.refreshResult(-4);
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                 presenter.refreshResult(-5);
            }
        });
    }
}
