package com.rayanandisheh.peysepar.passenger.activities.trip_management_new;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.models.Data;
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
    public void attachPresenter(Contract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void requesrListNewTrips(int position) {

        if (position == 0) {
            App.userInfo.setType(0);
        } else if (position == 1) {
            App.userInfo.setType(1);
        } else if (position == 2) {
            App.userInfo.setType(2);
        } else if (position == 3) {
            App.userInfo.setType(6); //runnuing trip
        } else if (position == 4) {  // cancel by driver
            App.userInfo.setType(4);
        }

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<List<Trip>> call = apiService.manageTrip(App.userInfo);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.code() == 200) {

                    if(position==0){ //new Trip
                        App.newTripManagementSuccess = false;
//                        App.listNewTabLayoutTrip = response.body();
                        App.listNewTripManagementNew = response.body();
                        if ( App.listNewTripManagementNew != null) {
                        presenter.loadDataResult(1 , position);
                        } else {
                        presenter.loadDataResult(0 , position);
                        }

                    }else if(position==1){ //confirmedTrip
                        App.confirmedTripManagementSuccess=false;
                        App.listConfirmedTripManagementNew= response.body();
                        if(App.listConfirmedTripManagementNew != null){
                            presenter.loadDataResult(1 , position);
                        }else{
                            presenter.loadDataResult(0 ,position);
                        }

                    }else if(position==2){ // waiting driver confirmed
                        App.waitingDriverConfirmTripManagementSuccess=false;
                        App.listWaitingDriverConfirmTripManagementNew = response.body();
                        if(App.listWaitingDriverConfirmTripManagementNew != null){
                            presenter.loadDataResult(1,position);
                        }else{
                            presenter.loadDataResult(0,position);
                        }

                    }else if(position==3){  //runnuing trip
                        App.RunningTripManagementSuccess=false;
                        App.listRunningTripManagementNew = response.body();
                        if(App.listRunningTripManagementNew != null){
                            presenter.loadDataResult(1 ,position);
                        }else{
                            presenter.loadDataResult(0,position);
                        }

                    }else if(position==4){
                        App.CanceledTripManagementSuccess=false;
                        App.listCanceledTripManagementNew = response.body();
                        if(App.listCanceledTripManagementNew != null){
                            presenter.loadDataResult(1,position);
                        }else{
                            presenter.loadDataResult(0,position);
                        }
                    }
                } else {
                    presenter.loadDataResult(-4 ,position);
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

                presenter.loadDataResult(-5 ,position);
            }
        });
    }

    @Override
    public void requestCancelNewAlertTripManagement(int iOfficialTrip, int position, int tiTripStatus) {
        Trip trip=new Trip();
        trip.setiOfficialTrip(iOfficialTrip);
        trip.setTiTripStatus(tiTripStatus);

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Integer> call=apiService.cancelTrip(trip);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.code()==200){
                    presenter.cencelNewTripAlertResult(response.body() ,position);
                }else{
                    presenter.cencelNewTripAlertResult(-4 ,position);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                presenter.cencelNewTripAlertResult(-5 , position);
            }
        });
    }

    @Override
    public void requestConfirmNewAlertTripManagement(int iOfficialTrip, int position) {
        Trip trip=new Trip();
        trip.setiOfficialTrip(iOfficialTrip);

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Data> call=apiService.managmentConfirm(trip);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.code() == 200){
                    App.data=response.body();
                    presenter.confirmNewTripAlertResult(response.body().getResult() ,position);
                }else{
                    presenter.confirmNewTripAlertResult(-4,position);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                presenter.confirmNewTripAlertResult(-5,position);
            }
        });

    }

    @Override
    public void requestCancelConfirmedTripAlertTripManagement(int iOfficialTrip, int position, int tiTripStatus) {

        Trip trip=new Trip();
        trip.setiOfficialTrip(iOfficialTrip);
        trip.setTiTripStatus(tiTripStatus);

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Integer> call=apiService.cancelTrip(trip);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.code()==200){
                    presenter.cencelConfirmTripAlertResult(response.body() ,position);
                }else{
                    presenter.cencelConfirmTripAlertResult(-4 ,position);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                presenter.cencelConfirmTripAlertResult(-5 , position);
            }
        });
    }

    @Override
    public void requestCancelWaitingDriverConfirmedAlertTripManagement(int iOfficialTrip, int position, int tiTripStatus) {
        Trip trip=new Trip();
        trip.setiOfficialTrip(iOfficialTrip);
        trip.setTiTripStatus(tiTripStatus);

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Integer> call=apiService.cancelTrip(trip);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.code()==200){
                    presenter.cencelWaitinDriverConfirmTripAlertResult(response.body() , position);
                }else{
                    presenter.cencelWaitinDriverConfirmTripAlertResult(-4 , position);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                presenter.cencelWaitinDriverConfirmTripAlertResult(-5 , position);
            }
        });
    }

    @Override
    public void requestCancelTripConfim(int iOfficialTrip, int position) {
        Trip trip=new Trip();
        trip.setiOfficialTrip(iOfficialTrip);

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Integer> call=apiService.cancelTrip(trip);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.code()==200){
                    presenter.cencelTripAlertConfirmResult(response.body() , position);
                }else{
                    presenter.cencelTripAlertConfirmResult(-1 , position);
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                presenter.cencelTripAlertConfirmResult(-5 , position);
            }
        });
    }
}
