package com.rayanandisheh.peysepar.passenger.fragment.trip_dashboard_fragment;

import android.content.Context;
import android.view.View;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Time;
import com.rayanandisheh.peysepar.passenger.models.Dashboard;
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
    public void requestLoadingDashboardInfos(View view) {


//        //todo send date From & To  ()uncomment below lines
//        if(App.dateFromTripManagement.equals("") && App.dateToTripManagement.equals("")){
//            App.userInfo.setDateFrom(Time.getNowPersianDate());
//            App.userInfo.setDateTo(Time.getNowPersianDate());
//        }else if(!App.dateFromTripManagement.equals("") && !App.dateToTripManagement.equals("")){
//            App.userInfo.setDateFrom(App.dateFromTripManagement);
//            App.userInfo.setDateTo(App.dateToTripManagement);
//        }else if(!App.dateFromTripManagement.equals("") && App.dateToTripManagement.equals("")){
//            App.userInfo.setDateFrom(App.dateFromTripManagement);
//            App.userInfo.setDateTo(Time.getNowPersianDate());
//        }else if(App.dateFromTripManagement.equals("") && !App.dateToTripManagement.equals("")){
//            App.userInfo.setDateFrom(Time.getNowPersianDate());
//            App.userInfo.setDateTo(App.dateToTripManagement);
//        }


        App.score.setStrDate(Time.getNowPersianDate());
        App.score.setiMobileDomain(App.userInfo.getiMobileDomain());

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<Dashboard> call = apiService.Dashboard(App.score);
        call.enqueue(new Callback<Dashboard>() {
            @Override
            public void onResponse(Call<Dashboard> call, Response<Dashboard> response) {
                if (response.code() == 200) {
                    App.dashboard = response.body();
                    presenter.dashboardResult(response.body().getResult() , view);
                } else {
                    presenter.dashboardResult(-4, view);
                }
            }

            @Override
            public void onFailure(Call<Dashboard> call, Throwable t) {
                presenter.dashboardResult(-5, view);
            }
        });

    }
}
