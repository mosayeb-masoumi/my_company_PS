package com.rayanandisheh.peysepar.passenger.activities.settings;

import android.content.Context;

public interface Contract {

    interface View{

        void setNightModeSwitch(boolean currentNightModel);
    }

    interface Presenter {

        void attachView (Context context,View view);

        void setNightModeTheme(boolean isChecked);

        void viewLoaded();
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

    }
}
