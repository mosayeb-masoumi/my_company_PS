package com.rayanandisheh.peysepar.passenger.fragment.bottom_sheet_fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.models.Report;
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
    public void requestTripTodayInfo() {

        App.report.setStrMobile(Cache.getString("mobileNumber"));

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Report> call=apiService.report(App.report);

        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(@NonNull Call<Report> call, Response<Report> response) {

                if(response.code() == 200 && response.body()!=null){
                    App.report=response.body();
                        presenter.reportResult(response.body().getResult());

                }else{
                    presenter.reportResult(-4);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Report> call, Throwable t) {
                presenter.reportResult(-5);
            }
        });
    }
}
