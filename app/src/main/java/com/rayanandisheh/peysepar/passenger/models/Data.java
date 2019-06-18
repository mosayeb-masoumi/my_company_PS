package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;


//model related to taaid alert new tablayout
public class Data {

    @SerializedName("Result")
    public int Result ;
    @SerializedName("Message")
    public String Message ;




    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
