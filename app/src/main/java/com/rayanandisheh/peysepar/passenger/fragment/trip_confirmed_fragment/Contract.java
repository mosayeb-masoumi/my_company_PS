package com.rayanandisheh.peysepar.passenger.fragment.trip_confirmed_fragment;

import android.content.Context;

public interface Contract {
    interface View{


        void hideImg_noItem();

        void showImg_noItem();

        void showSwipeRefresh();

        void hideSwipeRefresh();

        void setAdapter();
    }

    interface Presenter {

        void attachView (Context context,View view);


        void viewLoaded();

        void swpRefreshPressed();

        void loadDataResult(int result);

        void cancelConfirmedTripAlertTabLayoutTripManagement(int iOfficialTrip);

        void cencelAlertResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requesrListConfimedTripTabLayout();


        void requestCancelConfirmedTripAlertTabLayout(int iOfficialTrip);
    }
}
