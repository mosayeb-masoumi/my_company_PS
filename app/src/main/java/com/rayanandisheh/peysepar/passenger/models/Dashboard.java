package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class Dashboard {
    @SerializedName("CountInsertTrip")
    private int CountInsertTrip;
    @SerializedName("CountConfirmTrip")
    private int CountConfirmTrip;
    @SerializedName("CountEndedTrip")
    private int CountEndedTrip;
    @SerializedName("CountCanceledTrip")
    private int CountCanceledTrip;
    @SerializedName("CountMobileServicing")
    private int CountMobileServicing;
    @SerializedName("CountMobileReady")
    private int CountMobileReady;
    @SerializedName("CountMobileOutOfService")
    private int CountMobileOutOfService;
    @SerializedName("SumfKm")
    private float SumfKm;
    @SerializedName("SumMoveTime")
    private int SumMoveTime;
    @SerializedName("Result")
    private int Result;
    @SerializedName("Message")
    private String Message;
    @SerializedName("CountAssignDriverTrip")
    public int CountAssignDriverTrip;


    public int getCountMobileOutOfService() {
        return CountMobileOutOfService;
    }

    public void setCountMobileOutOfService(int countMobileOutOfService) {
        CountMobileOutOfService = countMobileOutOfService;
    }

    public int getCountAssignDriverTrip() {
        return CountAssignDriverTrip;
    }

    public void setCountAssignDriverTrip(int countAssignDriverTrip) {
        CountAssignDriverTrip = countAssignDriverTrip;
    }

    public int getCountInsertTrip() {
        return CountInsertTrip;
    }

    public void setCountInsertTrip(int countInsertTrip) {
        CountInsertTrip = countInsertTrip;
    }

    public int getCountConfirmTrip() {
        return CountConfirmTrip;
    }

    public void setCountConfirmTrip(int countConfirmTrip) {
        CountConfirmTrip = countConfirmTrip;
    }

    public int getCountEndedTrip() {
        return CountEndedTrip;
    }

    public void setCountEndedTrip(int countEndedTrip) {
        CountEndedTrip = countEndedTrip;
    }

    public int getCountCanceledTrip() {
        return CountCanceledTrip;
    }

    public void setCountCanceledTrip(int countCanceledTrip) {
        CountCanceledTrip = countCanceledTrip;
    }

    public int getCountMobileServicing() {
        return CountMobileServicing;
    }

    public void setCountMobileServicing(int countMobileServicing) {
        CountMobileServicing = countMobileServicing;
    }

    public int getCountMobileReady() {
        return CountMobileReady;
    }

    public void setCountMobileReady(int countMobileReady) {
        CountMobileReady = countMobileReady;
    }

    public float getSumfKm() {
        return SumfKm;
    }

    public void setSumfKm(float sumfKm) {
        SumfKm = sumfKm;
    }

    public int getSumMoveTime() {
        return SumMoveTime;
    }

    public void setSumMoveTime(int sumMoveTime) {
        SumMoveTime = sumMoveTime;
    }

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
