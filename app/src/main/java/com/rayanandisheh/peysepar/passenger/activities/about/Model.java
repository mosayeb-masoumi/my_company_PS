package com.rayanandisheh.peysepar.passenger.activities.about;

import com.rayanandisheh.peysepar.passenger.BuildConfig;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;

    @Override
    public void attachPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }

}