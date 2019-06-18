package com.rayanandisheh.peysepar.passenger.activities.currentTripDetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
import com.rayanandisheh.peysepar.passenger.models.Trip;

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
    public void btnCanceledPressed(Trip iOfficialTrip) {
        view.showProgressBar();
        model.cancelTripRequest(iOfficialTrip);
    }

    @Override
    public void cencelTripResult(Integer cancelTripResult) {

        view.hideProgressBar();
        if(cancelTripResult==1) {
            Toaster.shorter("سفر با موفقیت لغو شد");
            ((Activity)context).finish();
        }else if(cancelTripResult==0){
            Toaster.shorter("شما قادر به لغو سفر نیستید");
        }else if(cancelTripResult==-1){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }else if(cancelTripResult==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }
    }


    @Override
    public void btnCallPressed(String driverMobile) {

        model.btnCallPressed(driverMobile);

    }

}
