package com.rayanandisheh.peysepar.passenger.models;


import com.google.gson.annotations.SerializedName;

public class Locations {
    @SerializedName("id")
    private int id = -1;
    @SerializedName("strIMEI")
    private String strIMEI = "";
    @SerializedName("Lat")
    private Double Lat = 0d;
    @SerializedName("Lon")
    private Double Lon = 0d;
    @SerializedName("viSpeed")
    private String viSpeed = "";
    @SerializedName("steps")
    private String steps = "";
    @SerializedName("Accuracy")
    private String Accuracy = "";
    @SerializedName("LogDate")
    private String LogDate = "";
    @SerializedName("LogTime")
    private String LogTime = "";
    @SerializedName("batteryLevel")
    private String batteryLevel = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrIMEI() {
        return strIMEI;
    }

    public void setStrIMEI(String strIMEI) {
        this.strIMEI = strIMEI;
    }

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getLon() {
        return Lon;
    }

    public void setLon(Double lon) {
        Lon = lon;
    }

    public String getViSpeed() {
        return viSpeed;
    }

    public void setViSpeed(String viSpeed) {
        this.viSpeed = viSpeed;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(String accuracy) {
        Accuracy = accuracy;
    }

    public String getLogDate() {
        return LogDate;
    }

    public void setLogDate(String logDate) {
        LogDate = logDate;
    }

    public String getLogTime() {
        return LogTime;
    }

    public void setLogTime(String logTime) {
        LogTime = logTime;
    }

    public String getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(String batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}