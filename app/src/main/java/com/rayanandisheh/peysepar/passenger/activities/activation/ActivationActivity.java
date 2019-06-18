package com.rayanandisheh.peysepar.passenger.activities.activation;

import android.content.Context;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;

import java.text.MessageFormat;

public class ActivationActivity extends mAppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context = this;
    EditText etValidateCode,etFormerMobilePhone;
    Button btValidate,btnGotoRegisterDifferentIMEI,btValidateDifferentIMEI;
    ProgressBar pbValidate,pbValidateDifferentIMEI;
    TextView tvRequestValidationCodeAgain, tvSendValidationCodeAgainLoading;
    ImageView ivWarningActvationCode,ivWarningFormerMobilePhone;

    LinearLayout llFormerMobilePhoneDiffrentIMEI;
    RelativeLayout rlDifferentIMEI,rlSameIMEI_MOBILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        presenter.attachView(context,this);
        bindViews();



        if(App.diffrentIMEI) {
            llFormerMobilePhoneDiffrentIMEI.setVisibility(View.VISIBLE);
            rlDifferentIMEI.setVisibility(View.VISIBLE);
            rlSameIMEI_MOBILE.setVisibility(View.GONE);

        }
        else {
            llFormerMobilePhoneDiffrentIMEI.setVisibility(View.GONE);
            rlDifferentIMEI.setVisibility(View.GONE);
            rlSameIMEI_MOBILE.setVisibility(View.VISIBLE);
        }



        btnGotoRegisterDifferentIMEI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.btnGotoRegisterDifferentIMEI_Pressed(etFormerMobilePhone.getText().toString());
            }
        });





/*----------------------------------------------------------------------------------------*/
        etValidateCode.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        presenter.btValidateDifferentIMEI_Pressed(etValidateCode.getText().toString()
                                , etFormerMobilePhone.getText().toString());
                        return true;
                    default:
                        break;
                }
            }
            return false;
        });

        etFormerMobilePhone.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        presenter.btValidateDifferentIMEI_Pressed(etValidateCode.getText().toString()
                                , etFormerMobilePhone.getText().toString());
                        return true;
                    default:
                        break;
                }
            }
            return false;
        });

/*----------------------------------------------------------------------------------------*/








        btValidate.setOnClickListener(v -> presenter.btValidatePressed(etValidateCode.getText().toString()));
        btValidateDifferentIMEI.setOnClickListener(v -> presenter.btValidateDifferentIMEI_Pressed(etValidateCode.getText().toString()
                , etFormerMobilePhone.getText().toString()));



        etValidateCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(s.length()<3)
                   ivWarningActvationCode.setVisibility(View.VISIBLE);
               else
                   ivWarningActvationCode.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etFormerMobilePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=11)
                    ivWarningFormerMobilePhone.setVisibility(View.VISIBLE);
                else
                    ivWarningFormerMobilePhone.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    private void bindViews() {

        btValidate=findViewById(R.id.btValidate);
        pbValidate=findViewById(R.id.pbValidate);
        etValidateCode=findViewById(R.id.etValidateCode);
        tvRequestValidationCodeAgain=findViewById(R.id.tvRequestValidationCodeAgain);
        tvSendValidationCodeAgainLoading=findViewById(R.id.tvSendValidationCodeAgainLoading);

        llFormerMobilePhoneDiffrentIMEI=findViewById(R.id.llFormerMobilePhoneDiffrentIMEI);
        ivWarningFormerMobilePhone=findViewById(R.id.ivWarningFormerMobilePhone);
        etFormerMobilePhone=findViewById(R.id.etFormerMobilePhone);
        rlDifferentIMEI=findViewById(R.id.rlDifferentIMEI);
        btnGotoRegisterDifferentIMEI=findViewById(R.id.btnGotoRegisterDifferentIMEI);
        btValidateDifferentIMEI=findViewById(R.id.btValidateDifferentIMEI);
        pbValidateDifferentIMEI=findViewById(R.id.pbValidateDifferentIMEI);
        rlSameIMEI_MOBILE=findViewById(R.id.rlSameIMEI_MOBILE);
        ivWarningActvationCode=findViewById(R.id.ivWarningActvationCode);
        tvRequestValidationCodeAgain.setOnClickListener(v -> presenter.sendAgainPressed());

    }

    @Override
    public void startSendCodeAgainLoading() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvRequestValidationCodeAgain.setVisibility(View.GONE);
                tvSendValidationCodeAgainLoading.setVisibility(View.VISIBLE);
                tvSendValidationCodeAgainLoading.setText(MessageFormat.format("{0} {1} {2}", getString(R.string.after), millisUntilFinished / 1000, getString(R.string.secends)));
            }

            public void onFinish() {
                presenter.sendCodeAgainLoadTimeFinished();
            }
        }.start();
    }

    @Override
    public void stopSendCodeAgainLoading() {
        tvRequestValidationCodeAgain.setVisibility(View.VISIBLE);
        tvSendValidationCodeAgainLoading.setVisibility(View.GONE);
    }

    @Override
    public void startValidateLoading() {
        pbValidate.setVisibility(View.VISIBLE);
        btValidate.setVisibility(View.GONE);
    }

    @Override
    public void stopValidateLoading() {
        pbValidate.setVisibility(View.GONE);
        btValidate.setVisibility(View.VISIBLE);
    }

    @Override
    public void showActivationCodeError(String string) {
        etValidateCode.setError(string);
        etValidateCode.requestFocus();
    }

    @Override
    public void showPbDiffrentIMEI() {
        pbValidateDifferentIMEI.setVisibility(View.VISIBLE);
        btValidateDifferentIMEI.setVisibility(View.GONE);
    }

    @Override
    public void stopPbValidateDifferentIMEI() {
        pbValidateDifferentIMEI.setVisibility(View.GONE);
        btValidateDifferentIMEI.setVisibility(View.VISIBLE);
    }



/*----------------------------------------sms related---------------------------------------------*/
    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsReceiver);
    }

    private SmsReceiver smsReceiver = new SmsReceiver(new SmsReceiver.OnSmsReceivedListener() {
        @Override
        public void onSmsReceived(String code) {
            if (code == null) {
                etValidateCode.setError("لطفا کد تایید را وارد نمایید");
                etValidateCode.setText(code);
            } else {
                etValidateCode.setText(code);
            }
        }
    });
}
