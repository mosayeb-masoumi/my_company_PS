package com.rayanandisheh.peysepar.passenger.activities.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;
import com.rayanandisheh.peysepar.passenger.helpers.*;
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
    public boolean hasCachedUser() {
        String mobileNumber = Cache.getString("mobileNumber");
//        String mobileNumber = App.register.getStrMobile();
//        String encodedPassword = Cache.getString("encodedPassword");
//        return Validate.mobile(mobileNumber) && Validate.password(encodedPassword);
        return Validate.mobile(mobileNumber);
    }

    @Override
    public void login() {

        Register register = new Register();
        register.setStrMobile(Cache.getString("mobileNumber"));
//        register.setStrIMEI(Cache.getString("IMEI"));
        register.setStrIMEI(getDeviceIMEI());
        register.setStrToken(FirebaseInstanceId.getInstance().getToken());


        request(register);
    }
    private void request(Register register) {
        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<UserInfo> call = apiService.getLogin(register);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                if (response.code() == 200) {
                    App.userInfo = response.body();
                    App.register.setStrMobile(register.getStrMobile());
                    presenter.loginResult(response.body().getResult());
                } else
                    presenter.loginResult(-1);
            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                presenter.loginResult(-5);
            }
        });
    }

    @Override
    public void cacheMobilePhone() {

        Cache.setString("mobileNumber",App.register.getStrMobile());
    }


    @Override
    public void saveUserInfo(int result) {
//       App.userInfo = result;

    }


    public String getDeviceIMEI() {

        String deviceUniqueIdentifier = null;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm) {
//            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)

                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, 3);
            else
                deviceUniqueIdentifier = tm.getDeviceId();
            if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length())
                deviceUniqueIdentifier = "0";
        }
        return deviceUniqueIdentifier;
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case 3: {
//
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                } else {
//
//                    Toast.makeText(context, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//
//        }
//    }


}