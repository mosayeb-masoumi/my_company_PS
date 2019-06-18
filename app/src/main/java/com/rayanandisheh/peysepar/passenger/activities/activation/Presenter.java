package com.rayanandisheh.peysepar.passenger.activities.activation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.login.LoginActivity;
import com.rayanandisheh.peysepar.passenger.activities.main.MainActivity;
import com.rayanandisheh.peysepar.passenger.activities.register.RegisterActivity;
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
    public void btValidatePressed(String validationCode) {
        if (Validate.activationCode(validationCode)) {
            view.startValidateLoading();
            model.validate(validationCode);
        } else
            view.showActivationCodeError(context.getResources().getString(R.string.emptyValidationCode));
    }



    @Override
    public void btValidateDifferentIMEI_Pressed(String smsCode, String formerMobilePhone) {
        if (Validate.activationCode(smsCode)) {
            view.showPbDiffrentIMEI();
            model.validateDiffrentIMEI(smsCode,formerMobilePhone);
        } else
            view.showActivationCodeError(context.getResources().getString(R.string.emptyValidationCode));
    }




    @Override
    public void sendAgainPressed() {
        view.startSendCodeAgainLoading();
        model.sendCodeAgain();
    }


    @Override
    public void sendCodeResult(int result) {
        view.stopValidateLoading();
        switch (result) {
            case -5:
                Toaster.shorter(context.getString(R.string.connectionFaield));
                break;
            case -4:
                Toaster.shorter(context.getString(R.string.serverFaield));
                break;
//            case -1:
//                Toaster.shorter(context.getString(R.string.wrongUser));
//                break;
            case 0:
                Toaster.longer("کد فعالسازی اشتباه است ");
                break;
            case 1:
                Toaster.longer(context.getString(R.string.validateYourNumber));
                model.cacheUser();
                break;
            default:
                break;
        }
    }

    @Override
    public void validateResult(int result) {

        view.stopValidateLoading();
        switch (result) {
            case -5:
                view.stopSendCodeAgainLoading();
                Toaster.shorter(context.getString(R.string.connectionFaield));
                view.stopSendCodeAgainLoading();
                break;
            case -4:
                view.stopSendCodeAgainLoading();
                Toaster.shorter(context.getString(R.string.serverFaield));
                view.stopSendCodeAgainLoading();
                break;
            case -3:
//                Toaster.shorter(App.userInfo.strComment);
                context.startActivity(new Intent(context,LoginActivity.class));
                break;
            case -1:
                Toaster.shorter(App.userInfo.strComment);
                break;
            case 0:
                Toaster.longer("کد فعالسازی اشتباه است.");
                view.stopSendCodeAgainLoading();
                break;
            case 1:
                context.startActivity(new Intent(context,MainActivity.class));
                model.cacheUser();
                ((Activity)context).finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void validateDiffrentIMEIResult(int result) {
        view.stopPbValidateDifferentIMEI();
        if(result==1){
            context.startActivity(new Intent(context,MainActivity.class));
            model.cacheUser();
            ((Activity)context).finish();
        }else if(result==-3){
            Toaster.shorter(App.userInfo.strComment);
        }else if(result==-1) {
            Toaster.shorter(App.userInfo.strComment);
        }else if(result==0){
            Toaster.longer(App.userInfo.getStrComment());
            view.stopSendCodeAgainLoading();
        }

        else if(result==-5) {
            Toaster.shorter(App.userInfo.strComment);
            view.stopSendCodeAgainLoading();
            Toaster.shorter(context.getString(R.string.connectionFaield));
            view.stopSendCodeAgainLoading();
        }
    }















    @Override
    public void sendCodeAgainLoadTimeFinished() {
        view.stopSendCodeAgainLoading();
    }


    @Override
    public void btnGotoRegisterDifferentIMEI_Pressed(String formerMobilePhone) {
        App.formerMobilePhoneDiffrentIMEI=formerMobilePhone;
        App.formerMobileDiffrentIMEI=true;
        context.startActivity(new Intent(context,RegisterActivity.class));
    }


}