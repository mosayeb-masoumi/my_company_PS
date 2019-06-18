package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class DriverInfo {

    @SerializedName("mobiletype")
    private String mobiletype ;
    @SerializedName("mobileName")
    private String mobileName ;
    @SerializedName("drivername")
    private String drivername ;
    @SerializedName("driverStatus")
    private String driverStatus ;
    @SerializedName("tripcount")
    private int tripcount ;
    @SerializedName("strunitid")
    private String strunitid ;
    @SerializedName("iofficialtrip")
    private  int iofficialtrip;



    public int getIofficialtrip() {
        return iofficialtrip;
    }

    public void setIofficialtrip(int iofficialtrip) {
        this.iofficialtrip = iofficialtrip;
    }

    public String getStrunitid() {
        return strunitid;
    }

    public void setStrunitid(String strunitid) {
        this.strunitid = strunitid;
    }

    public String getMobiletype() {
        return mobiletype;
    }

    public void setMobiletype(String mobiletype) {
        this.mobiletype = mobiletype;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    public int getTripcount() {
        return tripcount;
    }

    public void setTripcount(int tripcount) {
        this.tripcount = tripcount;
    }
}
