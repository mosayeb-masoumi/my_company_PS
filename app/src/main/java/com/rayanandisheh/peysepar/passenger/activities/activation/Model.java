package com.rayanandisheh.peysepar.passenger.activities.activation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.firebase.iid.FirebaseInstanceId;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.helpers.DeviceInfo;
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
    public void validate(String validationCode) {

        Register register = new Register();
        register.setStrMobile(App.register.getStrMobile());
        register.setStrIMEI(DeviceInfo.getDeviceIMEI(context));
        register.setStrActivateCode(validationCode);
        register.setStrToken(FirebaseInstanceId.getInstance().getToken());

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<UserInfo> call = apiService.getActivate(register);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                if (response.code() == 200) {
                    App.userInfo = response.body();
                    presenter.validateResult(response.body().getResult());
                } else
                    presenter.validateResult(-4);
            }
            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                presenter.validateResult(-5);
            }
        });
    }





    @Override
    public void validateDiffrentIMEI(String smsCode, String formerMobilePhone) {
        App.replace.setStrActivateCode(smsCode);
        App.replace.setStrMobile1(formerMobilePhone);
        App.replace.setStrMobile2(App.mobileFirstRunning);
        App.replace.setStrIMEI(DeviceInfo.getDeviceIMEI(context));

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<UserInfo> call = apiService.Replacement(App.replace);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                if(response.code()==200){
                    App.userInfo = response.body();
                    presenter.validateDiffrentIMEIResult(response.body().getResult());
                }else{
                    presenter.validateDiffrentIMEIResult(-4);
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                presenter.validateDiffrentIMEIResult(-5);
            }
        });
    }


    @Override
    public void sendCodeAgain() {

        App.register.setStrMobile(App.register.getStrMobile());

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<Integer> call = apiService.getSmsCode(App.register);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                if (response.code() == 200)
                    presenter.sendCodeResult(response.body());
                else
                    presenter.sendCodeResult(-4);
            }

            @Override
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                presenter.sendCodeResult(-5);
            }
        });
    }

    @Override
    public void cacheUser() {
        Cache.setString("mobileNumber",  App.register.getStrMobile());
        Cache.setString("IMEI",  App.register.getStrIMEI());
//        Cache.setString("encodedPassword", App.user.getEncodedPassword());
    }

}