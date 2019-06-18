package com.rayanandisheh.peysepar.passenger.activities.credit;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;

public interface Contract {
    interface View{

        void showProgress();
    }

    interface Presenter {

        void attachView (Context context,View view);


        void btnIncCreditPressed(AppCompatEditText edtValue);
    }

    interface Model{
        void attachPresenter (Presenter presenter ,Context context);

    }
}
