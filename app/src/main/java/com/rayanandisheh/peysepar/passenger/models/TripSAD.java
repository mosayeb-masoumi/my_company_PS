package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class TripSAD {

    @SerializedName("strName")
    public String  strName;
    @SerializedName("iTripSad")
    public int iTripSad ;

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public int getiTripSad() {
        return iTripSad;
    }

    public void setiTripSad(int iTripSad) {
        this.iTripSad = iTripSad;
    }
}
