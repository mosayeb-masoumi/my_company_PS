package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class TripReason {
    @SerializedName("strComment")
    public String strComment ;
    @SerializedName("tiTripReason")
    public int tiTripReason ;

    public String getStrComment() {
        return strComment;
    }

    public void setStrComment(String strComment) {
        this.strComment = strComment;
    }

    public int getTiTripReason() {
        return tiTripReason;
    }

    public void setTiTripReason(int tiTripReason) {
        this.tiTripReason = tiTripReason;
    }
}
