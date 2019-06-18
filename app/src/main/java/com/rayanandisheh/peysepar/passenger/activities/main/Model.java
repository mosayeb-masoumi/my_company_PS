package com.rayanandisheh.peysepar.passenger.activities.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.models.Trip;
import com.rayanandisheh.peysepar.passenger.services.APIClient;
import com.rayanandisheh.peysepar.passenger.services.APIService;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.DownloadManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class Model implements Contract.Model {

    private Context context;
    private Contract.Presenter presenter;

    @Override
    public void attachPresenter(Context context, Contract.Presenter presenter) {
        this.presenter = presenter;
        App.isRun = true;
        this.context = context;
    }


    @Override
    public void checkUpdate() {
//        presenter.updateResult(App.user.getAppVersion());
        presenter.updateResult(App.userInfo.getVersionCode() ,App.userInfo.getVersionCodeMin());
    }

    @Override
    public void checkPermission() {
        presenter.permissionResult(
                (ContextCompat.checkSelfPermission(App.context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) ? -1 : 1);
    }

       @Override
    public void update() {
        new DownloadManager().DownloadUpdateApp(context);
    }

    @Override
    public void requestPermission() {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    @Override
    public void loadCurrentTripList() {

        presenter.showPregressbar();

        App.userInfo.setType(2);
        App.userInfo.setStrMobile(Cache.getString("mobileNumber"));

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<List<Trip>> call = apiService.getTrip(App.userInfo);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful()) {
                    App.listCurrentTrip = response.body();

                    if (App.listCurrentTrip != null) {
                        presenter.currentTripResult(1);
//                        App.currentTripSuccess=false;
                    }
                    else
                        presenter.currentTripResult(0);

                } else {
                    presenter.currentTripResult(-4);
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                presenter.currentTripResult(-5);
            }
        });
    }

}