package com.rayanandisheh.peysepar.passenger.models;

public class User {
    private String State;
    private String price;
    private String DestinationLat;
    private String DestinationLon;
    private String OriginLat;
    private String OriginLon;
    private String OriginAddress;
    private String DestinationAddress;
    private String Name;
    private String FName;
    private String title;
    private String Type;
    private String message;
    private int iOfficialTrip;
    private int AppVersion;
    private String MobileNumber;

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public int getAppVersion() {
        return AppVersion;
    }

    public void setAppVersion(int appVersion) {
        AppVersion = appVersion;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getiOfficialTrip() {
        return iOfficialTrip;
    }

    public void setiOfficialTrip(int iOfficialTrip) {
        this.iOfficialTrip = iOfficialTrip;
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

}