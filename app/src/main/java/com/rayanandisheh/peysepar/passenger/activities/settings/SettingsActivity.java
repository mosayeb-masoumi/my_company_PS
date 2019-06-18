package com.rayanandisheh.peysepar.passenger.activities.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.support.v7.widget.Toolbar;
import cn.like.nightmodel.NightModelManager;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;

public class SettingsActivity extends mAppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    Switch switchNightMode;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        NightModelManager.getInstance().attach(SettingsActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = this;
        presenter.attachView(context, this);

        bind();

        presenter.viewLoaded();

        switchNightMode.setOnCheckedChangeListener((v, isChecked) -> presenter.setNightModeTheme(isChecked));
    }


    private void bind() {
        toolbar = findViewById(R.id.toolbar_setting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setTitle("تنظیمات");
        switchNightMode = findViewById(R.id.switchNightMode);
    }

    @Override
    public void setNightModeSwitch(boolean currentNightModel) {
        switchNightMode.setChecked(currentNightModel);
    }


    @Override
    protected void onDestroy() {
        NightModelManager.getInstance().detach(this);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
