package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class EndTrip {

    @SerializedName("iOfficialTrip")
    private int iOfficialTrip ;
    @SerializedName("fScore")
    private float fScore ;
    @SerializedName("Img")
    private String Img ;

    public int getiOfficialTrip() {
        return iOfficialTrip;
    }

    public void setiOfficialTrip(int iOfficialTrip) {
        this.iOfficialTrip = iOfficialTrip;
    }

    public float getfScore() {
        return fScore;
    }

    public void setfScore(float fScore) {
        this.fScore = fScore;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}
