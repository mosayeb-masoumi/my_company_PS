package com.rayanandisheh.peysepar.passenger.activities.currentTripDetails;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.Time;
import com.rayanandisheh.peysepar.passenger.models.Trip;

public class CurrentTripDetailsActivity extends PersianAppcompatActivity implements Contract.View {

    Contract.Presenter presenter = new Presenter();
    Context context;

    TextView
            txtPursuitCode_currntDtls, txtTripStatus_currntDtls, txtRequestDate_Time_currntDtls, txtPassengerNames_currntDtls,
            txtSupposedDate_Time_currntDtls, txtOriginName_currntDtls, txtDesName_currntDtls, txtReasonImportance_currntDtls,
            txtVehicleType_currentTrip, txtReturn_currntDtls, txtInOrder_currntDtls, txtEmptyCommentCurrentTripDetail,
            txtComment_currntDtls, txtDriverName_currentTrip, txtCarModel_currntDtls,
            txtArrivingTimeCalculate_currntDtls, txtTripPreparationTime_currntDtls, txtStartTripTime_currntDtls,
            txtFinishTripDateTime_currntDtls, txtTripDurationTime_currntDtls, txt1_NPlate_crntDtls, txt2_NPlate_crntDtls,
            txt3_NPlate_crntDtls, txt4_NPlate_crntDtls;


    RatingBar rate_currntDtls;
    ImageView imgStar, imgCall;
    RelativeLayout rlCancelTrip;
    Button btnCancelCurrentTripDetails;
    ProgressBar pbCancelCurrentTripDetails;
    LinearLayout llDriverName, llCarType;
    CardView cvDriverInfo_currntDtls, cvDateTime__currntDtls;
    //no need to make it new
    public static Trip model = new Trip();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_trip_details);

        context = this;
        presenter.attachView(context, this);
        bindView();


        txtPursuitCode_currntDtls.setText(String.valueOf(model.getiOfficialTrip()));
        txtTripStatus_currntDtls.setText(model.getStrOfficialStatus_strComment());
        txtRequestDate_Time_currntDtls.setText(model.getStrRequestTime() + " - " + model.getStrRequestDate());
        txtPassengerNames_currntDtls.setText(model.getStrPassengers());
        txtSupposedDate_Time_currntDtls.setText(model.getStrTripTime() + " - " + model.getStrTripDate());
        txtOriginName_currntDtls.setText(model.getStrOriginAddress());
        txtDesName_currntDtls.setText(model.getStrDestinationAddress());
        txtReasonImportance_currntDtls.setText(model.getStrTripReason_strComment() + " - " + model.getStrTripImportance_strComment());
        //todo below should be model.getStrMobile(savari sangin....)
        txtVehicleType_currentTrip.setText(model.getStrMobileType());

        if (model.isbHaveReturn())
            txtReturn_currntDtls.setText("بله");
        else
            txtReturn_currntDtls.setText("خیر");

        if (model.isbExclusive())
            txtInOrder_currntDtls.setText("بله");
        else
            txtInOrder_currntDtls.setText("خیر");


        if (model.getStrComment().length() != 0)
            txtComment_currntDtls.setText(model.getStrComment());
        else
            txtEmptyCommentCurrentTripDetail.setText("-");

        txtDriverName_currentTrip.setText(model.getStrDriverName());
        txtCarModel_currntDtls.setText(model.getStrMobileType());
//        txtArrivingTimeCalculate_currntDtls.setText(model.getStrEstRedy4TrimpTime());
        txtTripPreparationTime_currntDtls.setText(model.getStrRedy4TrimpTime());
        txtStartTripTime_currntDtls.setText(model.getStrTripTime());
        txtFinishTripDateTime_currntDtls.setText(model.getStrFinishDate() + "-" + model.getStrTrimpFinishTime());



        if (model.getStrTrimTimeEstimate() != null)
            txtTripDurationTime_currntDtls.setText(Time.minutesToHours(model.getStrTrimTimeEstimate()));

        else
            txtTripDurationTime_currntDtls.setText("0" + " " + "دقیقه");



        if (model.getStrEstRedy4TrimpTime() != null)
            txtArrivingTimeCalculate_currntDtls.setText(Time.minutesToHours(model.getStrEstRedy4TrimpTime()));
        else
            txtArrivingTimeCalculate_currntDtls.setText("0" + " " + "دقیقه");





        rate_currntDtls.setRating(model.getiSatisfication() / 20);


        switch (model.getStrTripImportance_strComment()) {
            case "عادی":
                imgStar.setImageDrawable(getResources().getDrawable(R.drawable.star1));
                break;
            case "فوری":
                imgStar.setImageDrawable(getResources().getDrawable(R.drawable.star2));
                break;
            case "خیلی فوری":
                imgStar.setImageDrawable(getResources().getDrawable(R.drawable.star3));
                break;
        }


        // hide and show cancel's Button
        if (model.getTiTripStatus() == 0 || model.getTiTripStatus() == 1 || model.getTiTripStatus() == 2 || model.getTiTripStatus() == 3)
            rlCancelTrip.setVisibility(View.VISIBLE);
        else if (model.getTiTripStatus() == 5 || model.getTiTripStatus() == 6 || model.getTiTripStatus() == 7)
            rlCancelTrip.setVisibility(View.GONE);


//        // hide driverName && hide carType while trip status is not confirm by driver
//        if (model.getTiTripStatus() == 0 || model.getTiTripStatus() == 1 || model.getTiTripStatus() == 2) {
//            llDriverName.setVisibility(View.GONE);
//            llCarType.setVisibility(View.GONE);
//        } else if (model.getTiTripStatus() == 3 || model.getTiTripStatus() == 5 || model.getTiTripStatus() == 6) {
//            llDriverName.setVisibility(View.VISIBLE);
//            llCarType.setVisibility(View.VISIBLE);
//        }


        if (model.getTiTripStatus() == 0 || model.getTiTripStatus() == 1 || model.getTiTripStatus() == 2) {
            cvDriverInfo_currntDtls.setVisibility(View.GONE);
        } else {
            cvDriverInfo_currntDtls.setVisibility(View.VISIBLE);

            //numberPlate
            String numberPlate = model.getStrVehicleNo();
            txt1_NPlate_crntDtls.setText(numberPlate.substring(0, 2));
            txt2_NPlate_crntDtls.setText(numberPlate.substring(3, 4));
            txt3_NPlate_crntDtls.setText(numberPlate.substring(5, 8));
            txt4_NPlate_crntDtls.setText(numberPlate.substring(11));
        }


        if (model.getTiTripStatus() == 7) {
            cvDateTime__currntDtls.setVisibility(View.VISIBLE);
        } else {
            cvDateTime__currntDtls.setVisibility(View.GONE);
        }


        btnCancelCurrentTripDetails.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("آیا از لغو سفر با کد:" + model.getiOfficialTrip() + " مطمئن هستید؟");
            dialog.setPositiveButton("بله", (dialog1, which) -> presenter.btnCanceledPressed(model));
            dialog.setNegativeButton("خیر", (dialog12, which) -> dialog12.dismiss());
            dialog.create().show();
        });

        imgCall.setOnClickListener(v -> presenter.btnCallPressed(model.getDriverMobile()));

    }

    private void bindView() {
        txtPursuitCode_currntDtls = findViewById(R.id.txtPursuitCode_currntDtls);
        txtTripStatus_currntDtls = findViewById(R.id.txtTripStatus_currntDtls);
        txtRequestDate_Time_currntDtls = findViewById(R.id.txtRequestDate_Time_currntDtls);
        txtPassengerNames_currntDtls = findViewById(R.id.txtPassengerNames_currntDtls);
        txtSupposedDate_Time_currntDtls = findViewById(R.id.txtSupposedDate_Time_currntDtls);
        txtOriginName_currntDtls = findViewById(R.id.txtOriginName_currntDtls);
        txtDesName_currntDtls = findViewById(R.id.txtDesName_currntDtls);
        txtReasonImportance_currntDtls = findViewById(R.id.txtReasonImportance_currntDtls);
        txtVehicleType_currentTrip = findViewById(R.id.txtVehicleType_currentTrip);
        txtReturn_currntDtls = findViewById(R.id.txtReturn_currntDtls);
        txtInOrder_currntDtls = findViewById(R.id.txtInOrder_currntDtls);
        txtEmptyCommentCurrentTripDetail = findViewById(R.id.txtEmptyCommentCurrentTripDetail);
        txtComment_currntDtls = findViewById(R.id.txtComment_currntDtls);
        txtDriverName_currentTrip = findViewById(R.id.txtDriverName_currentTrip);
//        txtNumberPlate_currntDtls = findViewById(R.id.txtNumberPlate_currntDtls);
        txtCarModel_currntDtls = findViewById(R.id.txtCarModel_currntDtls);
        txtArrivingTimeCalculate_currntDtls = findViewById(R.id.txtArrivingTimeCalculate_currntDtls);
        txtTripPreparationTime_currntDtls = findViewById(R.id.txtTripPreparationTime_currntDtls);
        txtStartTripTime_currntDtls = findViewById(R.id.txtStartTripTime_currntDtls);
        txtFinishTripDateTime_currntDtls = findViewById(R.id.txtFinishTripDateTime_currntDtls);
        txtTripDurationTime_currntDtls = findViewById(R.id.txtTripDurationTime_currntDtls);
        txt1_NPlate_crntDtls = findViewById(R.id.txt1_NPlate_crntDtls);
        txt2_NPlate_crntDtls = findViewById(R.id.txt2_NPlate_crntDtls);
        txt3_NPlate_crntDtls = findViewById(R.id.txt3_NPlate_crntDtls);
        txt4_NPlate_crntDtls = findViewById(R.id.txt4_NPlate_crntDtls);

        rate_currntDtls = findViewById(R.id.rate_currntDtls);
        rlCancelTrip = findViewById(R.id.rlCancelTrip);
        btnCancelCurrentTripDetails = findViewById(R.id.btnCancelCurrentTripDetails);
        pbCancelCurrentTripDetails = findViewById(R.id.pbCancelCurrentTripDetails);
        llDriverName = findViewById(R.id.llDriverName);
        llCarType = findViewById(R.id.llCarType);
        imgStar = findViewById(R.id.imgStar_currentTripDetail);
        imgCall = findViewById(R.id.imgCall_currntDtls);
        cvDriverInfo_currntDtls = findViewById(R.id.cvDriverInfo_currntDtls);
        cvDateTime__currntDtls = findViewById(R.id.cvDateTime__currntDtls);
    }

    @Override
    public void showProgressBar() {
        pbCancelCurrentTripDetails.setVisibility(View.VISIBLE);
        btnCancelCurrentTripDetails.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        pbCancelCurrentTripDetails.setVisibility(View.GONE);
        btnCancelCurrentTripDetails.setVisibility(View.VISIBLE);
    }
}
