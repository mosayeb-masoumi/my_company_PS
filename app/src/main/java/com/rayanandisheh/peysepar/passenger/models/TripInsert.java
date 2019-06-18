package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class TripInsert {

    @SerializedName("tiTripReason")
    private short tiTripReason;
    @SerializedName("strPassengersName")
    private String strPassengersName ;
    @SerializedName("strRequestTime")
    private String strRequestTime ;
    @SerializedName("strRequestDate")
    private String strRequestDate ;
    @SerializedName("strApplicantMobile")
    private String strApplicantMobile ;
    @SerializedName("strApplicantFamily")
    private String strApplicantFamily ;
    @SerializedName("strApplicantName")
    private String strApplicantName ;
    @SerializedName("tiTripImportance")
    private short tiTripImportance ;
    @SerializedName("iTripSADDestination")
    private int iTripSADDestination = -1;
    @SerializedName("iTripSADSource")
    private int iTripSADSource = -1;
    @SerializedName("bExclusive")
    private Boolean bExclusive ;
    @SerializedName("bHaveReturn")
    private Boolean bHaveReturn ;
    @SerializedName("bMissionary")
    private Boolean bMissionary ;
    @SerializedName("iPassenger")
    private int iPassenger;
    @SerializedName("strTripDate")
    private String strTripDate ;
    @SerializedName("strTripTime")
    private String strTripTime ;
    @SerializedName("MobileType")
    private int MobileType ;
    @SerializedName("strComment")
     private String strComment;


    public Boolean getbMissionary() {
        return bMissionary;
    }

    public void setbMissionary(Boolean bMissionary) {
        this.bMissionary = bMissionary;
    }

    public String getStrComment() {
        return strComment;
    }

    public void setStrComment(String strComment) {
        this.strComment = strComment;
    }

    public int getMobileType() {
        return MobileType;
    }

    public void setMobileType(int mobileType) {
        MobileType = mobileType;
    }

    public short getTiTripReason() {
        return tiTripReason;
    }

    public void setTiTripReason(short tiTripReason) {
        this.tiTripReason = tiTripReason;
    }

    public String getStrPassengersName() {
        return strPassengersName;
    }

    public void setStrPassengersName(String strPassengersName) {
        this.strPassengersName = strPassengersName;
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

    public String getStrApplicantMobile() {
        return strApplicantMobile;
    }

    public void setStrApplicantMobile(String strApplicantMobile) {
        this.strApplicantMobile = strApplicantMobile;
    }

    public String getStrApplicantFamily() {
        return strApplicantFamily;
    }

    public void setStrApplicantFamily(String strApplicantFamily) {
        this.strApplicantFamily = strApplicantFamily;
    }

    public String getStrApplicantName() {
        return strApplicantName;
    }

    public void setStrApplicantName(String strApplicantName) {
        this.strApplicantName = strApplicantName;
    }

    public short getTiTripImportance() {
        return tiTripImportance;
    }

    public void setTiTripImportance(short tiTripImportance) {
        this.tiTripImportance = tiTripImportance;
    }

    public int getiTripSADDestination() {
        return iTripSADDestination;
    }

    public void setiTripSADDestination(int iTripSADDestination) {
        this.iTripSADDestination = iTripSADDestination;
    }

    public int getiTripSADSource() {
        return iTripSADSource;
    }

    public void setiTripSADSource(int iTripSADSource) {
        this.iTripSADSource = iTripSADSource;
    }

    public Boolean getbExclusive() {
        return bExclusive;
    }

    public void setbExclusive(Boolean bExclusive) {
        this.bExclusive = bExclusive;
    }

    public Boolean getbHaveReturn() {
        return bHaveReturn;
    }

    public void setbHaveReturn(Boolean bHaveReturn) {
        this.bHaveReturn = bHaveReturn;
    }

    public int getiPassenger() {
        return iPassenger;
    }

    public void setiPassenger(int iPassenger) {
        this.iPassenger = iPassenger;
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
}
