package com.rayanandisheh.peysepar.passenger.activities.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Time;

public class TestActivity extends AppCompatActivity {

    TextView txtFrom , txtTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        txtFrom=findViewById(R.id.txtFrom);
        txtTo=findViewById(R.id.txtTo);



        if(App.dateFromTripManagement.equals("") && App.dateToTripManagement.equals("")){
            txtFrom.setText(Time.getNowPersianDate());
            txtTo.setText(Time.getNowPersianDate());
        }else{
            txtFrom.setText(App.dateFromTripManagement);
            txtTo.setText(App.dateToTripManagement);
        }


    }
}
