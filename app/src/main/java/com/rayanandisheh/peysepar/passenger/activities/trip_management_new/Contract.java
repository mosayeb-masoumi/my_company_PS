package com.rayanandisheh.peysepar.passenger.activities.trip_management_new;

import android.content.Context;
import android.widget.AdapterView;

public interface Contract {
    interface View{

        void ShowSwpRefresh();

        void HideSwpRefresh();

        void showImg_noItem();

        void hideImg_noItem();

        void setAdapter(int position);
    }

    interface Presenter {

        void attachView (Context context,View view);

        void spnSelected(AdapterView<?> parent, android.view.View view, int position, long id);

        void loadDataResult(int result, int position);

        void swpRefreshPressed(int position);

        void imgArrowBackPressed();

        void viewLoded(int spnPosition);

        void cancelNewAlertTripManagement(int iOfficialTrip, int tiTripStatus);

//        void cencelNewTripAlertResult(int result, int position);

        void confirmNewAlertTripManagement(int iOfficialTrip);

        void confirmNewTripAlertResult(int result, int position);

        void cancelConfirmedTripAlertTripManagement(int iOfficialTrip, int tiTripStatus);


        void cencelConfirmTripAlertResult(int result, int position);

        void cancelWaitingDriverConfirmedAlertTripManagement(int iOfficialTrip, int tiTripStatus);

        void cencelWaitinDriverConfirmTripAlertResult(int result, int position);

        void updateList(int position);

        void confirm_CancelTripAlert(int iOfficialTrip);

        void cencelTripAlertConfirmResult(int result, int position);

        void cencelNewTripAlertResult(int result, int position);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requesrListNewTrips(int position);

        void requestCancelNewAlertTripManagement(int iOfficialTrip, int position, int tiTripStatus);

        void requestConfirmNewAlertTripManagement(int iOfficialTrip, int position);

        void requestCancelConfirmedTripAlertTripManagement(int iOfficialTrip, int position, int tiTripStatus);

        void requestCancelWaitingDriverConfirmedAlertTripManagement(int iOfficialTrip, int position, int tiTripStatus);

        void requestCancelTripConfim(int iOfficialTrip, int position);
    }
}
