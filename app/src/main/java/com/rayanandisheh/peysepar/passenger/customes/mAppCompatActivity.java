package com.rayanandisheh.peysepar.passenger.customes;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.rayanandisheh.peysepar.passenger.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class mAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("IRANSansMobile.ttf").setFontAttrId(R.attr.fontPath).build());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
