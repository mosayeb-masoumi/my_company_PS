package com.rayanandisheh.peysepar.passenger.activities.login;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.activation.ActivationActivity;
import com.rayanandisheh.peysepar.passenger.activities.main.MainActivity;
import com.rayanandisheh.peysepar.passenger.activities.register.RegisterActivity;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.helpers.DeviceInfo;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;

import java.text.MessageFormat;

public class LoginActivity extends mAppCompatActivity implements Contract.View {

    EditText etMobileNumber;
    Button btLogin;
    ProgressBar pbLogin;
    //    TextView tvRegister, tvForgotPassword, tvForgetLoading;
    TextView tvRegister, tvForgetLoading,txtVersionLogin;
    Contract.Presenter presenter = new Presenter();
    Context context = this;
    CountDownTimer countDownTimer = null;

    ImageView ivWarningMobileLogin;
    View view;

    PackageInfo pinfo = null;
    String versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter.attachView(context, this);

        bindViews();


//        view=findViewById(R.id.vHeader);
//        view.setOnClickListener(v -> {
//            context.startActivity(new Intent(context,MainActivity.class));
//        });

        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//
        versionName = pinfo.versionName;
        txtVersionLogin.setText(versionName);


        if (!Cache.getString("mobileNumber").equals("")) {
            etMobileNumber.setText(Cache.getString("mobileNumber"));
        } else {
            etMobileNumber.setText("");
        }


        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() != 11)
                    ivWarningMobileLogin.setVisibility(View.GONE);
                else
                    ivWarningMobileLogin.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



//        btLogin.setOnClickListener(v -> validateAndLogin());


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED ){

                    requestPermission();
                }else{
                    validateAndLogin();
//            dosomthing();
                }
            }
        });













        etMobileNumber.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        validateAndLogin();
                        return true;
                    default:
                        break;
                }
            }
            return false;
        });

    }

    private void bindViews() {
        etMobileNumber = findViewById(R.id.etMobileNumber);
//        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        pbLogin = findViewById(R.id.pbLogin);
        tvRegister = findViewById(R.id.tvRegister);
//        tvForgotPassword = findViewById(R.id.tvForgotPass);
//        tvForgetLoading = findViewById(R.id.tvForgotPassLoading);
        ivWarningMobileLogin = findViewById(R.id.ivWarningMobileLogin);
        txtVersionLogin=findViewById(R.id.txtVersionLogin);

        tvRegister.setOnClickListener(v -> presenter.register());


    }

    private void validateAndLogin() {

//        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        App.mobileFirstRunning = etMobileNumber.getText().toString();
        presenter.login(etMobileNumber.getText().toString());
    }

    @Override
    public void startLoading() {
        btLogin.setVisibility(View.GONE);
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        pbLogin.setVisibility(View.GONE);
        btLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void startForgotLoading() {
//        tvForgotPassword.setVisibility(View.GONE);
        tvForgetLoading.setVisibility(View.VISIBLE);
        if (countDownTimer != null)
            countDownTimer.cancel();
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvForgetLoading.setText(MessageFormat.format("{0} {1} {2}", getString(R.string.after), millisUntilFinished / 1000, getString(R.string.secends)));
            }

            public void onFinish() {
                presenter.forgotPassLoadTimeFinished();
            }
        }.start();
    }

    @Override
    public void stopForgotLoading() {
        tvForgetLoading.setVisibility(View.GONE);
//        tvForgotPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMobileNumberError(String string) {
        etMobileNumber.setError(string);
        etMobileNumber.requestFocus();
    }

    @Override
    public void showError09MobileNumberLogin() {
        etMobileNumber.setError("شماره موبایل می بایست با 09 شروع گردد");

    }

    @Override
    public void showErrorZeroMobileNumberLogin() {
        etMobileNumber.setError("وارد کردن شماره موبایل الزامیست");

    }

    @Override
    public void showErrorNotCorrectMobilePhoneLogin() {
        etMobileNumber.setError("شماره موبایل وارد شده صحیح نمی باشد");

    }


    @Override
    public void setEdtMobile_inActiveUser() {
        etMobileNumber.setText(App.mobileNumber_inActiveUser);
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (!App.modeChanged) {
            App.modeChanged = true;
            ((Activity) context).finish();
            context.startActivity(new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this
                ,new String[]{Manifest.permission.READ_PHONE_STATE},4);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode){
            case 4:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED ){
                    validateAndLogin();
//                    dosomthing();
                }else {

                    showAccessPermissionDialog();
                }
                break;

            case 1235:
                if(grantResults[1]==PackageManager.PERMISSION_GRANTED ){
                    startActivity(new Intent(context,ActivationActivity.class));
//                    dosomthing();
                }
                break;
        }
    }


    private void showAccessPermissionDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
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
            }
        });
        builder.create().show();
    }

}