package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("strTripName")
    private String strTripName;
    @SerializedName("strTripDate")
    private String strTripDate;
    @SerializedName("strTripTime")
    private String strTripTime;
    @SerializedName("strDriverName")
    private String strDriverName;
    @SerializedName("iSatisfication")
    private int iSatisfication;
    @SerializedName("strMobileType")
    private String strMobileType;
    @SerializedName("strDestinationAddress")
    private String strDestinationAddress;
    @SerializedName("strDestinationName")
    private String strDestinationName;
    @SerializedName("strOriginAddress")
    private String strOriginAddress;
    @SerializedName("strOriginName")
    private String strOriginName;
    @SerializedName("strFileAddress")
    private String strFileAddress;
    @SerializedName("strPassengers")
    private String strPassengers;
    @SerializedName("iOfficialTrip")
    private int iOfficialTrip;
    @SerializedName("strOfficialStatus_strComment")
    private String strOfficialStatus_strComment;
    @SerializedName("tiTripStatus")
    private int tiTripStatus;
    @SerializedName("strFinishDate")
    private String strFinishDate;
    @SerializedName("strComment")
    private String strComment;

    @SerializedName("strEstRedy4TrimpTime")
    private String strEstRedy4TrimpTime;
    @SerializedName("strRedy4TrimpTime")
    private String strRedy4TrimpTime;
    @SerializedName("strTrimpFinishTime")
    private String strTrimpFinishTime;
    @SerializedName("strTrimTimeEstimate")
    private String strTrimTimeEstimate;
    @SerializedName("strVehicleNo")
    private String strVehicleNo;

    @SerializedName("bExclusive")
    private boolean bExclusive;
    @SerializedName("bHaveReturn")
    private boolean bHaveReturn;

    @SerializedName("Result")
    private int Result ;
    @SerializedName("message")
    private String message ;

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isbExclusive() {
        return bExclusive;
    }

    public void setbExclusive(boolean bExclusive) {
        this.bExclusive = bExclusive;
    }

    public boolean isbHaveReturn() {
        return bHaveReturn;
    }

    public void setbHaveReturn(boolean bHaveReturn) {
        this.bHaveReturn = bHaveReturn;
    }




    public String getStrEstRedy4TrimpTime() {
        return strEstRedy4TrimpTime;
    }

    public void setStrEstRedy4TrimpTime(String strEstRedy4TrimpTime) {
        this.strEstRedy4TrimpTime = strEstRedy4TrimpTime;
    }

    public String getStrRedy4TrimpTime() {
        return strRedy4TrimpTime;
    }

    public void setStrRedy4TrimpTime(String strRedy4TrimpTime) {
        this.strRedy4TrimpTime = strRedy4TrimpTime;
    }

    public String getStrTrimpFinishTime() {
        return strTrimpFinishTime;
    }

    public void setStrTrimpFinishTime(String strTrimpFinishTime) {
        this.strTrimpFinishTime = strTrimpFinishTime;
    }

    public String getStrTrimTimeEstimate() {
        return strTrimTimeEstimate;
    }

    public void setStrTrimTimeEstimate(String strTrimTimeEstimate) {
        this.strTrimTimeEstimate = strTrimTimeEstimate;
    }

    public String getStrVehicleNo() {
        return strVehicleNo;
    }

    public void setStrVehicleNo(String strVehicleNo) {
        this.strVehicleNo = strVehicleNo;
    }

    public String getStrComment() {
        return strComment;
    }

    public void setStrComment(String strComment) {
        this.strComment = strComment;
    }

    public String getStrFinishDate() {
        return strFinishDate;
    }

    public void setStrFinishDate(String strFinishDate) {
        this.strFinishDate = strFinishDate;
    }

    @SerializedName("PassengerSig")
    private String PassengerSig;
    @SerializedName("strTripReason_strComment")
    private String strTripReason_strComment;
    @SerializedName("strTripImportance_strComment")
    private String strTripImportance_strComment;
    @SerializedName("strRequestTime")
    private String strRequestTime;
    @SerializedName("strRequestDate")
    private String strRequestDate;
    @SerializedName("strApplicantName")
    private String strApplicantName;
    @SerializedName("strApplicantFamily")
    private String strApplicantFamily;
    @SerializedName("strApplicantMobile")
    private String strApplicantMobile;
    @SerializedName("DriverMobile")
    private String DriverMobile;


    @SerializedName("fLonSource")
    public String fLonSource;
    @SerializedName("fLatSource")
    public String fLatSource;
    @SerializedName("fLonDestination")
    public String fLonDestination;
    @SerializedName("fLatDestination")
    public String fLatDestination;


    public String getfLonSource() {
        return fLonSource;
    }

    public void setfLonSource(String fLonSource) {
        this.fLonSource = fLonSource;
    }

    public String getfLatSource() {
        return fLatSource;
    }

    public void setfLatSource(String fLatSource) {
        this.fLatSource = fLatSource;
    }

    public String getfLonDestination() {
        return fLonDestination;
    }

    public void setfLonDestination(String fLonDestination) {
        this.fLonDestination = fLonDestination;
    }

    public String getfLatDestination() {
        return fLatDestination;
    }

    public void setfLatDestination(String fLatDestination) {
        this.fLatDestination = fLatDestination;
    }

    public String getPassengerSig() {
        return PassengerSig;
    }

    public void setPassengerSig(String passengerSig) {
        PassengerSig = passengerSig;
    }

    public String getStrTripReason_strComment() {
        return strTripReason_strComment;
    }

    public void setStrTripReason_strComment(String strTripReason_strComment) {
        this.strTripReason_strComment = strTripReason_strComment;
    }

    public String getStrTripImportance_strComment() {
        return strTripImportance_strComment;
    }

    public void setStrTripImportance_strComment(String strTripImportance_strComment) {
        this.strTripImportance_strComment = strTripImportance_strComment;
    }

    public String getStrRequestTime() {
        return strRequestTime;
    }

    public void setStrRequestTime(String strRequestTime) {
        this.strRequestTime = strRequestTime;
    }

    public String getStrRequestDate() {
        return strRequestDate;
    }

    public void setStrRequestDate(String strRequestDate) {
        this.strRequestDate = strRequestDate;
    }

    public String getStrApplicantName() {
        return strApplicantName;
    }

    public void setStrApplicantName(String strApplicantName) {
        this.strApplicantName = strApplicantName;
    }

    public String getStrApplicantFamily() {
        return strApplicantFamily;
    }

    public void setStrApplicantFamily(String strApplicantFamily) {
        this.strApplicantFamily = strApplicantFamily;
    }

    public String getStrApplicantMobile() {
        return strApplicantMobile;
    }

    public void setStrApplicantMobile(String strApplicantMobile) {
        this.strApplicantMobile = strApplicantMobile;
    }

    public String getDriverMobile() {
        return DriverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        DriverMobile = driverMobile;
    }

    public int getTiTripStatus() {
        return tiTripStatus;
    }

    public void setTiTripStatus(int tiTripStatus) {
        this.tiTripStatus = tiTripStatus;
    }

    public String getStrOfficialStatus_strComment() {
        return strOfficialStatus_strComment;
    }

    public void setStrOfficialStatus_strComment(String strOfficialStatus_strComment) {
        this.strOfficialStatus_strComment = strOfficialStatus_strComment;
    }

    public int getiOfficialTrip() {
        return iOfficialTrip;
    }

    public void setiOfficialTrip(int iOfficialTrip) {
        this.iOfficialTrip = iOfficialTrip;
    }

    public String getStrTripName() {
        return strTripName;
    }

    public void setStrTripName(String strTripName) {
        this.strTripName = strTripName;
    }

    public String getStrTripDate() {
        return strTripDate;
    }

    public void setStrTripDate(String strTripDate) {
        this.strTripDate = strTripDate;
    }

    public String getStrTripTime() {
        return strTripTime;
    }

    public void setStrTripTime(String strTripTime) {
        this.strTripTime = strTripTime;
    }

    public String getStrDriverName() {
        return strDriverName;
    }

    public void setStrDriverName(String strDriverName) {
        this.strDriverName = strDriverName;
    }

    public int getiSatisfication() {
        return iSatisfication;
    }

    public void setiSatisfication(int iSatisfication) {
        this.iSatisfication = iSatisfication;
    }

    public String getStrMobileType() {
        return strMobileType;
    }

    public void setStrMobileType(String strMobileType) {
        this.strMobileType = strMobileType;
    }

    public String getStrDestinationAddress() {
        return strDestinationAddress;
    }

    public void setStrDestinationAddress(String strDestinationAddress) {
        this.strDestinationAddress = strDestinationAddress;
    }

    public String getStrDestinationName() {
        return strDestinationName;
    }

    public void setStrDestinationName(String strDestinationName) {
        this.strDestinationName = strDestinationName;
    }

    public String getStrOriginAddress() {
        return strOriginAddress;
    }

    public void setStrOriginAddress(String strOriginAddress) {
        this.strOriginAddress = strOriginAddress;
    }

    public String getStrOriginName() {
        return strOriginName;
    }

    public void setStrOriginName(String strOriginName) {
        this.strOriginName = strOriginName;
    }

    public String getStrFileAddress() {
        return strFileAddress;
    }

    public void setStrFileAddress(String strFileAddress) {
        this.strFileAddress = strFileAddress;
    }

    public String getStrPassengers() {
        return strPassengers;
    }

    public void setStrPassengers(String strPassengers) {
        this.strPassengers = strPassengers;
    }

//   @SerializedName("strTripName")
//    private String strTripName ;
//    @SerializedName("strTripDate")
//    private String strTripDate ;
//    @SerializedName("strTripTime")
//    private String strTripTime ;
//    @SerializedName("strDriverName")
//    private String strDriverName ;
//    @SerializedName("iSatisfication")
//    private int iSatisfication ;
//    @SerializedName("strMobileType")
//    private String strMobileType ;
//    @SerializedName("strDestinationAddress")
//    private String strDestinationAddress ;
//    @SerializedName("strOriginAddress")
//    private String strOriginAddress ;
//    @SerializedName("strFileAddress")
//    private String strFileAddress ;
//
//
//    public String getStrTripName() {
//        return strTripName;
//    }
//
//    public void setStrTripName(String strTripName) {
//        this.strTripName = strTripName;
//    }
//
//    public String getStrTripDate() {
//        return strTripDate;
//    }
//
//    public void setStrTripDate(String strTripDate) {
//        this.strTripDate = strTripDate;
//    }
//
//    public String getStrTripTime() {
//        return strTripTime;
//    }
//
//    public void setStrTripTime(String strTripTime) {
//        this.strTripTime = strTripTime;
//    }
//
//    public String getStrDriverName() {
//        return strDriverName;
//    }
//
//    public void setStrDriverName(String strDriverName) {
//        this.strDriverName = strDriverName;
//    }
//
//    public int getiSatisfication() {
//        return iSatisfication;
//    }
//
//    public void setiSatisfication(int iSatisfication) {
//        this.iSatisfication = iSatisfication;
//    }
//
//    public String getStrMobileType() {
//        return strMobileType;
//    }
//
//    public void setStrMobileType(String strMobileType) {
//        this.strMobileType = strMobileType;
//    }
//
//    public String getStrDestinationAddress() {
//        return strDestinationAddress;
//    }
//
//    public void setStrDestinationAddress(String strDestinationAddress) {
//        this.strDestinationAddress = strDestinationAddress;
//    }
//
//    public String getStrOriginAddress() {
//        return strOriginAddress;
//    }
//
//    public void setStrOriginAddress(String strOriginAddress) {
//        this.strOriginAddress = strOriginAddress;
//    }
//
//    public String getStrFileAddress() {
//        return strFileAddress;
//    }
//
//    public void setStrFileAddress(String strFileAddress) {
//        this.strFileAddress = strFileAddress;
//    }
}
