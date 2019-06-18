package com.rayanandisheh.peysepar.passenger.activities.add_trip;

import android.content.Context;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;


public interface Contract {

    interface View{

//
//        void setSpnOriginData(List<String> spnOriginData);

        void showIvWarningOrigin();

        void hideIvWarningOrigin();

//        void setSpnDesData(List<String> spnDesData);

        void setSpnReasonData(List<String> spnReasonData);

        void setSpnImportanceData(List<String> importanceData);

        void showIvWarningDes();

        void hideIvWarningDes();

        void showIvWarningReason();

        void hideIvWarningReason();

        void showIvWarningImportance();

        void hideIvWarningImportance();

        void getOriginDes(ArrayAdapter<String> adapter);

        void setOriginDes(List<String> originDes);

        void HideDestination();

        void showDestination();

        void showProgressBar();

        void hidePreogressBar();

        void setSpnCarType(List<String> spnTypeCar);
    }

    interface Presenter {
        void attachView (Context context,View view);
        void cvCalandarPressed(EditText edtDate);

        void spinnerTripReasonPressed(Spinner spnReason);

        void spinnerTripImportancePressed(Spinner spnImportance);

        void viewLoaded();

        void chkInServiceChecked(boolean chkInServiceisChecked);

        void btnPressed(int spnReason, int spnImportance, int spnCarType, String autoCompleteTxtOrigin, String autoCompleteTxtDes, String edtDate, String numberPickerTime, boolean chkBoxInService, boolean chkBxReturn, boolean chkBxMissionary, String list, String strComment);

        void tripInsertResult(Integer result);

        void noValidDes();

        void noValidOrigin();

        void ErrorEqualOriginDestination();
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        List<String> getSpnOriginData();

        List<String> getSpnDesData();

        List<String> getSpnReasonData();

        List<String> getImportanceData();

        List<String> getOriginDes();

        void requestAddTrip(int spnReason, int spnImportance, int spnCarType, String autoCompleteTxtOrigin, String autoCompleteTxtDes, String edtDate, String numberPickerTime, boolean chkBoxInService, boolean chkBxReturn, boolean chkBxMissionary, String list, String strComment);


        List<String> getSpnTypeCar();
    }
}
