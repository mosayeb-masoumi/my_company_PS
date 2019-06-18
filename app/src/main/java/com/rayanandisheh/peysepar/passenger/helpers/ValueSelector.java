package com.rayanandisheh.peysepar.passenger.helpers;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.rayanandisheh.peysepar.passenger.R;

public class ValueSelector extends LinearLayout implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

    View rootView;
    EditText valueText;
    View buttonPlus;
    View buttonMinus;

    int minValue = 0;
    int maxValue = Integer.MAX_VALUE;

    private boolean isPlusButtonPressed = false;
    private boolean isMinusButtonPressed = false;
    private static final int TIME_INTERVAL = 100;

    private Handler handler;

    public ValueSelector(Context context) {
        super(context);
        init(context);
    }

    public ValueSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    private void init(Context context) {
        setSaveEnabled(true);
        rootView = inflate(context, R.layout.value_selector, this);
        valueText = rootView.findViewById(R.id.value_credit);
        buttonMinus = rootView.findViewById(R.id.btn_minus);
        buttonPlus = rootView.findViewById(R.id.btn_plus);

        handler = new Handler();

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);

        buttonPlus.setOnLongClickListener(this);
        buttonMinus.setOnLongClickListener(this);
        buttonMinus.setOnTouchListener(this);
        buttonPlus.setOnTouchListener(this);
    }

    public int getValue() {
        String text = valueText.getText().toString();
        if (text.isEmpty()) {
            valueText.setText("0");
            return 0;
        }
        return Integer.valueOf(text);
    }

    public void setValue(int newValue) {
        if (newValue > maxValue) {
            valueText.setText(String.valueOf(maxValue));
        } else if (newValue < minValue) {
            valueText.setText(String.valueOf(minValue));
        } else {
            valueText.setText(String.valueOf(newValue));
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == buttonMinus.getId()) {
            decrementValue();
        } else if (view.getId() == buttonPlus.getId()) {
            incrementValue();
        }
    }

    private void decrementValue() {
        int value = getValue();
        setValue(value - 1000);
    }

    private void incrementValue() {
        int value = getValue();
        setValue(value + 1000);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
//            if(view.getId() == buttonPlus.getId()){
            isPlusButtonPressed = false;
//            } else if(view.getId() == buttonMinus.getId()){
            isMinusButtonPressed = false;
//            }
        }
        return false;
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == buttonMinus.getId()) {
            isMinusButtonPressed = true;
            handler.postDelayed(new AutoDecrementer(), TIME_INTERVAL);
        } else if (view.getId() == buttonPlus.getId()) {
            isPlusButtonPressed = true;
            handler.postDelayed(new AutoIncrementer(), TIME_INTERVAL);
        }
        return false;
    }

    private class AutoDecrementer implements Runnable {

        @Override
        public void run() {
            if (isMinusButtonPressed) {
                decrementValue();
                handler.postDelayed(this, TIME_INTERVAL);
            }
        }
    }

    private class AutoIncrementer implements Runnable {
        @Override
        public void run() {
            if (isPlusButtonPressed) {
                incrementValue();
                handler.postDelayed(this, TIME_INTERVAL);
            }
        }
    }
}