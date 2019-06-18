package com.rayanandisheh.peysepar.passenger.activities.settings;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import cn.like.nightmodel.NightModelManager;
import com.rayanandisheh.peysepar.passenger.helpers.App;

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
    public void setNightModeTheme(boolean isChecked) {
        App.modeChanged = true;
        if (!isChecked) {
            NightModelManager.getInstance().applyDayModel((AppCompatActivity) context);
        } else {
            NightModelManager.getInstance().applyNightModel((AppCompatActivity) context);
        }

    }



    @Override
    public void viewLoaded() {
            view.setNightModeSwitch((NightModelManager.getInstance().isCurrentNightModel(context)));
    }
}
