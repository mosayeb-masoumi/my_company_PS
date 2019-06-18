package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

public class MobileType {

    @SerializedName("tiMobileType")
    public int tiMobileType ;
    @SerializedName("strComment")
    public String strComment ;

    public int getTiMobileType() {
        return tiMobileType;
    }

    public void setTiMobileType(int tiMobileType) {
        this.tiMobileType = tiMobileType;
    }

    public String getStrComment() {
        return strComment;
    }

    public void setStrComment(String strComment) {
        this.strComment = strComment;
    }
}
