package com.rayanandisheh.peysepar.passenger.helpers;

/*
* this class just use for convert float or double to integer in dashboardFragment
* to shows the values above each column as integer
* */

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class MyValueFormatter implements ValueFormatter {

    private DecimalFormat mFormat;

    public MyValueFormatter() {
//        mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
        mFormat = new DecimalFormat("###,###,##0"); // jsut show integer not float
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // write your logic here
//        return mFormat.format(value) + " $"; // e.g. append a dollar-sign
        return mFormat.format(value) ; // e.g. append a dollar-sign
    }
}

