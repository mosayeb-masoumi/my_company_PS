package com.rayanandisheh.peysepar.passenger.fragment.trip_cancel_fragment;

import android.content.Context;

public interface Contract {
    interface View{

        void showSwipeRefresh();

        void hideImg_noItem();

        void showImg_noItem();

        void hideSwipeRefresh();

        void setAdapter();


        void hideSwipRefresh();
    }

    interface Presenter {

        void attachView (Context context,View view);


        void viewLoaded();

        void swp_refreshPressed();

        void loadDataResult(int result);

        void cancelFragmentConfirmAlert(int iOfficialTrip);

        void cencelTripResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requesrListCanceledTripTabLayout();

        void requestAlertConfirmation(int iOfficialTrip);
    }
}
