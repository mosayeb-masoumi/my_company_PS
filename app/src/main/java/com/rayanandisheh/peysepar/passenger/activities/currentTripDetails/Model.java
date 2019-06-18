package com.rayanandisheh.peysepar.passenger.activities.currentTripDetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.models.Trip;
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
    public void cancelTripRequest(Trip iOfficialTrip) {

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<Integer> call=apiService.cancelTrip(iOfficialTrip);

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

    @Override
    public void btnCallPressed(String driverMobile) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+driverMobile));
//        intent.setData(Uri.parse("tel:09372112652"));
        context.startActivity(intent);
    }
}
