package com.rayanandisheh.peysepar.passenger.activities.activation;

import android.content.Context;

public interface Contract {

    interface View {

        void startSendCodeAgainLoading();

        void stopSendCodeAgainLoading();

        void startValidateLoading();

        void stopValidateLoading();

        void showActivationCodeError(String string);

        void showPbDiffrentIMEI();

        void stopPbValidateDifferentIMEI();
    }

    interface Presenter {

        void attachView(Context context, View view);

        void btValidatePressed(String validationCode);

        void sendAgainPressed();

        void sendCodeResult(int result);

        void validateResult(int i);

        void sendCodeAgainLoadTimeFinished();

        void btnGotoRegisterDifferentIMEI_Pressed(String formerMobilePhone);

        void btValidateDifferentIMEI_Pressed(String smsCode, String formerMobilePhone);


        void validateDiffrentIMEIResult(int result);
    }

    interface Model {

        void attachPresenter(Presenter presenter,Context context);

        void validate(String validationCode);

        void sendCodeAgain();

        void cacheUser();

        void validateDiffrentIMEI(String smsCode, String formerMobilePhone);
    }
}