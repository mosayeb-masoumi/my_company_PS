package com.rayanandisheh.peysepar.passenger.activities.login;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.helpers.DeviceInfo;
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
    public void attachPresenter(Contract.Presenter presenter,Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void login(String mobileNumber) {

       App.register.setStrMobile(mobileNumber);
       App.register.setStrIMEI(DeviceInfo.getDeviceIMEI(context));
       App.register.setStrToken(FirebaseInstanceId.getInstance().getToken());


        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<UserInfo> call = apiService.getLogin(App.register);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                if (response.code() == 200) {
                    App.userInfo = response.body();
                    presenter.loginResult(response.body().getResult());

                    App.mobileNumber_inActiveUser=mobileNumber;

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
    public void cacheUser() {
        Cache.setString("mobileNumber", App.register.getStrMobile());
        Cache.setString("IMEI", App.register.getStrIMEI());
    }



    @Override
    public void forgotPass(String mobileNumber) {
        App.user.setMobileNumber(mobileNumber);
        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<Integer> call = apiService.forgot(App.user);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                if (response.code() == 200)
                    presenter.forgotResult(response.body());
                else
                    presenter.forgotResult(-4);
            }

            @Override
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                presenter.forgotResult(-5);
            }
        });
    }




    @Override
    public boolean CheckSmsPermissionGranted() {

        if(ContextCompat.checkSelfPermission(context,Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,Manifest.permission.READ_SMS)!=PackageManager.PERMISSION_GRANTED){

            requestReceveSMS_ReadSMSPermmision();
        }else{
            return true;
        }

        return false;
    }

    private void requestReceveSMS_ReadSMSPermmision() {
        ActivityCompat.requestPermissions((Activity) context
                ,new String[]{Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS},1235);
    }

}