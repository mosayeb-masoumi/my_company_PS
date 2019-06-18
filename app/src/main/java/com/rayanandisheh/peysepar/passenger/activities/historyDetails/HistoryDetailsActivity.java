package com.rayanandisheh.peysepar.passenger.activities.historyDetails;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.Time;
import com.rayanandisheh.peysepar.passenger.models.Trip;


public class HistoryDetailsActivity extends PersianAppcompatActivity implements Contract.View {

    Contract.Presenter presenter = new Presenter();
    Context context;
    TextView txtImportance_historyDtls,txtPersuadeCode_HistoryTripDtls,txtTripStatus_historyDtls,txtRequestDate_Time_historyDtls,txtPassengerNames_HistoryTripDtls,
            txtSupposedDate_Time_historyDtls,txtOriginName_HistoryTripDtls,txtDesName_HistoryTripDtls,txtReasonImportance_historyDtls,
            txtVehicleType_HistoryTripDtls,txtReturn_historyDtls,txtInOrder_historyDtls,txtEmptyCommentHistoryTripDetail,
            txtComment_historyDtls,txtDriverName_HistoryTripDtls,txtCarModel_historyDtls,txtStartTripTime_historyDtls,
            txtFinishTripDateTime_historyDtls,txtTripDurationTime_historyDtls,txt1_NPlate_historyDtls ,txt2_NPlate_historyDtls,
            txt3_NPlate_historyDtls, txt4_NPlate_historyDtls;



    RatingBar rateNotToday_HistoryTripDtls, rateToday_historyDetails;
    Button btn_rateTodayHistoryDetail;
    ProgressBar pb_rateTodayHistoryDetail;
    RelativeLayout rlScoreNotToday, rlScoreToday;
    ImageView imgStar;

    public static Trip model = new Trip();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        context = this;
        presenter.attachView(context, this);
        bindView();

        txtImportance_historyDtls.setText(model.getStrTripImportance_strComment());
        txtPersuadeCode_HistoryTripDtls.setText(String.valueOf(model.getiOfficialTrip()));
        txtTripStatus_historyDtls.setText(model.getStrOfficialStatus_strComment());
        txtRequestDate_Time_historyDtls.setText(model.getStrRequestTime().trim() + " - " + model.getStrRequestDate());
        txtPassengerNames_HistoryTripDtls.setText(model.getStrPassengers());
        txtSupposedDate_Time_historyDtls.setText(model.getStrTripTime() + " - " + model.getStrTripDate());
        txtOriginName_HistoryTripDtls.setText(model.getStrOriginAddress());
        txtDesName_HistoryTripDtls.setText(model.getStrDestinationAddress());
        txtReasonImportance_historyDtls.setText(model.getStrTripReason_strComment() + " - " + model.getStrTripImportance_strComment());
        txtVehicleType_HistoryTripDtls.setText(model.getStrMobileType());

        if (model.isbHaveReturn())
            txtReturn_historyDtls.setText("بله");
        else
            txtReturn_historyDtls.setText("خیر");

        if (model.isbExclusive())
            txtInOrder_historyDtls.setText("بله");
        else
            txtInOrder_historyDtls.setText("خیر");

        if (model.getStrComment().length() != 0)
            txtComment_historyDtls.setText(model.getStrComment());
        else
            txtEmptyCommentHistoryTripDetail.setText("-");

        txtDriverName_HistoryTripDtls.setText(model.getStrDriverName());

        txtCarModel_historyDtls.setText(model.getStrMobileType());
        txtStartTripTime_historyDtls.setText(model.getStrTripTime());
        txtFinishTripDateTime_historyDtls.setText(model.getStrFinishDate()+"-"+model.getStrTrimpFinishTime());


        if(model.getStrTrimTimeEstimate() !=null)
            txtTripDurationTime_historyDtls.setText(Time.minutesToHours(model.getStrTrimTimeEstimate()));
        else
            txtTripDurationTime_historyDtls.setText("-");



        //numberPlate
        String numberPlate = model.getStrVehicleNo();
        txt1_NPlate_historyDtls.setText(numberPlate.substring(0, 2));
        txt2_NPlate_historyDtls.setText(numberPlate.substring(3, 4));
        txt3_NPlate_historyDtls.setText(numberPlate.substring(5, 8));
        txt4_NPlate_historyDtls.setText(numberPlate.substring(11));







        rateNotToday_HistoryTripDtls.setRating(model.getiSatisfication() / 20);


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



        if (Time.getNowPersianDate().equals(model.getStrFinishDate())) {
            rlScoreToday.setVisibility(View.VISIBLE);
            rlScoreNotToday.setVisibility(View.GONE);
        } else {
            rlScoreNotToday.setVisibility(View.VISIBLE);
            rlScoreToday.setVisibility(View.GONE);
        }

        btn_rateTodayHistoryDetail.setOnClickListener(v ->
                presenter.sendRate(rateToday_historyDetails.getRating(), model.getiOfficialTrip()));
    }

    private void bindView() {
        txtImportance_historyDtls=findViewById(R.id.txtImportance_historyDtls);
        txtPersuadeCode_HistoryTripDtls = findViewById(R.id.txtPersuadeCode_HistoryTripDtls);
        txtTripStatus_historyDtls=findViewById(R.id.txtTripStatus_historyDtls);
        txtRequestDate_Time_historyDtls=findViewById(R.id.txtRequestDate_Time_historyDtls);
        txtPassengerNames_HistoryTripDtls = findViewById(R.id.txtPassengerNames_HistoryTripDtls);
        txtSupposedDate_Time_historyDtls=findViewById(R.id.txtSupposedDate_Time_historyDtls);
        txtOriginName_HistoryTripDtls = findViewById(R.id.txtOriginName_HistoryTripDtls);
        txtDesName_HistoryTripDtls = findViewById(R.id.txtDesName_HistoryTripDtls);
        txtReasonImportance_historyDtls=findViewById(R.id.txtReasonImportance_historyDtls);
        txtVehicleType_HistoryTripDtls = findViewById(R.id.txtVehicleType_HistoryTripDtls);
        txtReturn_historyDtls=findViewById(R.id.txtReturn_historyDtls);
        txtInOrder_historyDtls=findViewById(R.id.txtInOrder_historyDtls);
        txtEmptyCommentHistoryTripDetail=findViewById(R.id.txtEmptyCommentHistoryTripDetail);
        txtComment_historyDtls = findViewById(R.id.txtComment_historyDtls);
        txtDriverName_HistoryTripDtls = findViewById(R.id.txtDriverName_HistoryTripDtls);
        txtCarModel_historyDtls=findViewById(R.id.txtCarModel_historyDtls);
        txtStartTripTime_historyDtls=findViewById(R.id.txtStartTripTime_historyDtls);
        txtFinishTripDateTime_historyDtls=findViewById(R.id.txtFinishTripDateTime_historyDtls);
        txtTripDurationTime_historyDtls=findViewById(R.id.txtTripDurationTime_historyDtls);
        txt1_NPlate_historyDtls=findViewById(R.id.txt1_NPlate_historyDtls);
        txt2_NPlate_historyDtls=findViewById(R.id.txt2_NPlate_historyDtls);
        txt3_NPlate_historyDtls=findViewById(R.id.txt3_NPlate_historyDtls);
        txt4_NPlate_historyDtls=findViewById(R.id.txt4_NPlate_historyDtls);
        imgStar=findViewById(R.id.imgStar_historyTripDetail);
        rateNotToday_HistoryTripDtls = findViewById(R.id.rateNotToday_historyDetails);
        rateToday_historyDetails = findViewById(R.id.rateToday_historyDetails);
        btn_rateTodayHistoryDetail = findViewById(R.id.btn_rateTodayHistoryDetail);
        pb_rateTodayHistoryDetail = findViewById(R.id.pb_rateTodayHistoryDetail);
        rlScoreNotToday = findViewById(R.id.rlScoreNotToday);
        rlScoreToday = findViewById(R.id.rlScoreToday);
    }

    @Override
    public void showProgressBar() {
        pb_rateTodayHistoryDetail.setVisibility(View.VISIBLE);
        btn_rateTodayHistoryDetail.setVisibility(View.GONE);
    }

    @Override
    public void setTodayScore() {
        rateToday_historyDetails.setRating(model.getiSatisfication() / 20);
    }

    @Override
    public void hideProgressBar() {
        pb_rateTodayHistoryDetail.setVisibility(View.GONE);
        btn_rateTodayHistoryDetail.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rateToday_historyDetails.setRating(model.getiSatisfication() / 20);
    }
}
