package com.rayanandisheh.peysepar.passenger.activities.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.activation.ActivationActivity;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;

public class Presenter implements Contract.Presenter {

    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this, context);
    }


    @Override
    public void btRegisterPressed(String firstName, String lastName, String mobileNumber, String sexuality) {

        if (firstName.isEmpty())
            view.showErrorZeroFirstName();
        else if (firstName.length() < 2)
            view.showErrorNotCorrectFirstName();
        else if (lastName.isEmpty())
            view.showErrorZeroLastName();
        else if (lastName.length() < 2)
            view.showErrorNotCorrectLastName();
        else if (mobileNumber.isEmpty())
            view.showErrorZeroMobileNumber();
        else if (mobileNumber.length() != 11)
            view.showErrorNotCorrectMobilePhone();
        else if (!mobileNumber.startsWith("09"))
            view.showError09MobileNumber();
        else {
            view.startLoading();
            model.register(firstName, lastName, mobileNumber, sexuality);
        }

    }


    @Override
    public void registerResult(int result) {
        view.stopLoading();

        if (result == -4) {
            Toaster.shorter(context.getString(R.string.connectionFaield));

            // TODO: 12/23/2018 delete below line. just for test 
//            context.startActivity(new Intent(context, ActivationActivity.class));
        } else if (result == -1)
            Toaster.shorter(context.getString(R.string.serverFaield));

        else if (result == -2) {
            Toaster.shorter("شما قبلا ثبت نام کرده اید.");
        } else if (result == 1) {
            context.startActivity(new Intent(context, ActivationActivity.class));
            ((Activity) context).finish();
            {
            }

//        switch (result) {
//            case -1:
//                Toaster.shorter(context.getString(R.string.connectionFaield));
//                break;
//            case -4:
//                Toaster.shorter(context.getString(R.string.serverFaield));
//                break;
//            case 1:
//                context.startActivity(new Intent(context, ActivationActivity.class));
//                Toaster.longer(context.getString(R.string.validateYourNumber));
//                break;
//            default:
//                break;
//        }
//    }


        }
    }
}