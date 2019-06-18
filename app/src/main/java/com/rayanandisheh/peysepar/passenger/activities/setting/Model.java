package com.rayanandisheh.peysepar.passenger.activities.setting;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.helpers.App;
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
    public void checkAddressRequest(String etIP) {

        App.userInfo.setUrl(etIP);

        APIService apiService=APIClient.getClient().create(APIService.class);
//        Call<Integer> call=apiService.checkWebServiceAddress(App.userInfo);
        Call<Integer> call=apiService.checkWebServiceAddress();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if(response.code()==200){
                    presenter.checkAddressResult(response.body());
                }else{
                    presenter.checkAddressResult(-4);
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                presenter.checkAddressResult(-5);
            }
        });
    }
}
