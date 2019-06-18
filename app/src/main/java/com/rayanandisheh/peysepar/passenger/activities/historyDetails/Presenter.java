package com.rayanandisheh.peysepar.passenger.activities.historyDetails;

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
        model.attachPresenter(this,context);
    }

    @Override
    public void sendRate(float rating, int iOfficialTrip) {
        view.showProgressBar();
        model.requestSendTodayRating(rating , iOfficialTrip);
    }

    @Override
    public void changeScoreResult(int result) {
        view.hideProgressBar();
        if(result==-4){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }else if(result==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }else if(result==1){
//            Toaster.shorter("امتیاز شما ثبت گردید");
            Toaster.shorter(App.data.getMessage());

        }else if(result==-1){
//            Toaster.shorter("امتیاز شما ثبت گردید");
            Toaster.shorter(App.data.getMessage());

           view.setTodayScore();
        }
    }
}
