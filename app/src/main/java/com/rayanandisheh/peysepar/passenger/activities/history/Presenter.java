package com.rayanandisheh.peysepar.passenger.activities.history;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
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

//    @Override
//    public void viewLoaded(RecyclerView recyclerView) {
//
//        if (App.historySuccess) {
//          Model.loadData(recyclerView);
//        } else if (App.historyList.size() > 0) {
//
//            Model.DataLoaded(recyclerView);
//            view.hideImg_noItem();
//
//        } else {
//            view.showImg_noItem();
//        }
//        }

//    @Override
//    public void viewLoaded() {
//        if (App.historyTripSuccess) {
////            model.loadHistoryTripList();
//              model.loadHistoryTripList(dateFrom ,dateTo);
//        } else if (App.listHistoryTrip.size() > 0) {
//
////            Model.DataLoaded();
//            view.hideImg_noItem();
//
//        } else {
//            view.showImg_noItem();
//        }
//    }

    @Override
    public void viewLoaded(String dateFrom, String dateTo) {
        if (App.historyTripSuccess) {
            model.loadHistoryTripList(dateFrom ,dateTo);
        } else if (App.listHistoryTrip.size() > 0) {
            view.setAdapter();
            view.hideImg_noItem();
        } else if(App.listHistoryTrip.size()==0){
            view.showImg_noItem();
        }
    }


//    @Override
//    public void swipeClicked() {
//        model.loadData();
//
//    }

    @Override
    public void showPregressbar() {
        view.showProgressLoading();
    }



    @Override
    public void swipRefreshPressed(String dateFrom, String dateTo) {
        model.loadHistoryTripList(dateFrom ,dateTo);
        view.showSwipeRefresh();
    }



    @Override
    public void loadDataResult(int result) {
        view.stopProgressLoading();
        view.hideSwipeRefresh();
        if (result == -4)
            Toaster.shorter(context.getString(R.string.serverFaield));
        else if (result == -5)
            Toaster.shorter(context.getString(R.string.connectionFaield));
        else if(result==0)
            view.showImg_noItem();
        else if(result==1){
            view.setAdapter();
            if(App.listHistoryTrip.size()>0)
                view.hideImg_noItem();
            else
                view.showImg_noItem();

        }

    }


    @Override
    public void edtDatePressed(boolean isFrom) {
        date(isFrom);

    }



    private void date(boolean isFrom) {

//        PersianCalendar initDate = new PersianCalendar();
//        initDate.setPersianDate(1370, 3, 13);

        picker = new PersianDatePickerDialog(context)
                .setPositiveButtonString("تایید")
                .setNegativeButton("انصراف")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setActionTextColor(Color.GRAY)
//                .setTypeFace(typeface)

                //write again it if it has error
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
//                        Toast.makeText(context, persianCalendar.getPersianYear() + "/" +
//                                persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay(), Toast.LENGTH_SHORT).show();
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