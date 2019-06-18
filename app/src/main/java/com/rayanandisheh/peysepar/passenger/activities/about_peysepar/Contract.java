package com.rayanandisheh.peysepar.passenger.activities.about_peysepar;

import android.content.Context;

public interface Contract {

    interface View{

        void showAppVersion();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void brandClicked();

        void activityLoaded();
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        String getAppVersion();
    }
}
