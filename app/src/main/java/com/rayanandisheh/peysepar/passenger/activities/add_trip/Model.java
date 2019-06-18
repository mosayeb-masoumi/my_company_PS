package com.rayanandisheh.peysepar.passenger.activities.add_trip;


import android.content.Context;

import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.models.TripInsert;
import com.rayanandisheh.peysepar.passenger.models.TripSAD;
import com.rayanandisheh.peysepar.passenger.services.APIClient;
import com.rayanandisheh.peysepar.passenger.services.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public List<String> getSpnOriginData() {
        List<String> origin = new ArrayList<String>();
        origin.add("انتخاب کنید");
        origin.add("میدان آزادی");
        origin.add("انقلاب");
        origin.add("پیروزی");
        return origin;
    }

    @Override
    public List<String> getSpnDesData() {
        List<String> destination = new ArrayList<String>();
        destination.add("انتخاب کنید");
        destination.add("بیمه سلامت");
        destination.add("ترمینال 4 فرودگاه مهرآباد");
        return destination;
    }


    @Override
    public List<String> getSpnReasonData() {
        List<String> reasons = new ArrayList<>();
        for (int i = 0; i < App.userInfo.getTripReason().size(); i++)
            reasons.add(App.userInfo.getTripReason().get(i).getStrComment());
        return reasons;
    }


    @Override
    public List<String> getSpnTypeCar() {
        List<String> cartype = new ArrayList<>();
        for (int i = 0; i < App.userInfo.getMobileType().size(); i++)
            cartype.add(App.userInfo.getMobileType().get(i).getStrComment());
        return cartype;
    }



    @Override
    public List<String> getImportanceData() {
        List<String> importance = new ArrayList<>();
        for (int i = 0; i < App.userInfo.getTripImportance().size(); i++)
            importance.add(App.userInfo.getTripImportance().get(i).getStrComment());
        return importance;
    }


    @Override
    public List<String> getOriginDes() {
        List<String> originDes = new ArrayList<String>();
        for (int i = 0; i < App.userInfo.getTripSAD().size(); i++)
            originDes.add(App.userInfo.getTripSAD().get(i).getStrName());
        return originDes;
    }


    @Override
    public void requestAddTrip(int spnReason, int spnImportance, int spnCarType, String autoCompleteTxtOrigin,
                               String autoCompleteTxtDes, String edtDate, String numberPickerTime,
                               boolean chkBoxInService, boolean chkBxReturn, boolean chkBxMissionary, String list, String strComment) {

        TripInsert tripInsert = new TripInsert();
        tripInsert.setTiTripReason((short) App.userInfo.getTripReason().get(spnReason).getTiTripReason());
        tripInsert.setTiTripImportance((short) App.userInfo.getTripImportance().get(spnImportance).getTiTripImportance());
        tripInsert.setMobileType((short) App.userInfo.getMobileType().get(spnCarType).getTiMobileType());

        tripInsert.setStrComment(strComment);

        tripInsert.setStrTripDate(edtDate);
        tripInsert.setStrTripTime(numberPickerTime);

        tripInsert.setbHaveReturn(chkBxReturn);
        tripInsert.setbExclusive(chkBoxInService);
        tripInsert.setbMissionary(chkBxMissionary);

        tripInsert.setStrApplicantMobile(Cache.getString("mobileNumber"));
        tripInsert.setStrPassengersName(list);

//        for (TripImportance tripImportance : App.userInfo.getTripImportance()) {
//            if (autoCompleteTxtDes.equals(tripImportance.getStrComment())){
//                tripImportance.getTiTripImportance();
//            }
//        }


        for (TripSAD tripSAD : App.userInfo.getTripSAD()) {
            if (autoCompleteTxtOrigin.equals(tripSAD.getStrName())) {
                tripInsert.setiTripSADSource(tripSAD.getiTripSad());
            }
        }

        if (tripInsert.getiTripSADSource() == -1) {
            presenter.noValidOrigin();
            return;
        }


        for (TripSAD tripSAD : App.userInfo.getTripSAD()) {
            if (autoCompleteTxtDes.equals(tripSAD.getStrName())) {
//                tripSAD.getiTripSad();
                tripInsert.setiTripSADDestination(tripSAD.getiTripSad());
            }

        }
        if (!chkBoxInService && tripInsert.getiTripSADDestination() == -1) {
            presenter.noValidDes();
            return;
        }

        if (tripInsert.getiTripSADSource() == tripInsert.getiTripSADDestination()) {
            presenter.ErrorEqualOriginDestination();
            return;
        }


        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<Integer> call = apiService.getInsertTrip(tripInsert);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if (response.code() == 200 && response.body() != null) {

                    presenter.tripInsertResult(response.body());
                    App.pursuitCode = response.body();

                } else {
                    presenter.tripInsertResult(-4);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                presenter.tripInsertResult(-1);
            }
        });
    }

}
