package com.rayanandisheh.peysepar.passenger.fragment.dashboard_trips;

import android.content.Context;

public interface Contract {
    interface View{

        void showSwpRefresh();

        void ResultOK();

        void hideSwipRefresh();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded();

        void dashboardTripResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requestLoadingDashboardInfos();
    }
}
