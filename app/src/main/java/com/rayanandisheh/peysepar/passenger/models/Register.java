package com.rayanandisheh.peysepar.passenger.models;


import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("strName")
    private String strName;
    @SerializedName("strFamily")
    private String strFamily ;
    @SerializedName("strMobile")
    private String strMobile ;
    @SerializedName("tiSex")
    private int tiSex ;
    @SerializedName("strToken")
    private String strToken ;
    @SerializedName("strActivateCode")
    private String strActivateCode ;
    @SerializedName("Result")
    private int Result ;
    @SerializedName("strIMEI")
    private String strIMEI ;
    @SerializedName("strNationalCode")
    private String strNationalCode ;
    @SerializedName("Lat")
    public double Lat ;
    @SerializedName("Lon")
    public double Lon ;
    @SerializedName("img")
    private String img ;



    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double lon) {
        Lon = lon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStrNationalCode() {
        return strNationalCode;
    }

    public void setStrNationalCode(String strNationalCode) {
        this.strNationalCode = strNationalCode;
    }

    public String getStrIMEI() {
        return strIMEI;
    }

    public void setStrIMEI(String strIMEI) {
        this.strIMEI = strIMEI;
    }

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrFamily() {
        return strFamily;
    }

    public void setStrFamily(String strFamily) {
        this.strFamily = strFamily;
    }

    public String getStrMobile() {
        return strMobile;
    }

    public void setStrMobile(String strMobile) {
        this.strMobile = strMobile;
    }

    public int getTiSex() {
        return tiSex;
    }

    public void setTiSex(int tiSex) {
        this.tiSex = tiSex;
    }

    public String getStrToken() {
        return strToken;
    }

    public void setStrToken(String strToken) {
        this.strToken = strToken;
    }

    public String getStrActivateCode() {
        return strActivateCode;
    }

    public void setStrActivateCode(String strActivateCode) {
        this.strActivateCode = strActivateCode;
    }
}
