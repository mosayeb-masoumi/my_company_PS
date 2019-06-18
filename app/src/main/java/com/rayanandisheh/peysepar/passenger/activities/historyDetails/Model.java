package com.rayanandisheh.peysepar.passenger.activities.historyDetails;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.models.Data;
import com.rayanandisheh.peysepar.passenger.services.APIClient;
import com.rayanandisheh.peysepar.passenger.services.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;


    @Override
    public void attachPresenter(Contract.Presenter presenter ,Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void requestSendTodayRating(float rating, int iOfficialTrip) {

      App.score.setIofficialtrip(iOfficialTrip);
      App.score.setFscore(rating*20);

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Data> call=apiService.ChangeScore(App.score);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.code()==200){

                    App.data=response.body();
                    presenter.changeScoreResult(1);

                }else{
                    presenter.changeScoreResult(-4);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                presenter.changeScoreResult(-5);
            }
        });

    }
}
