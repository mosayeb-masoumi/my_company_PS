package com.rayanandisheh.peysepar.passenger.activities.main;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;

public interface Contract {

    interface View {


        void showAvailableUpdate();

        void showForceUpdate();

        void showPermissionAlert();


        void hideProgressBar();

        void setAdapter();

        void showProgressBar();

        void showSwipeRefresh();

        void hideSwipeRefresh();

        void showImageEmpety();

        void hideImageEmpety();

    }

    interface Presenter {
        
        void attachView(Context context, View view);

        void viewLoaded();


        void menuPressed(DrawerLayout drawerLayout);


        void updateResult(int appVersion, int versionCodeMin);

        void updateRequest();

        void exitApp();

        void permissionResult(int result);

        void requestPermission();

        void requestPermissionAccessed();



        void menuItemSelected(int itemId);

        void fabADDpressed();


        void checkNightMode();

        void currentTripResult(int result);

        void showPregressbar();


        void swipRefreshPressed();
    }

    interface Model {
        
        void attachPresenter(Context context, Presenter presenter);

        void checkUpdate();

        void checkPermission();

        void update();

        void requestPermission();


        void loadCurrentTripList();
    }
}