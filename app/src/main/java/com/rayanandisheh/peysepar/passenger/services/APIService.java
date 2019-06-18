package com.rayanandisheh.peysepar.passenger.services;

import android.text.Editable;

import android.widget.EditText;
import com.google.android.gms.maps.model.LatLng;
import com.rayanandisheh.peysepar.passenger.models.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;


public interface APIService {

    //real
    @POST("RegisterUser")
    Call<Register> getRegister(@Body Register register);


    @POST("Activate")
    Call<UserInfo> getActivate(@Body Register register);


    @POST("Login")
    Call<UserInfo> getLogin(@Body Register register);


    @POST("SendActivateCode")
    Call<Integer> getSmsCode(@Body Register register);


    @POST("TripListHistory")
    Call<List<Trip>> getTrip(@Body UserInfo userInfo);


    @POST("InsertTrip")
    Call<Integer> getInsertTrip(@Body TripInsert tripInsert);


    @POST("UpdateProfile")
    Call<Register> getUpdateProfile(@Body Register register);


    @POST("EndTrip")
    Call<Integer> endTrip(@Body EndTrip EndTrip);


    @POST("CheckWebServiceAddress")
    Call<Integer> checkWebServiceAddress();


    @POST("CancelTrip")
    Call<Integer> cancelTrip(@Body Trip trip);


    @POST("Refresh")
    Call<UserInfo> refresh(@Body UserInfo userInfo);


    @POST("Report")
    Call<Report> report(@Body Report report);


    @POST("ManageTrip")
    Call<List<Trip>> manageTrip(@Body UserInfo userInfo);


    //model related to taaid alert new tablayout
    @POST("ManagmentConfirm")
    Call<Data> managmentConfirm(@Body Trip trip);



    @POST("DriverList")
    Call<List<DriverInfo>> driverList(@Body UserInfo userInfo);

    @POST("AssignDriver")
    Call<Data> assignDriver(@Body AssignDriver assignDriver);



    @POST("ChangeScore")
    Call<Data> ChangeScore(@Body Score score);


    @POST("Replacement")
    Call<UserInfo> Replacement(@Body Replace replace);


    @POST("Dashboard")
    Call<Dashboard> Dashboard(@Body Score Score);


////////////////////////////////........./////////////////////////////..........//////////////////////////////
////////////////////////////////........./////////////////////////////..........////////////////////////////////





















//shahriar
    @POST("RayanAndishehNasr/Login")
    Call<User> Login(@Body User user);

    @POST("RayanAndishehNasr/Forget")
    Call<Integer> forgot(@Body User user);

    @POST("RayanAndishehNasr/Change")
    Call<Integer> changePass(@Body User user);

    @POST("RayanAndishehNasr/Movement")
    Call<String> locations(@Body Locations location);

    @POST("RayanAndishehNasr/Around")
    Call<List<LatLng>> aroundCars(@Body LatLng latLong);

    @POST("RayanAndishehNasr/Around")
    Call<Integer> feedback(Editable feedback, int rate);

///////////////////////////////////////////////////////////////////////////////////////////
//    @POST("Rest/GetAccidenceList")
//    Call<List<Accidence>> GetAccidenceList(@Body User user);
//
//    @POST("Rest/FormDesigner")
//    Call<FormDesigner> FormDesigner(@Body User user);
//
//    @POST("Rest/InsertFormDesigner")
//    Call<FormDesigner> InsertFormDesigner(@Body FormDesigner InsertFormDesigner);
//
//    @POST("Rest/TestConnection/")
//    Call<String> TestConnection(@Body User user);
//
//    @POST("Rest/GetBaseData")
//    Call<BaseData> GetBaseData(@Body User user);
//
//    @POST("Rest/SaveMessage")
//    Call<MobileContact> SaveMessage(@Body MobileContact mobileContact);
//
//    @POST("Rest/SaveAccidenceAction")
//    Call<Accidence> SaveAccidenceAction(@Body Accidence accidence);
//
//    @POST("Rest/SaveAccidenceActionOfflineList")
//    Call<Accidence> SaveAccidenceActionOfflineList(@Body List<Accidence> accidence);
//
//    @POST("Rest/InsertListFormDesigner")
//    Call<FormDesigner> SaveFormDesignerActionOfflineList(@Body List<FormDesigner> formDesigners);
//
//    @POST("Rest/SaveAccidence")
//    Call<AccidenceReport> SaveAccidence(@Body AccidenceReport accidenceReport);
//
//    /////test
//    @POST("Rest/SaveAccidenceList")
//    Call<AccidenceReport> SaveAccidenceList(@Body List<AccidenceReport> accidenceReport);
//
//    @POST("Rest/SendLocation")
//    Call<Locations> SendLocation(@Body List<Locations> location);
//
//    @POST("Rest/GetMessageGathers")
//    Call<List<Message>> GetMessageGathers(@Body User user);
//
//    @POST("Rest/viewMessageWithActions")
//    Call<List<Proceeding>> viewMessageWithActions(@Body Message message);
//
//    @POST("Rest/SaveAction")
//    Call<Proceeding> SaveAction(@Body Proceeding proceeding);
//
//    @POST("Rest/ChangePassword")
//    Call<User> ChangePassword(@Body User user);
//
//    @POST("Rest/PushNotfication")
//    Call<Notify> PushNotfication(@Body Notify notify);
//
//    @POST("Rest/PeopleRelationStatus")
//    Call<PeopleRelationStatus> PeopleRelationStatus(@Body PeopleRelationStatus peopleRelationStatus);
}