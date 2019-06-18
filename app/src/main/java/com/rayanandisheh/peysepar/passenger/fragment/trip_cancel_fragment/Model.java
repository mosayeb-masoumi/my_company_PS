package com.rayanandisheh.peysepar.passenger.fragment.trip_cancel_fragment;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Time;
import com.rayanandisheh.peysepar.passenger.models.Trip;
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
    public void requesrListCanceledTripTabLayout() {


        if(App.dateFromTripManagement.equals("") && App.dateToTripManagement.equals("")){
            App.userInfo.setDateFrom(Time.getNowPersianDate());
            App.userInfo.setDateTo(Time.getNowPersianDate());
        }else if(!App.dateFromTripManagement.equals("") && !App.dateToTripManagement.equals("")){
            App.userInfo.setDateFrom(App.dateFromTripManagement);
            App.userInfo.setDateTo(App.dateToTripManagement);
        }else if(!App.dateFromTripManagement.equals("") && App.dateToTripManagement.equals("")){
            App.userInfo.setDateFrom(App.dateFromTripManagement);
            App.userInfo.setDateTo(Time.getNowPersianDate());
        }else if(App.dateFromTripManagement.equals("") && !App.dateToTripManagement.equals("")){
            App.userInfo.setDateFrom(Time.getNowPersianDate());
            App.userInfo.setDateTo(App.dateToTripManagement);
        }


        App.userInfo.setType(6);

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<List<Trip>> call= apiService.manageTrip(App.userInfo);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(response.code()==200){
                    App.CanceledTripTabLayoutListSuccess=false;

                    App.listCanceledTabLayout = response.body();
                    if(App.listCanceledTabLayout != null){
                        presenter.loadDataResult(1);
                    }else{
                        presenter.loadDataResult(0);
                    }
                }else{
                    presenter.loadDataResult(-4);
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

                presenter.loadDataResult(-5);
            }
        });
    }


    //todo list cant update (problem from back)
    @Override
    public void requestAlertConfirmation(int iOfficialTrip) {

        Trip trip=new Trip();
        trip.setiOfficialTrip(iOfficialTrip);

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Integer> call=apiService.cancelTrip(trip);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.code()==200){
                    presenter.cencelTripResult(response.body());
                }else{
                    presenter.cencelTripResult(-1);
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                presenter.cencelTripResult(-5);
            }
        });

    }
}
