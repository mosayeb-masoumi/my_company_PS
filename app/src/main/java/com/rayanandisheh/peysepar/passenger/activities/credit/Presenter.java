package com.rayanandisheh.peysepar.passenger.activities.credit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatEditText;

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
    public void btnIncCreditPressed(AppCompatEditText edtValue) {
        view.showProgress();
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")));
    }

}