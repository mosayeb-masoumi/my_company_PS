package com.rayanandisheh.peysepar.passenger.activities.trip_management;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;


public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    private PersianDatePickerDialog picker;
    String date;

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this, context);
    }

    @Override
    public void edtDatePressed(boolean isFrom) {
        date(isFrom);
    }

    private void date(boolean isFrom) {

        picker = new PersianDatePickerDialog(context)
                .setPositiveButtonString("تایید")
                .setNegativeButton("انصراف")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(1450)
//                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setActionTextColor(Color.GRAY)
//                .setTypeFace(typeface)

                //write again it if it has error
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {

                        date = persianCalendar.getPersianYear() + "/" +
                                (String.valueOf(persianCalendar.getPersianMonth()).length()==2?
                                        persianCalendar.getPersianMonth() : "0" + persianCalendar.getPersianMonth()) + "/" +
                                (String.valueOf(persianCalendar.getPersianDay()).length()==2?
                                        persianCalendar.getPersianDay() : "0" + persianCalendar.getPersianDay());
                        if (isFrom)
                            view.setFromDate(date);
                        else
                            view.setToDate(date);
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
    }

}
