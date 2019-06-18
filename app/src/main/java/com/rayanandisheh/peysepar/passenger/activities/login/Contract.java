package com.rayanandisheh.peysepar.passenger.activities.login;

import android.content.Context;

public interface Contract {

    interface View {

        void startLoading();

        void stopLoading();

        void startForgotLoading();

        void stopForgotLoading();

        void showMobileNumberError(String string);

        void showError09MobileNumberLogin();

        void showErrorZeroMobileNumberLogin();

        void showErrorNotCorrectMobilePhoneLogin();

        void setEdtMobile_inActiveUser();


//        void showPasswordError(String string);
    }

    interface Presenter {

        void attachView(Context context, View view);

        void forgotPass(String mobileNumber);

        void register();

        void login(String mobileNumber);

        void loginResult(int result);

        void forgotResult(int result);

        void forgotPassLoadTimeFinished();
    }

    interface Model {

        void attachPresenter(Presenter presenter ,Context context);

        void forgotPass(String mobileNumber);

        void login(String mobileNumber);

        void cacheUser();

        boolean CheckSmsPermissionGranted();
    }
}
