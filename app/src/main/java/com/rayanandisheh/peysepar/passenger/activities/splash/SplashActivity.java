package com.rayanandisheh.peysepar.passenger.activities.splash;

import android.Manifest;
import android.app.AlertDialog;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.setting.SettingActivity;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;

public class SplashActivity extends mAppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context = this;
    ProgressBar progressBar;

    TextView txtVersionSplash;
    PackageInfo pinfo = null;
    String versionName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        bindViews();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        presenter.attachView(context, this);


        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//
        versionName = pinfo.versionName;

        txtVersionSplash.setText(String.format("نسخه : %s", versionName));



        //todo use setting Activity to change IP
//        App.ServerURL = Cache.getString("IP");
        App.ServerURL = Cache.getString("IP");
//
    }


    //moved codes in onCreate to onStart for resolve the problem of
    // checkPermission when we goto settingPermission and back to splash
    @Override
    protected void onStart() {
        super.onStart();

        if (App.ServerURL.equals("")) {
            context.startActivity(new Intent(context, SettingActivity.class));

        } else {

            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermission();

            }else{
                presenter.activityLoaded();
            }
        }
    }


    private void bindViews() {
        progressBar = findViewById(R.id.progressSplash);
        txtVersionSplash=findViewById(R.id.txtVersionSplash);
    }

    @Override
    public void finishActivity() {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitApp();
    }


    /*---------- close all app---------*/
    private void exitApp() {
        finish();
        startActivity(new Intent(Intent.ACTION_MAIN).
                addCategory(Intent.CATEGORY_HOME).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        android.os.Process.killProcess(android.os.Process.myPid());
        super.finish();
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this
                , new String[]{Manifest.permission.READ_PHONE_STATE}, 5);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 5:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.activityLoaded();
//                    dosomthing();
                } else {

                    showAccessPermissionDialog();
                }
                break;

        }
    }


    private void showAccessPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("عدم اجازه دسترسی");
        builder.setMessage(R.string.accessDeniedMessage);
        builder.setPositiveButton("برو به تنظیمات", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
                //mused below code for resolve the problem of
                // checkPermission when we goto settingPermission and back to splash
                SplashActivity.super.onStop();

            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }


}


