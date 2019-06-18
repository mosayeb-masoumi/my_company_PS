package com.rayanandisheh.peysepar.passenger.fragment.trip_draiver_confirmed_fragment;

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

        void attachView (Context context,View view);

        void viewLoaded();

        void swp_refreshPressed();

        void loadDataResult(int result);

        void cancelDriverConfirmedAlertTabLayoutTripManagement(int iOfficialTrip);

        void cencelTripAlertResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requesrListWaitingDriverTabLayout();

        void requestCancelDriverConfirmedAlertTabLayoutTripManagement(int iOfficialTrip);
    }
}
