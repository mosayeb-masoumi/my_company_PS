package com.rayanandisheh.peysepar.passenger.fragment.dashboard_vehicles;

import android.content.Context;

public interface Contract {
    interface View{

        void showSwpRefresh();

        void hideSwipRefresh();

        void ResultOK();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded();

        void dashboardCarResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requestLoadingDashboardInfos();
    }
}
