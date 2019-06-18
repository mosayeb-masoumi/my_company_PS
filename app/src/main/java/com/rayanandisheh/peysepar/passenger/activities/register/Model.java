package com.rayanandisheh.peysepar.passenger.activities.register;
import android.content.Context;
import android.support.annotation.NonNull;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.models.Register;
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
    public void register(String firstName, String lastName, String mobileNumber, String sexuality) {

        Register register = new Register();

        register.setStrName(firstName);
        register.setStrFamily(lastName);
        register.setStrMobile(mobileNumber);

//        // TODO: 12/22/2018 delete below saving. phone must be save after request
//        Cache.setString("mobileNumber", mobileNumber);

        if (sexuality.equals("مرد")) {
            register.setTiSex(1);
        } else {
            register.setTiSex(2);
        }

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<Register> call = apiService.getRegister(register);

        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(@NonNull Call<Register> call, @NonNull Response<Register> response) {
                if (response.code() == 200 && response.body() != null) {

                    presenter.registerResult(response.body().getResult());

                    //save values behalf of sharepref...
                    App.register.setStrMobile(mobileNumber);

                } else
                    presenter.registerResult(-1);
            }

            @Override
            public void onFailure(@NonNull Call<Register> call, @NonNull Throwable t) {
                presenter.registerResult(-4);
            }
        });
    }
}