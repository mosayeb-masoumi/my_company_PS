package com.rayanandisheh.peysepar.passenger.activities.setting;

import android.content.Context;

public interface Contract {
    interface View{

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void btnCheckaddressPressed(String etIP);

        void checkAddressResult(Integer checkAddresessResult);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void checkAddressRequest(String etIP);
    }
}
