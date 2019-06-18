package com.rayanandisheh.peysepar.passenger.activities.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.activation.ActivationActivity;
import com.rayanandisheh.peysepar.passenger.activities.login.LoginActivity;
import com.rayanandisheh.peysepar.passenger.activities.main.MainActivity;
import com.rayanandisheh.peysepar.passenger.activities.register.RegisterActivity;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
import com.rayanandisheh.peysepar.passenger.helpers.Validate;

import java.util.Timer;
import java.util.TimerTask;

public class Presenter implements Contract.Presenter {

    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    private int requestCount=0;

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this, context);
    }

    @Override
    public void activityLoaded() {

        String mobile=Cache.getString("mobileNumber");

//        if (model.hasCachedUser()) {
        if (Validate.mobile(mobile)) {
            model.login();
        } else
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    gotoLogin();
                }
            }, 2700);
    }


    @Override
    public void loginResult(int result) {
        if (result == 1) {
//            model.saveUserInfo(result);
            context.startActivity(new Intent(context, MainActivity.class));
            model.cacheMobilePhone();
            ((Activity) context).finish();
        } else if (result == 0) {
            context.startActivity(new Intent(context, LoginActivity.class));
            ((Activity) context).finish();
        }else if (result == -2) {
            context.startActivity(new Intent(context, ActivationActivity.class));
            Toaster.longer(context.getString(R.string.validateYourNumber));
            ((Activity) context).finish();
        }

        else if(result==-1) {
            Toaster.shorter(context.getString(R.string.serverFaield));
            //comment below line to prevent jumping splash when the web service is not correct
//            context.startActivity(new Intent(context,SplashActivity.class));
            ((Activity) context).finish();
        }else if(result==-5){



         //after 3 times encountiong timeout ,refer to loginActivity
//            requestCount++;
//            if(requestCount<4){
//                Toaster.shorter(context.getString(R.string.connectionFaield));
//                context.startActivity(new Intent(context,SplashActivity.class));
//            }else{
                context.startActivity(new Intent(context,LoginActivity.class));
//            }
            ((Activity) context).finish();





        }else {
            gotoLogin();
        }

    }

    private void gotoLogin() {
        context.startActivity(new Intent(context, LoginActivity.class));
        ((Activity) context).finish();

    }
}