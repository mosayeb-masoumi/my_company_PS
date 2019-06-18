package com.rayanandisheh.peysepar.passenger.activities.signature;

import android.content.Context;
import android.graphics.Bitmap;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Converter;
import com.rayanandisheh.peysepar.passenger.models.EndTrip;
import com.rayanandisheh.peysepar.passenger.services.APIClient;
import com.rayanandisheh.peysepar.passenger.services.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {
    private Contract.Presenter presenter;
    private Context context;

    String bitmapSign;

    @Override
    public void attachPresenter(Contract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void saveAndFinish(Bitmap bitmap, float rating) {
        if (bitmap != null) {
            presenter.requestStart();
            bitmapSign=Converter.bitmapToString(bitmap);
            request(bitmapSign, rating);
        }
    }

    private void request(String bitmapSign, float rating) {

        EndTrip endTrip = new EndTrip();

        endTrip.setfScore((int) rating * 20);
        endTrip.setImg(bitmapSign);
        endTrip.setiOfficialTrip(App.notifModel.getiOfficialTrip());

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<Integer> call = apiService.endTrip(endTrip);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if (response.code() == 200) {
                    presenter.endTripResult(response.body());
                } else {
                    presenter.endTripResult(-2);
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                presenter.endTripResult(-1);
            }
        });
    }
}