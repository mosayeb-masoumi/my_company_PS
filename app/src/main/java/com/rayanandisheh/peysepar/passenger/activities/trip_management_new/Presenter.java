package com.rayanandisheh.peysepar.passenger.activities.trip_management_new;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;

public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();
    int position;

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this, context);
    }
    @Override
    public void viewLoded(int spnPosition) {
//        position=spnPosition;
    }
    @Override
    public void cancelNewAlertTripManagement(int iOfficialTrip, int tiTripStatus) {
        view.ShowSwpRefresh();
        model.requestCancelNewAlertTripManagement(iOfficialTrip , position , tiTripStatus);
    }
    @Override
    public void confirmNewAlertTripManagement(int iOfficialTrip) {

        view.ShowSwpRefresh();
        model.requestConfirmNewAlertTripManagement(iOfficialTrip , position);
    }
    @Override
    public void cancelConfirmedTripAlertTripManagement(int iOfficialTrip, int tiTripStatus) {
        view.ShowSwpRefresh();
        model.requestCancelConfirmedTripAlertTripManagement(iOfficialTrip , position , tiTripStatus);
    }
    @Override
    public void cancelWaitingDriverConfirmedAlertTripManagement(int iOfficialTrip, int tiTripStatus) {
        view.ShowSwpRefresh();
        model.requestCancelWaitingDriverConfirmedAlertTripManagement(iOfficialTrip , position , tiTripStatus);
    }
    @Override
    public void confirm_CancelTripAlert(int iOfficialTrip) {
        view.ShowSwpRefresh();
        model.requestCancelTripConfim(iOfficialTrip , position);
    }
    @Override
    public void cencelTripAlertConfirmResult(int result, int position) {
        view.HideSwpRefresh();
        if(result==1) {
            Toaster.shorter("سفر با موفقیت لغو شد");
            view.ShowSwpRefresh();
            model.requesrListNewTrips(position);
//            ((Activity)context).finish();
        }else if(result==0){
            Toaster.shorter("شما قادر به لغو سفر نیستید");
        }else if(result==-1){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }else if(result==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }
    }
    @Override
    public void cencelWaitinDriverConfirmTripAlertResult(int result, int position) {
        view.HideSwpRefresh();
        if (result == -4) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == -5) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        } else{
            Toaster.shorter("سفر شما با موفقیت لغو گردید");
            //to update list
            model.requesrListNewTrips(position);
        }
    }
    @Override
    public void updateList(int position) {
        view.HideSwpRefresh();
        model.requesrListNewTrips(position);
    }
    @Override
    public void cencelConfirmTripAlertResult(int result, int position) {
        view.HideSwpRefresh();
        if (result == -4) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == -5) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        } else{
            Toaster.shorter("سفر شما با موفقیت لغو گردید");
            //to update list
            model.requesrListNewTrips(position);
        }
    }
    @Override
    public void confirmNewTripAlertResult(int result, int position) {
        view.HideSwpRefresh();
        if(result == 1){
            Toaster.shorter(App.data.getMessage());
            view.ShowSwpRefresh();

            //to update list
            model.requesrListNewTrips(position);

        }else if(result== -2){
            Toaster.shorter(App.data.getMessage());
        }else if(result== -1){
            Toaster.shorter(App.data.getMessage());
        }else if(result==-5){
            Toaster.shorter(context.getString(R.string.connectionFaield));
        }
    }

    @Override
    public void cencelNewTripAlertResult(int result, int position) {
        view.HideSwpRefresh();
        if (result == -4) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == -5) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        } else if (result==1){
            Toaster.shorter("سفر شما با موفقیت لغو گردید");
            //to update list
            model.requesrListNewTrips(position);
        }else if(result==-1){
            Toaster.shorter(context.getString(R.string.serverFaield));
        }
    }

    @Override
    public void spnSelected(AdapterView<?> parent, View view1, int position1, long id) {
//        String text=parent.getItemAtPosition(position).toString();
//        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        if (position1 == 0) {
            view.ShowSwpRefresh();
            model.requesrListNewTrips(position1);
            // todo check here
            position=position1;
//            Toast.makeText(context, "جدید", Toast.LENGTH_SHORT).show();
        } else if (position1 == 1) {
            view.ShowSwpRefresh();
            model.requesrListNewTrips(position1);
            position=position1;
//            Toast.makeText(context, "تایید شده", Toast.LENGTH_SHORT).show();
        } else if (position1 == 2) {
            view.ShowSwpRefresh();
            model.requesrListNewTrips(position1);
            position=position1;
//            Toast.makeText(context, "منتظر تایید راننده", Toast.LENGTH_SHORT).show();
        } else if (position1== 3) {
            view.ShowSwpRefresh();
            model.requesrListNewTrips(position1);
            position=position1;
//            Toast.makeText(context, "در حال اجرا", Toast.LENGTH_SHORT).show();
        } else if (position1 == 4) {
            view.ShowSwpRefresh();
            model.requesrListNewTrips(position1);
            position=position1;
//            Toast.makeText(context, "لغو شده", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadDataResult(int result, int position) {
        view.HideSwpRefresh();
        if (result == -5) {
            Toaster.shorter(context.getString(R.string.connectionFaield));
        } else if (result == -4) {
            Toaster.shorter(context.getString(R.string.serverFaield));
        } else if (result == 0) {
            view.showImg_noItem();
        } else if (result == 1) {

            view.setAdapter(position);
//           view.hideImg_noItem();
            if (position == 0) { // newTrip
                if (App.listNewTripManagementNew.size() > 0) {
//                    Toast.makeText(context, App.data.getMessage(), Toast.LENGTH_SHORT).show();
                    view.hideImg_noItem();
                }
                else
                    view.showImg_noItem();
            } else if (position == 1) {  //confirmTrip
                if (App.listConfirmedTripManagementNew.size() > 0)
                    view.hideImg_noItem();
                else
                    view.showImg_noItem();
            } else if (position == 2) {  //driverConfirmTrip
                if (App.listWaitingDriverConfirmTripManagementNew.size() > 0)
                    view.hideImg_noItem();
                else
                    view.showImg_noItem();
            } else if (position == 3) {  //runningTrip
                if (App.listRunningTripManagementNew.size() > 0)
                    view.hideImg_noItem();
                else
                    view.showImg_noItem();
            } else if (position == 4) {  //cancelTrip
                if (App.listCanceledTripManagementNew.size() > 0)
                    view.hideImg_noItem();
                else
                    view.showImg_noItem();
            }
        }
    }

    @Override
    public void swpRefreshPressed(int position) {
        model.requesrListNewTrips(position);
    }

    @Override
    public void imgArrowBackPressed() {
        ((Activity) context).finish();
    }
}
