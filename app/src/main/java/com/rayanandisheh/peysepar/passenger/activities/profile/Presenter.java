package com.rayanandisheh.peysepar.passenger.activities.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.map_choose_origin.MapsActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
import com.rayanandisheh.peysepar.passenger.helpers.Validate;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    String date;
    private PersianDatePickerDialog picker;

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this, context);
    }


    //choose profile Pic
    @Override
    public void showSelectPicDialog() {
        PickSetup setup = new PickSetup()
                .setTitle("تصوير جديد")
                .setProgressText("درحال ارسال...")
                .setSystemDialog(true);
        PickImageDialog.build(setup).show((FragmentActivity) context);
    }


    @Override
    public void cvCalandarPressed(EditText edtDate) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edtDate, InputMethodManager.SHOW_IMPLICIT);

//        PersianCalendar now = new PersianCalendar();
//        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance((view, year, monthOfYear, dayOfMonth) -> {
//                    date =(year + "/" + (monthOfYear+1) + "/" + dayOfMonth);
//                    edtDate.setText(date);
//                },
//                now.getPersianYear(),
//                now.getPersianMonth(),
//                now.getPersianDay());
//
//
//        view.showGetFragmentManager(datePickerDialog , date);


//        PersianCalendar initDate = new PersianCalendar();
//        initDate.setPersianDate(1370, 3, 13);


        picker = new PersianDatePickerDialog(context
        )
                .setPositiveButtonString("تایید")
                .setNegativeButton("انصراف")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(1450)
//                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
//                .setInitDate(initDate)
                .setActionTextColor(Color.GRAY)
//                .setTypeFace(typeface)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(ir.hamsaa.persiandatepicker.util.PersianCalendar persianCalendar) {

//                             Toast.makeText(context
//                                , persianCalendar.getPersianYear() + "/"
//                                             + persianCalendar.getPersianMonth() + "/"
//                                             + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
//
                        date = persianCalendar.getPersianYear() + "/"
                                + persianCalendar.getPersianMonth() + "/"
                                + persianCalendar.getPersianDay();
//                             Toast.makeText(context, date, Toast.LENGTH_SHORT).show();
                        edtDate.setText(date);
                    }

                    @Override
                    public void onDismissed() {
                    }
                });
        picker.show();

    }

    @Override
    public void btnPressed( String name, String family, String nationalID,String PermanentOriginAddress, boolean rbManchecked, Bitmap bmProfile) {

        if (!Validate.firstName(name))
            view.showNameError();

        else if (!Validate.lastName(family))
            view.showFamilyError();

        /*national Id is optional*/
//        else if (!Validate.nationalCode(nationalID))
//            view.showNcodeError();

//        else if(bmProfile == null)
//            Toaster.shorter("عکس پروفایل را انتخاب کنید");

        else if((App.selectedPosition.latitude >20 && App.selectedPosition.longitude >20) && PermanentOriginAddress.length()==0){
               view.showErrorEmpetyEdtPermanentAddress();
        }else if((App.selectedPosition.latitude <20 && App.selectedPosition.longitude <20) && !(PermanentOriginAddress.length() ==0)){
               Toaster.shorter("لطفا مبدا را از روی نقشه مشخص کنید");
        }

        else {
            view.showProggressBar();
            model.requestProfile(name, family, nationalID,PermanentOriginAddress,rbManchecked ,bmProfile);
        }

    }


    @Override
    public void updateProfileResulr(int result) {
        view.hideProgressBar();
        if (result == -2) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == -1) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }else if (result == 1) {
            model.requestRefresh();
            model.saveLATLNG_AddreesName();
        }
    }



    @Override
    public void viewLoaded(EditText name, EditText family, EditText nCode) {

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().length()<2)
                    view.showImageErrorName();
                else
                    view.hideImageErrorName();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        family.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().length()<2)
                    view.showImageErrorFamily();
                else
                    view.hideImageErrorFamily();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        nCode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                if(s.toString().length()!=10)
//                    view.showImageErrorNcode();
//                else
//                    view.hideImageErrorNcode();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    @Override
    public void rlChoosePermanentOriginPressed() {
        if (model.checkPermission()) {
//            if (model.checkGPSturnOn()) {
                context.startActivity(new Intent(context, MapsActivity.class));
//            } else {
////                view.showDialogeToTurnOnGPS();
            }
//        }
    }

    @Override
    public void requestResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1234:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    context.startActivity(new Intent(context, MapsActivity.class));


//                    view.showDialogeToTurnOnGPS();
                }
            default:
//                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }

    @Override
    public void refreshResult(int refreshResult) {
        if(refreshResult==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }else if(refreshResult==-4){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }else if(refreshResult==1){
            Toaster.shorter("پروفایل با موفقیت آپدیت شد");
            ((Activity)context).finish();
        }
    }



    @Override
    public void showGPSsetting() {
        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }


}

