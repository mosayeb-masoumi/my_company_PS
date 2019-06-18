package com.rayanandisheh.peysepar.passenger.activities.setting;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.R;
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
    public void btnCheckaddressPressed(String etIP) {


        view.showProgressBar();
        model.checkAddressRequest(etIP);
    }

    @Override
    public void checkAddressResult(Integer checkAddresessResult) {
        view.hideProgressBar();
        if(checkAddresessResult==1)
        Toaster.shorter("آدرس اینترنتی وارد شده صحیح می باشد");
        else if(checkAddresessResult==0)
            Toaster.shorter("آدرس اینترنتی وارد شده صحیح نمی باشد");
        else if(checkAddresessResult==-5)
            Toaster.shorter(context.getString(R.string.connectionFaield));
        else if(checkAddresessResult==-4)
            Toaster.shorter(context.getString(R.string.serverFaield));
    }
}
