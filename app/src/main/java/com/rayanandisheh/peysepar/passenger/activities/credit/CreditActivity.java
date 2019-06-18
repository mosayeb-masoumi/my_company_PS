package com.rayanandisheh.peysepar.passenger.activities.credit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.ValueSelector;

public class CreditActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    public TextView txt_10000, txt_5000, txt_20000;
    public ValueSelector valueSelector;
    public Button btn_increase_credite;

    ProgressBar progressBar;
    AppCompatEditText edtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        context = this;
        presenter.attachView(context, this);


        bindView();


        txt_5000.setOnClickListener(v -> valueSelector.setValue(5000));
        txt_10000.setOnClickListener(v -> valueSelector.setValue(10000));
        txt_20000.setOnClickListener(v -> valueSelector.setValue(20000));

        btn_increase_credite.setOnClickListener(v -> presenter.btnIncCreditPressed(edtValue));



    }

    private void bindView() {
        valueSelector = findViewById(R.id.valueSelector);
        btn_increase_credite = findViewById(R.id.btn_increase_credite);
        progressBar=findViewById(R.id.pbIncreaseCredit);
        txt_5000 = findViewById(R.id.txt_5000);
        txt_10000 = findViewById(R.id.txt_10000);
        txt_20000 = findViewById(R.id.txt_20000);
        edtValue=findViewById(R.id.value_credit);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
