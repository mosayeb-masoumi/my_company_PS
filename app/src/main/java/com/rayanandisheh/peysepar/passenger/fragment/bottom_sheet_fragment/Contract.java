package com.rayanandisheh.peysepar.passenger.fragment.bottom_sheet_fragment;

import android.content.Context;

public interface Contract {
    interface View{

        void setText();
    }

    interface Presenter {

        void attachView (Context context, View view);

        void viewLoaded();

        void reportResult(int reportResult);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requestTripTodayInfo();
    }
}
