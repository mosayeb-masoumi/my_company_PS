package com.rayanandisheh.peysepar.passenger.activities.feedback;

import android.content.Context;
import android.text.Editable;

public interface Contract {

    interface View {

        void showLoading();

        void stopLoading();
    }

    interface Presenter {

        void attachView(Context context, View view);

        void submit(Editable feedback, float rating);

        void feedbackResult(int result);
    }

    interface Model {

        void attachPresenter(Presenter presenter,Context context);

        void submitFeedback(Editable feedback, int rate);
    }
}
