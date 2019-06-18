package com.rayanandisheh.peysepar.passenger.activities.add_trip;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.main.MainActivity;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;


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


    @Override
    public void cvCalandarPressed(EditText edtDate) {

        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edtDate, InputMethodManager.SHOW_IMPLICIT);

//        PersianCalendar initDate = new PersianCalendar();
//        initDate.setPersianDate(1370, 3, 13);

        picker = new PersianDatePickerDialog(context)
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

//                        date = persianCalendar.getPersianYear() + "/"
//                                + persianCalendar.getPersianMonth() + "/"
//                                + persianCalendar.getPersianDay();

                        date = persianCalendar.getPersianYear() + "/" +
                                (String.valueOf(persianCalendar.getPersianMonth()).length() < 2 ? "0" + persianCalendar.getPersianMonth() : persianCalendar.getPersianMonth()) + "/" +
                                        (String.valueOf(persianCalendar.getPersianDay()).length() < 2 ? "0" + persianCalendar.getPersianDay() : persianCalendar.getPersianDay());


//                             Toast.makeText(context, date, Toast.LENGTH_SHORT).show();
                        edtDate.setText(date);
                    }

                    @Override
                    public void onDismissed() {
                    }
                });
        picker.show();
    }


//    @Override
//    public void spinnerOriginPressed(Spinner spnOrigin) {
//        spnOrigin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                if (item.equals("انتخاب کنید")) {
//                    view.showIvWarningOrigin();
//                } else {
//                    view.hideIvWarningOrigin();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }


//    @Override
//    public void spinnerDestinationPressed(Spinner spnDestination) {
//        spnDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                if (item.equals("انتخاب کنید")) {
//                    view.showIvWarningDes();
//                } else {
//                    view.hideIvWarningDes();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }

    @Override
    public void spinnerTripReasonPressed(Spinner spnReason) {
        spnReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.equals("انتخاب کنید")) {
                    view.showIvWarningReason();
                } else {
                    view.hideIvWarningReason();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void spinnerTripImportancePressed(Spinner spnImportance) {
        spnImportance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.equals("انتخاب کنید")) {
                    view.showIvWarningImportance();
                } else {
                    view.hideIvWarningImportance();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Override
    public void viewLoaded() {
//        view.setSpnOriginData(model.getSpnOriginData());
//        view.setSpnDesData(model.getSpnDesData());
        view.setSpnReasonData(model.getSpnReasonData());
        view.setSpnImportanceData(model.getImportanceData());

        view.setOriginDes(model.getOriginDes());

        view.setSpnCarType(model.getSpnTypeCar());


//
//        String[] s = new String[App.userInfo.getTripSAD().size()];
//        for (int i = 0; i < App.userInfo.getTripSAD().size(); i++)
//            s[i] = App.userInfo.getTripSAD().get(i).getStrName();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, s);

//
//        List<String> redddasons = new ArrayList<String>();
//        for (int i = 0; i < App.userInfo.getTripSAD().size(); i++)
//         redddasons.add(App.userInfo.getTripReason().get(i).getStrComment());


//        view.getOriginDes(adapter);


    }

    @Override
    public void chkInServiceChecked(boolean chkInServiceisChecked) {

        if (chkInServiceisChecked)
            view.HideDestination();
        else
            view.showDestination();
    }

    @Override
    public void btnPressed(int spnReason, int spnImportance, int spnCarType, String autoCompleteTxtOrigin,
                           String autoCompleteTxtDes, String edtDate, String numberPickerTime,
                           boolean chkBoxInService, boolean chkBxReturn, boolean chkBxMissionary, String list, String strComment) {

        view.showProgressBar();
        model.requestAddTrip(spnReason, spnImportance,spnCarType, autoCompleteTxtOrigin,
                autoCompleteTxtDes, edtDate, numberPickerTime, chkBoxInService, chkBxReturn, chkBxMissionary,list,strComment);
    }

    @Override
    public void tripInsertResult(Integer result) {

        view.hidePreogressBar();

        if (result == -4) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == -1) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        } else if (result==0){
            Toaster.shorter("سفر شما قبلا ثبت شده است");
        }else {
//            Toast.makeText(context, "کد رهگیری شما" + result, Toast.LENGTH_SHORT).show();
            Toaster.shorter("سفر شما با موفقیت ثبت شد");

            context.startActivity(new Intent(context, MainActivity.class));
            ((Activity) context).finish();
        }
    }

    @Override
    public void noValidDes() {
        Toaster.shorter("آدرس مقصد می بایست از موارد پیشنهادی انتخاب گردد");
        view.hidePreogressBar();
    }

    @Override
    public void noValidOrigin() {
        Toaster.shorter("آدرس مبدا می بایست از موارد پیشنهادی انتخاب گردد");
        view.hidePreogressBar();
    }

    @Override
    public void ErrorEqualOriginDestination() {
        Toaster.shorter("مبدا و مقصد نباید یکسان انتخاب گردند");
        view.hidePreogressBar();
    }
}

