package com.rayanandisheh.peysepar.passenger.customes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class mEditText extends AppCompatEditText {

    public mEditText(Context context) {
        super(context);
    }

    public mEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public mEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @NonNull
    @Override
    public String toString() {
        return super.getText() != null ? super.getText().toString() : "";
    }

    public int toInteger() {
        return super.getText() != null ? Integer.parseInt(super.getText().toString()) : 0;
    }



}
