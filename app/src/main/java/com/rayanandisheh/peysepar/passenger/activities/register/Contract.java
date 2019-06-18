package com.rayanandisheh.peysepar.passenger.activities.register;

import android.content.Context;

public interface Contract {
    interface View {

        void startLoading();

        void stopLoading();

        void showFirstNameError(String string);

        void showLastNameError(String string);

        void showNationalCodeError(String string);

        void showMobileNumberError(String string);

        void showPasswordError(String string);

        void showRepeatPasswordError(String string);

        void showErrorZeroMobileNumber();

        void showError09MobileNumber();

        void showErrorNotCorrectMobilePhone();

        void showErrorZeroFirstName();

        void showErrorNotCorrectFirstName();

        void showErrorZeroLastName();

        void showErrorNotCorrectLastName();
//        implements Contract.View {
//            Contract.Presenter Presenter = new Presenter();
//            Presenter.attachView(context,this);
//            Presenter.activityLoaded();

    }

    interface Presenter {

        void attachView(Context context, View view);
        void registerResult(int i);

        void btRegisterPressed(String firstName, String lastName, String mobileNumber, String sexuality);
    }

    interface Model {

        void attachPresenter(Presenter presenter, Context context);

        void register(String firstName, String lastName, String mobileNumber, String sexuality);

//        void register(String firstName, String lastName, String mobileNumber);
    }
}
