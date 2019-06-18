package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class Score {
    @SerializedName("iofficialtrip")
    private int iofficialtrip ;
    @SerializedName("fscore")
    private float fscore ;
    @SerializedName("strDate")
    private String strDate ;
    @SerializedName("iMobileDomain")
    private int iMobileDomain ;

    public int getiMobileDomain() {
        return iMobileDomain;
    }

    public void setiMobileDomain(int iMobileDomain) {
        this.iMobileDomain = iMobileDomain;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public int getIofficialtrip() {
        return iofficialtrip;
    }

    public void setIofficialtrip(int iofficialtrip) {
        this.iofficialtrip = iofficialtrip;
    }

    public float getFscore() {
        return fscore;
    }

    public void setFscore(float fscore) {
        this.fscore = fscore;
    }
}
