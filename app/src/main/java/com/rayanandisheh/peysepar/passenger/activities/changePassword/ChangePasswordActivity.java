package com.rayanandisheh.peysepar.passenger.activities.changePassword;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;

public class ChangePasswordActivity extends mAppCompatActivity implements Contract.View {

    Contract.Presenter presenter = new Presenter();
    Context context = this;
    EditText etPassword, etRepeatPassword;
    Button btSave;
    ProgressBar pbSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        presenter.attachView(context,this);
        bindViews();
    }

    private void bindViews() {

        btSave.setOnClickListener(v -> change());
        etPassword.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_ENTER:
                        change();
                        return true;
                    default:
                        break;
                }
            }
            return false;
        });
    }

    private void change() {
        presenter.saveNewPass(etPassword.getText().toString(), etRepeatPassword.getText().toString());
    }

    @Override
    public void startLoading() {
        btSave.setVisibility(View.GONE);
        pbSave.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        pbSave.setVisibility(View.GONE);
        btSave.setVisibility(View.VISIBLE);
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
}
