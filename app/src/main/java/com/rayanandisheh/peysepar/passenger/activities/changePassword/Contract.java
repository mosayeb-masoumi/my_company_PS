package com.rayanandisheh.peysepar.passenger.activities.changePassword;

import android.content.Context;

public interface Contract {
    interface View {

        void startLoading();

        void stopLoading();

        void showPasswordError(String string);

        void showRepeatPasswordError(String string);
    }

    interface Presenter {

        void attachView(Context context, View view);

        void saveNewPass(String string, String string1);

        void changePassResult(Integer body);
    }

    interface Model {

        void attachPresenter(Presenter presenter);

        void changePass(String pass);
    }
}
