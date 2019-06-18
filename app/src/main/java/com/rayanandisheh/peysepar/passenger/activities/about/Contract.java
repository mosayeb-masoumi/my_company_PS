package com.rayanandisheh.peysepar.passenger.activities.about;

import android.content.Context;

public interface Contract {

    interface View {

        void showAppVersion(String appVersion);
    }

    interface Presenter {

        void attachView(Context context, View view);

        void activityLoaded();


        void tvCoUrlPressed();
    }

    interface Model {

        void attachPresenter(Presenter presenter);

        String getAppVersion();
    }
}
