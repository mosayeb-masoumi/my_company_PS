package com.rayanandisheh.peysepar.passenger.activities.about_peysepar;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.BuildConfig;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter ,Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }
}
