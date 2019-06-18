package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class Replace {

    @SerializedName("strIMEI")
    private String strIMEI ;
    @SerializedName("strMobile1")
    private String strMobile1 ;
    @SerializedName("strMobile2")
    private String strMobile2 ;
    @SerializedName("strActivateCode")
    private String strActivateCode ;
    @SerializedName("strToken")
    private String strToken ;


    public String getStrIMEI() {
        return strIMEI;
    }

    public void setStrIMEI(String strIMEI) {
        this.strIMEI = strIMEI;
    }

    public String getStrMobile1() {
        return strMobile1;
    }

    public void setStrMobile1(String strMobile1) {
        this.strMobile1 = strMobile1;
    }

    public String getStrMobile2() {
        return strMobile2;
    }

    public void setStrMobile2(String strMobile2) {
        this.strMobile2 = strMobile2;
    }

    public String getStrActivateCode() {
        return strActivateCode;
    }

    public void setStrActivateCode(String strActivateCode) {
        this.strActivateCode = strActivateCode;
    }

    public String getStrToken() {
        return strToken;
    }

    public void setStrToken(String strToken) {
        this.strToken = strToken;
    }
}
