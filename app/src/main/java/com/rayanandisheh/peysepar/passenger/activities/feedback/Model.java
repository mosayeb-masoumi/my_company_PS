package com.rayanandisheh.peysepar.passenger.activities.feedback;

import android.content.Context;
import android.text.Editable;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;
    @Override
    public void attachPresenter(Contract.Presenter presenter,Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void submitFeedback(Editable feedback, int rate) {
//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<Integer> call = apiService.feedback(feedback, rate);
//        call.enqueue(new Callback<Integer>() {
//            @Override
//            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
//                if (response.code() == 200)
//                    presenter.feedbackResult(response.body());
//                else
//                    presenter.feedbackResult(-4);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
//                presenter.feedbackResult(-5);
//            }
//        });
    }

}