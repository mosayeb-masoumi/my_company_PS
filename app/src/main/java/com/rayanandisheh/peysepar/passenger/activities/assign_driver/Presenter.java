package com.rayanandisheh.peysepar.passenger.activities.assign_driver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.fragment.trip_confirmed_fragment.TripConfirmedFragment;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;

public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this,context);
    }

    @Override
    public void viewLoaded(int iOfficialTrip) {
        if (App.assignDriverListSuccess) {
            view.showSwipeRefresh();
            model.requesrListAssignDriver(iOfficialTrip);

        } else if (App.listAssignDriverConfirmedTabLayout.size() > 0) {
//            view.setAdapter();
            view.hideImg_noItem();
        } else if(App.listAssignDriverConfirmedTabLayout.size()==0){
            view.showImg_noItem();
        }
    }

    @Override
    public void swpRefreshPressed(int iOfficialTrip) {
        view.showSwipeRefresh();
        model.requesrListAssignDriver(iOfficialTrip);
    }

    @Override
    public void driverListResult(int result) {
        view.hideSwipeRefresh();
        if (result == -4)
            Toaster.shorter(context.getString(R.string.serverFaield));
        else if (result == -5)
            Toaster.shorter(context.getString(R.string.connectionFaield));
        else if(result==0)
            view.showImg_noItem();
        else if(result==1){
            view.setAdapter();
            view.hideImg_noItem();
            if(App.listAssignDriverConfirmedTabLayout.size()>0)
                view.hideImg_noItem();
            else
                view.showImg_noItem();

        }
    }

    @Override
    public void confirmAlertSelectedDriver(String strunitid, int iofficialtrip) {

        model.requestConfirmDriverAlert(strunitid,iofficialtrip);
    }

    @Override
    public void assignDriverSelectedResult(int result) {
        view.hideSwipeRefresh();
        if (result == -4)
            Toaster.shorter(context.getString(R.string.serverFaield));
        else if (result == -5)
            Toaster.shorter(context.getString(R.string.connectionFaield));
        else if(result==1) {
            Toaster.shorter(App.data.getMessage());
            ((Activity) context).finish();
        }
        else if(result==-1)
            Toaster.shorter(App.data.getMessage());
    }


//    @Override
//    public void loadDataResult(int result) {
//        view.hideSwipeRefresh();
//        if (result == -4)
//            Toaster.shorter(context.getString(R.string.serverFaield));
//        else if (result == -5)
//            Toaster.shorter(context.getString(R.string.connectionFaield));
//        else if(result==0)
//            view.showImg_noItem();
//        else if(result==1){
//            view.setAdapter();
//            view.hideImg_noItem();
//            if(App.listConfirmedTripTabLayout.size()>0)
//                view.hideImg_noItem();
//            else
//                view.showImg_noItem();
//
//        }
//    }
}
