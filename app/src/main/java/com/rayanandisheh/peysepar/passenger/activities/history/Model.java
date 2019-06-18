package com.rayanandisheh.peysepar.passenger.activities.history;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
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

        HistoryAdapter historyAdapter;
        List<Trip> historyList;

    @Override
    public void attachPresenter(Contract.Presenter presenter ,Context context) {
        this.presenter = presenter;
        this.context=context;
    }


    @Override
    public void loadHistoryTripList(String dateFrom, String dateTo) {

        presenter.showPregressbar();

        App.userInfo.setDateFrom(dateFrom);
        App.userInfo.setDateTo(dateTo);
        App.userInfo.setType(1);
        App.userInfo.setStrMobile(Cache.getString("mobileNumber"));

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<List<Trip>> call = apiService.getTrip(App.userInfo);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if(response.code()==200){

                    App.historySuccess= false;

                    App.listHistoryTrip = response.body();
                    if(App.listHistoryTrip != null){
                        presenter.loadDataResult(1);
                    }else{
                        presenter.loadDataResult(0);
                    }
//                     Presenter.loadData(response.body().getResult);

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
}
