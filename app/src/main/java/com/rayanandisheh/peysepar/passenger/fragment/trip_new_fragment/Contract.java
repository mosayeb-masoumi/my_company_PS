package com.rayanandisheh.peysepar.passenger.fragment.trip_new_fragment;

import android.content.Context;
import android.widget.Toast;

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

        void swpNewTabLayoutPressed();

        void loadDataResult(int result);

        void cancelNewAlertTabLayoutTripManagement(int iOfficialTrip);

        void cencelTripAlertNewTabLayoutResult(int result);

        void confirmNewAlertTabLayoutTripManagement(int iOfficialTrip);

        void confirmTripAlertNewTabLayoutResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requesrListNewTabLayout();

        void requestCancelNewAlertTabLayoutTripManagement(int iOfficialTrip);

        void requestConfirmNewAlertTabLayoutTripManagement(int iOfficialTrip);
    }

  

}
