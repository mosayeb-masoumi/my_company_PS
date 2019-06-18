package com.rayanandisheh.peysepar.passenger.activities.changePassword;

import android.support.annotation.NonNull;
import com.rayanandisheh.peysepar.passenger.services.APIClient;
import com.rayanandisheh.peysepar.passenger.services.APIService;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    @Override
    public void attachPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void changePass(String pass) {
        APIService apiService = APIClient.getClient().create(APIService.class);
        User user = App.user;
//        user.setEncodedPassword(Converter.Encoder(pass));
        Call<Integer> call = apiService.changePass(user);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                if (response.code() == 200) {
                    presenter.changePassResult(response.body());
                } else
                    presenter.changePassResult(-4);
            }

            @Override
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                presenter.changePassResult(-5);
            }
        });
    }

}