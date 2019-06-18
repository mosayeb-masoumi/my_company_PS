package com.rayanandisheh.peysepar.passenger.activities.signature;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.widget.RelativeLayout;
import com.rayanandisheh.peysepar.passenger.helpers.DrawView;

public interface Contract {
    interface View{

        void showSignatureAvailable(DrawView drawView);

        void showProgressBar();

        void hideProgressbar();

    }

    interface Presenter {

        void attachView (Context context,View view);

        void loadView(RelativeLayout parent, FloatingActionButton fab);

        void btnRegisterPressed(float rating);

        void endTripResult(int result);

        void requestStart();
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void saveAndFinish(Bitmap bitmap, float rating);
    }
}
