package com.rayanandisheh.peysepar.passenger.fragment.bottom_sheet_fragment;

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
    public void viewLoaded() {
        model.requestTripTodayInfo();
    }

    @Override
    public void reportResult(int reportResult) {
        if(reportResult<1){
            Toaster.shorter(App.report.getStrMessage());
        }else if(reportResult == -4){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }else if(reportResult == -5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }else{
            view.setText();
        }
    }
}
