package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class Report {

    @SerializedName("TripCount")
    private int TripCount ;
    @SerializedName("Time")
    private float Time ;
    @SerializedName("fkm")
    private float fkm ;
    @SerializedName("Result")
    private int Result ;
    @SerializedName("strMobile")
    private String strMobile;

    @SerializedName("strMessage")
    private String strMessage;

    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }

    public int getTripCount() {
        return TripCount;
    }

    public void setTripCount(int tripCount) {
        TripCount = tripCount;
    }

    public float getTime() {
        return Time;
    }

    public void setTime(float time) {
        Time = time;
    }

    public float getFkm() {
        return fkm;
    }

    public void setFkm(float fkm) {
        this.fkm = fkm;
    }

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }

    public String getStrMobile() {
        return strMobile;
    }

    public void setStrMobile(String strMobile) {
        this.strMobile = strMobile;
    }
}
