package com.rayanandisheh.peysepar.passenger.fragment.trip_dashboard_fragment;

import android.content.Context;

public interface Contract {

    interface View{


        void hideSwipRefresh();

        void showSwpRefresh();

        void ResultOK(android.view.View view);
    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded(android.view.View view);

        void dashboardResult(int result, android.view.View view);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requestLoadingDashboardInfos(android.view.View view);

   
    }
}
