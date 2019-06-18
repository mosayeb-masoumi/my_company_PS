package com.rayanandisheh.peysepar.passenger.fragment.dashboard_vehicles;

import android.content.Context;
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
    public void viewLoaded() {
        view.showSwpRefresh();
        model.requestLoadingDashboardInfos();
    }

    @Override
    public void dashboardCarResult(int result) {
        view.hideSwipRefresh();
        if(result==-4){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }else if(result==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }else if(result==1){

            view.ResultOK();
        }else{
            Toast.makeText(context, App.dashboard.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
