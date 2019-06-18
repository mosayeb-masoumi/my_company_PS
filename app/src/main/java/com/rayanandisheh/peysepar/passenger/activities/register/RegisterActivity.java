package com.rayanandisheh.peysepar.passenger.activities.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.activation.ActivationActivity;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;

public class RegisterActivity extends mAppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context = this;
    EditText etFirstName, etLastName, etNCode, etPassword, etRepeatPassword;

    Button btRegister;
    ProgressBar pbRegister;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView txtRayan,txtMobileNumber;
    ImageView ivWarningNameRegister, ivWarningFamilyRegister, ivWarningMobilePhoneRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter.attachView(context, this);
        bindViews();



//        Intent intent=getIntent();
//        String receiver= intent.getStringExtra("mobilePhoneFirstEnter");
//        etMobileNumber.setText(receiver);





//        if(App.formerMobileDiffrentIMEI){
//            txtMobileNumber.setText(App.formerMobilePhoneDiffrentIMEI);
//        }else{
            txtMobileNumber.setText(App.mobileFirstRunning);
//        }





        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 2)
                    ivWarningNameRegister.setVisibility(View.VISIBLE);
                else
                    ivWarningNameRegister.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 2)
                    ivWarningFamilyRegister.setVisibility(View.VISIBLE);
                else
                    ivWarningFamilyRegister.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        etMobileNumber.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 11)
//                    ivWarningMobilePhoneRegister.setVisibility(View.GONE);
//                else
//                    ivWarningMobilePhoneRegister.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });



        btRegister.setOnClickListener(v -> btnClicked());

    }

    private void bindViews() {

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        radioGroup = findViewById(R.id.radioGroupRegister);

//        String selectedText = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());


//        etNCode = findViewById(R.id.etNCode);

        txtMobileNumber=findViewById(R.id.txtMobileNumber);
//        etPassword = findViewById(R.id.etPassword);
//        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        btRegister = findViewById(R.id.btRegister);
        pbRegister = findViewById(R.id.pbRegister);


//        tvGoBack.setOnClickListener(v -> onBackPressed());

        ivWarningNameRegister = findViewById(R.id.ivWarningNameRegister);
        ivWarningFamilyRegister = findViewById(R.id.ivWarningFamilyRegister);
        ivWarningMobilePhoneRegister = findViewById(R.id.ivWarningMobilePhoneRegister);
    }

    private void btnClicked() {

//        int selectedId=radioGroup.getCheckedRadioButtonId();
//        radioButton=findViewById(selectedId);

//        String selectedText = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

        String sexualityType = radioButton.getText().toString();


        presenter.btRegisterPressed(
                etFirstName.getText().toString(),
                etLastName.getText().toString(),
                txtMobileNumber.getText().toString(),
                sexualityType);
    }


    @Override
    public void startLoading() {
        btRegister.setVisibility(View.GONE);
        pbRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        pbRegister.setVisibility(View.GONE);
        btRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFirstNameError(String string) {
        etFirstName.setError(string);
        etFirstName.requestFocus();
    }

    @Override
    public void showLastNameError(String string) {
        etLastName.setError(string);
        etLastName.requestFocus();
    }

    @Override
    public void showNationalCodeError(String string) {
        etNCode.setError(string);
        etNCode.requestFocus();
    }

    @Override
    public void showMobileNumberError(String string) {

//            etMobileNumber.setError(string);
//            etMobileNumber.requestFocus();
//        }else if(!etMobileNumber.startsWith("09")){
//            etMobileNumber.setError("شماره موبایل می بایست با 09 شروع شود");
//            etMobileNumber.requestFocus();
//        }else if(string.length()!=11 && string.length()!=0){
//            etMobileNumber.setError("شماره موبایل می بایست 11 رقم باشد");
//            etMobileNumber.requestFocus();
//        }
    }




    @Override
    public void showPasswordError(String string) {
        etPassword.setError(string);
        etPassword.requestFocus();
    }

    @Override
    public void showRepeatPasswordError(String string) {
        etRepeatPassword.setError(string);
        etRepeatPassword.requestFocus();
    }




    @Override
    public void showErrorZeroFirstName() {
        etFirstName.setError("وارد کردن نام الزامیست");
//        ivWarningNameRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorNotCorrectFirstName() {
        etFirstName.setError("نام وارد شده صحیح نمی باشد");
//        ivWarningNameRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorZeroLastName() {
        etLastName.setError("وارد کردن نام خانوادگی الزامیست");
//        ivWarningFamilyRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorNotCorrectLastName() {
        etLastName.setError("نام خانوادگی وارد شده صحیح نمی باشد");
//        ivWarningFamilyRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorZeroMobileNumber() {
//        etMobileNumber.setError("وارد کردن شماره موبایل الزامیست");
//        ivWarningMobilePhoneRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError09MobileNumber() {
//        etMobileNumber.setError("شماره موبایل می بایست با 09 شروع گردد");
//        ivWarningMobilePhoneRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorNotCorrectMobilePhone() {
//        etMobileNumber.setError("شماره موبایل وارد شده صحیح نمی باشد");
//        ivWarningMobilePhoneRegister.setVisibility(View.VISIBLE);
    }
}
