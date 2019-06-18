package com.rayanandisheh.peysepar.passenger.activities.profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.EditText;


public interface Contract {
    interface View{

        void hideProgressBar();

        void showProggressBar();

        void showNameError();

        void showFamilyError();

        void showNcodeError();

        void showImageErrorName();

        void hideImageErrorName();

        void showImageErrorFamily();

        void hideImageErrorFamily();

        void showImageErrorNcode();

        void hideImageErrorNcode();

        void showDialogeToTurnOnGPS();

        void showErrorEmpetyEdtPermanentAddress();
    }

    interface Presenter {

        void attachView (Context context,View view);
        void showSelectPicDialog();


        void cvCalandarPressed(EditText edtDate);

        void btnPressed( String name, String family, String nationalID,String PermanentOriginAddress, boolean rbManchecked, Bitmap bmProfile);

        void updateProfileResulr(int result);

        void viewLoaded(EditText name, EditText family, EditText nCode);

        void rlChoosePermanentOriginPressed();

        void showGPSsetting();

        void requestResult(int requestCode, String[] permissions, int[] grantResults);

        void refreshResult(int refreshResult);


//        void btnPressed(String name, String date, boolean rbMan, Bitmap bm);
    }

    interface Model{
        void attachPresenter (Presenter presenter ,Context context);

        void requestProfile(String name, String family, String nationalID, String permanentOriginAddress, boolean rbManchecked, Bitmap bmProfile);

        boolean checkPermission();

        boolean checkGPSturnOn();

        void saveLATLNG_AddreesName();

        void requestRefresh();


//        void requestProfile(String name, String date, boolean rbMan, Bitmap bm);
    }
}
