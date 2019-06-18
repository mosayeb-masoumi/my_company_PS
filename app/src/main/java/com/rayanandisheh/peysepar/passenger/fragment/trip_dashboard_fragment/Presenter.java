package com.rayanandisheh.peysepar.passenger.fragment.trip_dashboard_fragment;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
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
    public void viewLoaded(View view) {
        this.view.showSwpRefresh();
        model.requestLoadingDashboardInfos(view);
    }

    @Override
    public void dashboardResult(int result, View view) {
        this.view.hideSwipRefresh();
        if(result==-4){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }else if(result==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }else if(result==1){

            this.view.ResultOK(view);
        }else{
            Toast.makeText(context, App.dashboard.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
