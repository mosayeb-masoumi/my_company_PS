package com.rayanandisheh.peysepar.passenger.activities.setting;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.splash.SplashActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
import com.rayanandisheh.peysepar.passenger.helpers.Validate;
import com.rayanandisheh.peysepar.passenger.services.APIClient;

public class SettingActivity extends AppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    EditText etIP;
    Button btRegisterIP , btn_checkAddress;
    ProgressBar pbCheckAddress;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        context = this;
        presenter.attachView(context, this);

        bindViews();



        btn_checkAddress.setOnClickListener(v -> {
            APIClient.retrofit = null;
            App.ServerURL= etIP.getText().toString();
            presenter.btnCheckaddressPressed(etIP.getText().toString());
            //            http://passenger.peysepar.com

        });


//        etIP.setText("http://192.168.1.109:1000");

//        etIP.setText(R.string.IPaddress);



        btRegisterIP.setOnClickListener(v -> {
            String ip=etIP.getText().toString();
                if(Validate.url(ip)) {
                    APIClient.retrofit = null;

                    App.ServerURL = ip;
                    Cache.setString("IP", ip);

                         //clause to solve problem of jumping in splash
                           if(App.ServerURL!=null)
                           startActivity(new Intent(SettingActivity.this, SplashActivity.class));
                           else
                               Toaster.shorter("ServerUrl null");



                    finish();
                }
              else
                    etIP.setError("آدرس را با توجه به مثال زده شده وارد نمایید");
//                    etIP.setError("آدرس خود را وارد نمایید");
        });
    }


    private void bindViews() {
        etIP=findViewById(R.id.etIP);
        btRegisterIP=findViewById(R.id.btRegisterIP);
        pbCheckAddress=findViewById(R.id.pbCheckAddress);
        btn_checkAddress=findViewById(R.id.btnCheckAddress);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            exitApp();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "برای خروج مجددا دکمه ی بازگشت را بزنید", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 3000);
    }

    private void exitApp() {
        finish();
        startActivity(new Intent(Intent.ACTION_MAIN).
                addCategory(Intent.CATEGORY_HOME).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        android.os.Process.killProcess(android.os.Process.myPid());
        super.finish();
    }





    @Override
    protected void onResume() {
        super.onResume();
        if(Cache.getString("IP").equals(""))
//        etIP.setText(R.string.IPaddress);
        etIP.setText(R.string.IPaddress);
        else
            etIP.setText(Cache.getString("IP"));
    }

    @Override
    public void showProgressBar() {
        pbCheckAddress.setVisibility(View.VISIBLE);
        btn_checkAddress.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        pbCheckAddress.setVisibility(View.GONE);
        btn_checkAddress.setVisibility(View.VISIBLE);
    }




}
