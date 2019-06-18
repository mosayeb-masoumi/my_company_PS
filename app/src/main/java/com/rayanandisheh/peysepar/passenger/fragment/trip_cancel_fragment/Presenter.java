package com.rayanandisheh.peysepar.passenger.fragment.trip_cancel_fragment;

import android.app.Activity;
import android.content.Context;
import com.rayanandisheh.peysepar.passenger.R;
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
        model.attachPresenter(this, context);
    }

    @Override
    public void viewLoaded() {
        if (App.CanceledTripTabLayoutListSuccess) {
            view.showSwipeRefresh();
            model.requesrListCanceledTripTabLayout();

        } else if (App.listCanceledTabLayout.size() > 0) {
//            view.setAdapter();
            view.hideImg_noItem();
        } else if(App.listNewTabLayoutTrip.size()==0){
            view.showImg_noItem();
        }
    }

    @Override
    public void swp_refreshPressed() {
        view.showSwipeRefresh();
        model.requesrListCanceledTripTabLayout();
    }

    @Override
    public void loadDataResult(int result) {
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
            if(App.listCanceledTabLayout.size()>0)
                view.hideImg_noItem();
            else
                view.showImg_noItem();

        }
    }

    @Override
    public void cancelFragmentConfirmAlert(int iOfficialTrip) {
        view.showSwipeRefresh();
        model.requestAlertConfirmation(iOfficialTrip);
    }

    @Override
    public void cencelTripResult(int result) {
        view.hideSwipRefresh();
        if(result==1) {
            Toaster.shorter("سفر با موفقیت لغو شد");
            view.showSwipeRefresh();
            model.requesrListCanceledTripTabLayout();
//            ((Activity)context).finish();
        }else if(result==0){
            Toaster.shorter("شما قادر به لغو سفر نیستید");
        }else if(result==-1){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }else if(result==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }
    }


}
