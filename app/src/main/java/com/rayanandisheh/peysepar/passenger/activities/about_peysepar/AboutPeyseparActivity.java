package com.rayanandisheh.peysepar.passenger.activities.about_peysepar;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.BuildConfig;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;

public class AboutPeyseparActivity extends AppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    ImageView ivIcon;
    TextView tvAppName,tvAppVersion;

    String versionName;
    PackageInfo pinfo = null;
    int versionNumber;
    PackageManager packageManager;




    int currentVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_peysepar);

        context = this;
        presenter.attachView(context, this);

        presenter.activityLoaded();
        bindView();


        ivIcon.setOnClickListener(v -> presenter.brandClicked());
        tvAppName.setOnClickListener(v -> presenter.brandClicked());



        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//
       versionName = pinfo.versionName;

        try {
            currentVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;

        } catch (PackageManager.NameNotFoundException e) {}
        tvAppVersion.setText(versionName);
//        tvAppVersion.setText(App.userInfo.getVersionName());

    }

    private void bindView() {
        ivIcon=findViewById(R.id.ivIcon);
        tvAppName=findViewById(R.id.tvAppName);
        tvAppVersion=findViewById(R.id.tvAppVersion);
    }

    @Override
    public void showAppVersion() {
//todo get versionName from server and set here
//        tvAppVersion.setText(versionNumber);
    }
}
