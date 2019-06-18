package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class TripImportance {
    @SerializedName("strComment")
    private String strComment ;
    @SerializedName("tiTripImportance")
    private int tiTripImportance ;

    public String getStrComment() {
        return strComment;
    }

    public void setStrComment(String strComment) {
        this.strComment = strComment;
    }

    public int getTiTripImportance() {
        return tiTripImportance;
    }

    public void setTiTripImportance(int tiTripImportance) {
        this.tiTripImportance = tiTripImportance;
    }
}
