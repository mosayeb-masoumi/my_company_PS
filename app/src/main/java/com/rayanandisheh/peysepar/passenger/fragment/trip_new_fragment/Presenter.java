package com.rayanandisheh.peysepar.passenger.fragment.trip_new_fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.main.MainActivity;
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
    public void viewLoaded() {

        if (App.newTabLayoutListSuccess) {
            view.showSwipeRefresh();
            model.requesrListNewTabLayout();

        } else if (App.listNewTabLayoutTrip.size() > 0) {
//            view.setAdapter();
            view.hideImg_noItem();
        } else if(App.listNewTabLayoutTrip.size()==0){
            view.showImg_noItem();
        }

    }

    @Override
    public void swpNewTabLayoutPressed() {
        view.showSwipeRefresh();
        model.requesrListNewTabLayout();
    }

    @Override
    public void loadDataResult(int result) {
//        view.stopProgressLoading();
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
            if(App.listNewTabLayoutTrip.size()>0)
                view.hideImg_noItem();
            else
                view.showImg_noItem();

        }
    }

    @Override
    public void cancelNewAlertTabLayoutTripManagement(int iOfficialTrip) {
        view.showSwipeRefresh();
        model.requestCancelNewAlertTabLayoutTripManagement(iOfficialTrip);
    }

    @Override
    public void cencelTripAlertNewTabLayoutResult(int result) {
        view.hideSwipeRefresh();
        if (result == -4) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == -5) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        } else{
            Toaster.shorter("سفر شما با موفقیت لغو گردید");
            //to update list
            model.requesrListNewTabLayout();
        }


    }

    @Override
    public void confirmNewAlertTabLayoutTripManagement(int iOfficialTrip) {
        view.showSwipeRefresh();
        model.requestConfirmNewAlertTabLayoutTripManagement(iOfficialTrip);
    }

    @Override
    public void confirmTripAlertNewTabLayoutResult(int result) {
        view.hideSwipeRefresh();
        if(result == 1){
            Toaster.shorter(App.data.getMessage());

            view.showSwipeRefresh();
            model.requesrListNewTabLayout();

//            context.startActivity(new Intent(context,MainActivity.class));
//            context.startActivity(new Intent(context,TripConfirmedFragment.class));




        }else if(result== -2){
            Toaster.shorter(App.data.getMessage());
        }else if(result== -1){
            Toaster.shorter(App.data.getMessage());
        }else if(result==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }
    }


}
