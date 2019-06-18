package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NotificationModel {
//
//    @SerializedName("strDriverName")
//    private String strDriverName;
//    @SerializedName("driverImage")
//    private String driverImage;
//    @SerializedName("OriginAddress")
//    private String OriginAddress;
//    @SerializedName("DestinationAddress")
//    private String DestinationAddress;
//    @SerializedName("title")
//    private String title;
//    @SerializedName("Type")
//    private String Type;
//    @SerializedName("message")
//    private String message;
//    @SerializedName("iOfficialTrip")
//    private int iOfficialTrip;
//
//    public String getStrDriverName() {
//        return strDriverName;
//    }
//
//    public void setStrDriverName(String strDriverName) {
//        this.strDriverName = strDriverName;
//    }
//
//    public String getDriverImage() {
//        return driverImage;
//    }
//
//    public void setDriverImage(String driverImage) {
//        this.driverImage = driverImage;
//    }
//
//    public String getOriginAddress() {
//        return OriginAddress;
//    }
//
//    public void setOriginAddress(String originAddress) {
//        OriginAddress = originAddress;
//    }
//
//    public String getDestinationAddress() {
//        return DestinationAddress;
//    }
//
//    public void setDestinationAddress(String destinationAddress) {
//        DestinationAddress = destinationAddress;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getType() {
//        return Type;
//    }
//
//    public void setType(String type) {
//        Type = type;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public int getiOfficialTrip() {
//        return iOfficialTrip;
//    }
//
//    public void setiOfficialTrip(int iOfficialTrip) {
//        this.iOfficialTrip = iOfficialTrip;
//    }

    /*--------------new below---------------*/

    @SerializedName("State")
    private String State;
    @SerializedName("price")
    private String price;
    @SerializedName("DestinationLat")
    private String DestinationLat;
    @SerializedName("DestinationLon")
    private String DestinationLon;
    @SerializedName("DestinationName")
    private String DestinationName;
    @SerializedName("OriginName")
    private String OriginName;
    @SerializedName("OriginLat")
    private String OriginLat;
    @SerializedName("OriginLon")
    private String OriginLon;
    @SerializedName("OriginAddress")
    private String OriginAddress;
    @SerializedName("DestinationAddress")
    private String DestinationAddress;
    @SerializedName("Name")
    private String Name;
    @SerializedName("FName")
    private String FName;
    @SerializedName("strDriverName")
    private String strDriverName;
    @SerializedName("driverImage")
    private String driverImage;
    @SerializedName("iOfficialTrip")
    private int iOfficialTrip;
    @SerializedName("title")
    private String title;
    @SerializedName("Type")
    private String Type;
    @SerializedName("message")
    private String message;
    @SerializedName("bExclusive")
    private boolean bExclusive;
    @SerializedName("bMission")
    private boolean bMission;
    @SerializedName("bHaveReturn")
    private boolean bHaveReturn;
    @SerializedName("important")
    private String important;
    @SerializedName("bNeedConfirmByDriver")
    private boolean bNeedConfirmByDriver;
    @SerializedName("iOfficialTripList")
    private List<String> iOfficialTripLists = new ArrayList<>();





    public List<String> getiOfficialTripLists() {
        return iOfficialTripLists;
    }

    public void setiOfficialTripLists(List<String> iOfficialTripLists) {
        this.iOfficialTripLists = iOfficialTripLists;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDestinationLat() {
        return DestinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        DestinationLat = destinationLat;
    }

    public String getDestinationLon() {
        return DestinationLon;
    }

    public void setDestinationLon(String destinationLon) {
        DestinationLon = destinationLon;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void setDestinationName(String destinationName) {
        DestinationName = destinationName;
    }

    public String getOriginName() {
        return OriginName;
    }

    public void setOriginName(String originName) {
        OriginName = originName;
    }

    public String getOriginLat() {
        return OriginLat;
    }

    public void setOriginLat(String originLat) {
        OriginLat = originLat;
    }

    public String getOriginLon() {
        return OriginLon;
    }

    public void setOriginLon(String originLon) {
        OriginLon = originLon;
    }

    public String getOriginAddress() {
        return OriginAddress;
    }

    public void setOriginAddress(String originAddress) {
        OriginAddress = originAddress;
    }

    public String getDestinationAddress() {
        return DestinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        DestinationAddress = destinationAddress;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getStrDriverName() {
        return strDriverName;
    }

    public void setStrDriverName(String strDriverName) {
        this.strDriverName = strDriverName;
    }

    public String getDriverImage() {
        return driverImage;
    }

    public void setDriverImage(String driverImage) {
        this.driverImage = driverImage;
    }

    public int getiOfficialTrip() {
        return iOfficialTrip;
    }

    public void setiOfficialTrip(int iOfficialTrip) {
        this.iOfficialTrip = iOfficialTrip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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

    public boolean isbMission() {
        return bMission;
    }

    public void setbMission(boolean bMission) {
        this.bMission = bMission;
    }

    public boolean isbHaveReturn() {
        return bHaveReturn;
    }

    public void setbHaveReturn(boolean bHaveReturn) {
        this.bHaveReturn = bHaveReturn;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public boolean isbNeedConfirmByDriver() {
        return bNeedConfirmByDriver;
    }

    public void setbNeedConfirmByDriver(boolean bNeedConfirmByDriver) {
        this.bNeedConfirmByDriver = bNeedConfirmByDriver;
    }


}
