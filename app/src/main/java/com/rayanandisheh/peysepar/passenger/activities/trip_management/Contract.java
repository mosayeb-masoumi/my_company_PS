package com.rayanandisheh.peysepar.passenger.activities.trip_management;

import android.content.Context;
import android.view.View;

public interface Contract {

    interface View{

        void setFromDate(String date);

        void setToDate(String date);
    }

    interface Presenter {

        void attachView (Context context,View view);
        
        void edtDatePressed(boolean isFrom);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

    }
}
