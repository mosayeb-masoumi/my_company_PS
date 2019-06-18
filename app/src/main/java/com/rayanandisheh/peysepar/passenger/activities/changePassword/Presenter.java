package com.rayanandisheh.peysepar.passenger.activities.changePassword;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.Validate;

public class Presenter implements Contract.Presenter {

    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this);
    }

    @Override
    public void saveNewPass(String pass, String repeatPass) {
        if (!Validate.password(pass))
            view.showPasswordError(context.getResources().getString(R.string.emptyPassword));
        else if (repeatPass.equals(pass))
            view.showRepeatPasswordError(context.getResources().getString(R.string.passwordsAreNotMatch));
        else {
            view.startLoading();
            model.changePass(pass);
        }
    }

    @Override
    public void changePassResult(Integer result) {
        view.stopLoading();

    }
}
