package com.rayanandisheh.peysepar.passenger.fragment.trip_draiver_confirmed_fragment;

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
        if (App.waitingDriverConfirmTabLayoutListSuccess) {
            view.showSwipeRefresh();
            model.requesrListWaitingDriverTabLayout();

        } else if (App.listWaitingDriverConfirmTabLayout.size() > 0) {
//            view.setAdapter();
            view.hideImg_noItem();
        } else if(App.listWaitingDriverConfirmTabLayout.size()==0){
            view.showImg_noItem();
        }
    }

    @Override
    public void swp_refreshPressed() {
        view.showSwipeRefresh();
        model.requesrListWaitingDriverTabLayout();
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
            if(App.listWaitingDriverConfirmTabLayout.size()>0)
                view.hideImg_noItem();
            else
                view.showImg_noItem();

        }
    }

    @Override
    public void cancelDriverConfirmedAlertTabLayoutTripManagement(int iOfficialTrip) {
        view.showSwipeRefresh();
        model.requestCancelDriverConfirmedAlertTabLayoutTripManagement(iOfficialTrip);
    }

    @Override
    public void cencelTripAlertResult(int result) {
        view.hideSwipeRefresh();
        if (result == -4) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == -5) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        } else{
            Toaster.shorter("سفر شما با موفقیت لغو گردید");
            //to update list
            model.requesrListWaitingDriverTabLayout();
        }
    }


}
