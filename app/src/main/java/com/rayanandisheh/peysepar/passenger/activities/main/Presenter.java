package com.rayanandisheh.peysepar.passenger.activities.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.about.AboutActivity;
import com.rayanandisheh.peysepar.passenger.activities.about_peysepar.AboutPeyseparActivity;
import com.rayanandisheh.peysepar.passenger.activities.dashboard.DashboardActivity;
import com.rayanandisheh.peysepar.passenger.activities.help.HelpActivity;
import com.rayanandisheh.peysepar.passenger.activities.history.HistoryActivity;
import com.rayanandisheh.peysepar.passenger.activities.add_trip.AddTripActivity;
import com.rayanandisheh.peysepar.passenger.activities.trip_management_new.TripManagementNewActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;

public class Presenter implements Contract.Presenter {

    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    int  currentVersion;



    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(context, this);
    }

    @Override
    public void viewLoaded() {

        model.checkUpdate();

//        model.loadCurrentTripList();
        if (App.currentTripSuccess) {
            model.loadCurrentTripList();
        } else if (App.listCurrentTrip.size() > 0) {

//            Model.DataLoaded();
            view.setAdapter();
            view.hideImageEmpety();

        }else if(App.listCurrentTrip.size() == 0)
            view.showImageEmpety();
    }


    @Override
    public void updateResult(int appVersion, int versionCodeMin) {
        try {
           currentVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {}


        if(currentVersion < versionCodeMin && App.userInfo.getDownloadLinkApp()!=null) {
            view.showForceUpdate();
        }
        else if(currentVersion<appVersion  && App.userInfo.getDownloadLinkApp()!=null){
            view.showAvailableUpdate();
        }

    }


    @Override
    public void updateRequest() {
        model.checkPermission();
    }

    @Override
    public void exitApp() {
        exitApplication();
    }

    @Override
    public void permissionResult(int result) {
        if (result == 1)
            model.update();
        else
            view.showPermissionAlert();
    }

    @Override
    public void requestPermission() {
        model.requestPermission();
    }

    @Override
    public void requestPermissionAccessed() {
        model.update();
    }


    @Override
    public void menuItemSelected(int id) {

//        if (id == R.id.nav_setting)
//            context.startActivity(new Intent(context, SettingsActivity.class));
            //       else if (id == R.id.nav_credit)
//            context.startActivity(new Intent(context, CreditActivity.class));
//        else if (id == R.id.nav_support) {
//            context.startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + context.getString(R.string.app_support_number))));
//            Toaster.longer(context.getString(R.string.callToSupport));


         if (id == R.id.nav_historyTrips)
            context.startActivity(new Intent(context, HistoryActivity.class));

//         else if (id == R.id.nav_tripManagement)
//             context.startActivity(new Intent(context, TripManagementActivity.class));

         else if (id == R.id.nav_tripManagementNew)
             context.startActivity(new Intent(context, TripManagementNewActivity.class));

         else if (id == R.id.nav_about)
            context.startActivity(new Intent(context, AboutActivity.class));

         else if (id == R.id.nav_tripDashboard)
             context.startActivity(new Intent(context, DashboardActivity.class));

//         else if (id == R.id.nav_vehiclesDashboard)
//             context.startActivity(new Intent(context, VehicleDashboardActivity.class));

         else if (id == R.id.nav_about_peysepar)
            context.startActivity(new Intent(context, AboutPeyseparActivity.class));

         else if (id == R.id.nav_guidance)
             context.startActivity(new Intent(context, HelpActivity.class));

//        else if (id == R.id.nav_feedback)
//            context.startActivity(new Intent(context, FeedbackActivity.class));

//        else if (id == R.id.nav_changeIP)
//            context.startActivity(new Intent(context, SettingActivity.class));

//        else if (id == R.id.nav_signature)
//            context.startActivity(new Intent(context, MapsActivity2.class));


//        else if (id == R.id.nav_share) {
//            context.startActivity(Intent.createChooser(new Intent(android.content.Intent.ACTION_SEND).setType("text/plain").
//                            putExtra(android.content.Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.app_name_persian)).
//                            putExtra(android.content.Intent.EXTRA_TEXT,
//                                    context.getString(R.string.app_share_content) + "\n" +
//                                            context.getResources().getString(R.string.app_share_link))
//                    , context.getString(R.string.app_share_title)));
//        }
    }

    @Override
    public void fabADDpressed() {
        context.startActivity(new Intent(context, AddTripActivity.class));
    }


    @Override
    public void checkNightMode() {
        if (App.modeChanged) {
            App.modeChanged = false;
            ((Activity) context).finish();
            context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
//        }
        }
    }

    @Override
    public void currentTripResult(int result) {
        view.hideProgressBar();
        view.hideSwipeRefresh();
        if (result == -4) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == -5) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        } else if (result == 0) {
               view.showImageEmpety();
        } else if (result==1){
            view.setAdapter();

            if(App.listCurrentTrip.size()>0)
            view.hideImageEmpety();
            else
                view.showImageEmpety();
        }else{
            view.showImageEmpety();
        }
    }

    @Override
    public void showPregressbar() {
        view.showProgressBar();
    }

    @Override
    public void swipRefreshPressed() {
        view.showSwipeRefresh();
        model.loadCurrentTripList();

    }


    private void exitApplication() {
        context.startActivity(new Intent(Intent.ACTION_MAIN).
                addCategory(Intent.CATEGORY_HOME).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        App.isRun = false; // for don't start location service onDestroy
        android.os.Process.killProcess(android.os.Process.myPid());
        ((Activity) context).finish();
    }


    @Override
    public void menuPressed(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            drawerLayout.openDrawer(GravityCompat.START);
    }

}
