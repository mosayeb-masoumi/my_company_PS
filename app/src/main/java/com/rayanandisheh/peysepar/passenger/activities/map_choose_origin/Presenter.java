package com.rayanandisheh.peysepar.passenger.activities.map_choose_origin;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.maps.model.LatLng;

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
    public void btnRegisterPressed(LatLng position) {
//         view.showProgressBar();
        if (position == new LatLng(0, 0))
            saved();
        else
            model.saveSelectedLatLng(position);
    }

    @Override
    public void saved() {
//        view.hideProgressBar();
        ((Activity) context).finish();
    }
}
