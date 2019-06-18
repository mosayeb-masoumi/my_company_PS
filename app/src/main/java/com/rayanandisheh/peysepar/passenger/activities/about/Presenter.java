package com.rayanandisheh.peysepar.passenger.activities.about;

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
        model.attachPresenter(this);
    }

    @Override
    public void activityLoaded() {
        view.showAppVersion(context.getString(R.string.version) + " " + model.getAppVersion());
    }



    @Override
    public void tvCoUrlPressed() {
        context.startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.rayanandisheh.com")));
    }


}
