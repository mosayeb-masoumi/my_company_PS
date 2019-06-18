package com.rayanandisheh.peysepar.passenger.activities.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.activation.ActivationActivity;
import com.rayanandisheh.peysepar.passenger.activities.changePassword.ChangePasswordActivity;
import com.rayanandisheh.peysepar.passenger.activities.main.MainActivity;
import com.rayanandisheh.peysepar.passenger.activities.register.RegisterActivity;
import com.rayanandisheh.peysepar.passenger.activities.setting.SettingActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
import com.rayanandisheh.peysepar.passenger.helpers.Validate;

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
    public void forgotPass(String mobileNumber) {
        if (Validate.mobile(mobileNumber)) {
            model.forgotPass(mobileNumber);
            view.startForgotLoading();
        } else
            view.showMobileNumberError(context.getResources().getString(R.string.emptyMobileNumber));
    }

    @Override
    public void register() {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    public void login(String mobileNumber) {


//        if(!mobileNumber.startsWith("09"))
//            view.showError09MobileNumberLogin();

         if(mobileNumber.isEmpty()) //todo
            view.showErrorZeroMobileNumberLogin();
        else if(mobileNumber.equals("305040"))
            context.startActivity(new Intent(context,SettingActivity.class));

        else{
            view.startLoading();
            model.login(mobileNumber);
        }
//
//
//        if (!Validate.mobile(mobileNumber))
//            view.showMobileNumberError(context.getResources().getString(R.string.emptyMobileNumber));

    }

    @Override
    public void loginResult(int result) {
        view.stopLoading();
        switch (result) {
            case -5:
                Toaster.shorter(context.getString(R.string.connectionFaield));
                break;
            case -1:
                Toaster.shorter(App.userInfo.strComment);
                break;
            case -3:
                Toaster.shorter(App.userInfo.strComment);
                view.setEdtMobile_inActiveUser();
                break;

            case -4:
                //same IMEI & diffrent mobilePhone
                App.diffrentIMEI=true;
                if(model.CheckSmsPermissionGranted()) {
                    context.startActivity(new Intent(context, ActivationActivity.class));
                    Toaster.longer(context.getString(R.string.validateYourNumber));
                    ((Activity) context).finish();
                }
                break;
            case 0:
//                Toaster.shorter(context.getString(R.string.wrongUser));
                context.startActivity(new Intent(context, RegisterActivity.class));
//                ((Activity)context).finish();
                break;
            case -2:


                if(model.CheckSmsPermissionGranted()){
                    context.startActivity(new Intent(context, ActivationActivity.class));
                    Toaster.longer(context.getString(R.string.validateYourNumber));
                    ((Activity)context).finish();
                }


                break;
            case 1:
                context.startActivity(new Intent(context, MainActivity.class));
                ((Activity)context).finish();
                model.cacheUser();
                break;
            default:
                break;
        }
    }















    @Override
    public void forgotResult(int result) {
        switch (result) {
            case -5:
                view.stopForgotLoading();
                Toaster.shorter(context.getString(R.string.connectionFaield));
                break;
            case -4:
                view.stopForgotLoading();
                Toaster.shorter(context.getString(R.string.serverFaield));
                break;
            case 0:
                // TODO : check for after validate what happen
                context.startActivity(new Intent(context, ActivationActivity.class));
                Toaster.longer(context.getString(R.string.validateYourNumber));
                break;
            case 1:
                context.startActivity(new Intent(context, ChangePasswordActivity.class));
                model.cacheUser();
                break;
            default:
                break;
        }
    }

    @Override
    public void forgotPassLoadTimeFinished() {
        view.stopForgotLoading();
    }

}