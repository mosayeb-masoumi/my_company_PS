package com.rayanandisheh.peysepar.passenger.activities.splash;

import android.content.Context;

public interface Contract {

    interface View {

        void finishActivity();
    }

    interface Presenter {

        void attachView(Context context, View view);

        void activityLoaded();

        void loginResult(int result);
    }

    interface Model {

        void attachPresenter(Presenter presenter,Context context);

        boolean hasCachedUser();

        void login();

        void saveUserInfo(int result);

        void cacheMobilePhone();
    }
}
