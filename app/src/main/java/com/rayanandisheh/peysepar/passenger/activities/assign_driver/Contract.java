package com.rayanandisheh.peysepar.passenger.activities.assign_driver;

import android.content.Context;

public interface Contract {
    interface View{

        void showSwipeRefresh();

        void hideImg_noItem();

        void showImg_noItem();

        void hideSwipeRefresh();

        void setAdapter();
    }

    interface Presenter {

        void attachView (Context context, View view);

        void viewLoaded(int iOfficialTrip);

        void swpRefreshPressed(int iOfficialTrip);

        void driverListResult(int result);

        void confirmAlertSelectedDriver(String strunitid, int iofficialtrip);

        void assignDriverSelectedResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requesrListAssignDriver(int iOfficialTrip);


        void requestConfirmDriverAlert(String strunitid, int iofficialtrip);
    }
}
