package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class AssignDriver {
    @SerializedName("iofficialtrip")
    public int iofficialtrip ;
    @SerializedName("strunitId")
    public String strunitId ;


    public int getIofficialtrip() {
        return iofficialtrip;
    }

    public void setIofficialtrip(int iofficialtrip) {
        this.iofficialtrip = iofficialtrip;
    }

    public String getStrunitId() {
        return strunitId;
    }

    public void setStrunitId(String strunitId) {
        this.strunitId = strunitId;
    }
}
