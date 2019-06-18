package com.rayanandisheh.peysepar.passenger.activities.feedback;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;

public class Presenter implements Contract.Presenter {

    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this,context);
    }

    @Override
    public void submit(Editable feedback, float rating) {
        view.showLoading();
        model.submitFeedback(feedback, (int) (rating * 20));
    }

    @Override
    public void feedbackResult(int result) {
        view.stopLoading();
        switch (result) {
            case -5:
                Toaster.shorter(context.getString(R.string.connectionFaield));
                break;
            case -4:
                Toaster.shorter(context.getString(R.string.serverFaield));
                break;
            case 1:
                Toaster.shorter(context.getString(R.string.successfullySubmited));
                ((Activity) context).finish();
                break;
            default:
                break;
        }
    }
}
