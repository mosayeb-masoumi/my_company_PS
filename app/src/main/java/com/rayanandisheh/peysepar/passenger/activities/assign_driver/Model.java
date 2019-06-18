package com.rayanandisheh.peysepar.passenger.activities.assign_driver;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.models.Data;
import com.rayanandisheh.peysepar.passenger.models.DriverInfo;
import com.rayanandisheh.peysepar.passenger.services.APIClient;
import com.rayanandisheh.peysepar.passenger.services.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter ,Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void requesrListAssignDriver(int iOfficialTrip) {

        App.userInfo.setStrMobile(Cache.getString("mobileNumber"));
        App.userInfo.setIofficialtrip(iOfficialTrip);
        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<List<DriverInfo>> call=apiService.driverList(App.userInfo);
        call.enqueue(new Callback<List<DriverInfo>>() {
            @Override
            public void onResponse(Call<List<DriverInfo>> call, Response<List<DriverInfo>> response) {
                if(response.code()==200){

                 App.listAssignDriverConfirmedTabLayout=response.body();
                 if(App.listAssignDriverConfirmedTabLayout!=null){
                     presenter.driverListResult(1);
                 }

                }else{
                    presenter.driverListResult(-4);
                }
            }

            @Override
            public void onFailure(Call<List<DriverInfo>> call, Throwable t) {
               presenter.driverListResult(-5);
            }
        });

    }

    
    @Override
    public void requestConfirmDriverAlert(String strunitid, int iofficialtrip) {
      App.assignDriver.setStrunitId(strunitid);
      App.assignDriver.setIofficialtrip(iofficialtrip);
      APIService apiService=APIClient.getClient().create(APIService.class);
      Call<Data> call=apiService.assignDriver(App.assignDriver);
      call.enqueue(new Callback<Data>() {
          @Override
          public void onResponse(Call<Data> call, Response<Data> response) {
              if(response.code()==200){
                  App.data=response.body();
                  presenter.assignDriverSelectedResult(1);
              }else{
                  presenter.assignDriverSelectedResult(-4);
              }
          }

          @Override
          public void onFailure(Call<Data> call, Throwable t) {
              presenter.assignDriverSelectedResult(-5);
          }
      });
    }


}
