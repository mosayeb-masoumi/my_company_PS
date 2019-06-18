package com.rayanandisheh.peysepar.passenger.activities.about_peysepar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.rayanandisheh.peysepar.passenger.R;

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
    public void brandClicked() {
        context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(context.getString(R.string.app_link))));
    }

    @Override
    public void activityLoaded() {
//        view.showAppVersion(context.getString(R.string.version) + " " + model.getAppVersion());
        view.showAppVersion();
    }
}
