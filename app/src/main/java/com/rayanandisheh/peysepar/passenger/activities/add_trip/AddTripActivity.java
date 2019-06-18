package com.rayanandisheh.peysepar.passenger.activities.add_trip;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.Time;

import java.util.Calendar;
import java.util.List;

public class AddTripActivity extends PersianAppcompatActivity implements Contract.View, RecyclerViewAdapter.OnItemClickListener {
    Contract.Presenter presenter = new Presenter();
    Context context;
    TextView txtTimePicker;
    Spinner spnReason, spnImportance, spnCarType;
    Button btnRegisterTrip;
    ProgressBar pbRegisterTrip;
    Toolbar toolbar;
    RelativeLayout rlACtextDes;
    LinearLayout rlTextDes;
    AutoCompleteTextView autoCompleteTxtOrigin, autoCompleteTxtDes;
    EditText edtDate, edtEnterNamePassenges, edtCommentAddTrip;
    ImageView imgAddPassengers;
    RecyclerView rvAddPassenges;
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter myRecyclerViewAdapter;
    //    @BindView(R.id.timePickerAddTrip)
//    TimePicker picker;
    @BindView(R.id.chkBxAvailable)
    CheckBox chkBoxInService;
    @BindView(R.id.ivWarningOrigin)
    ImageView ivWarningOrigin;
    @BindView(R.id.ivWarningDes)
    ImageView ivWarningDes;
    @BindView(R.id.ivWarningTripReason)
    ImageView ivWarningTripReason;
    @BindView(R.id.ivWarningTripImportance)
    ImageView ivWarningTripImportance;
    @BindView(R.id.chkBxReturn)
    CheckBox chkBxReturn;
    @BindView(R.id.chkBxMissionary)
    CheckBox chkBxMissionary;
    NumberPicker npHour, npMin;
    Calendar calander;
    String numberPickerTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        ButterKnife.bind(this);

        context = this;
        presenter.attachView(context, this);
//
//        picker.setIs24HourView(true);
        bindView();

        presenter.viewLoaded();
        initializeRecyclerViewAddPassengers();


        chkBxReturn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            chkBoxInService.setChecked(false);
            chkBxMissionary.setChecked(false);
        });


        chkBoxInService.setOnCheckedChangeListener((v, isChecked) -> {
            chkBxReturn.setChecked(false);
            chkBxMissionary.setChecked(false);
            presenter.chkInServiceChecked(isChecked);
        });


        chkBxMissionary.setOnCheckedChangeListener((buttonView, isChecked) -> {
            chkBxReturn.setChecked(false);
            chkBoxInService.setChecked(false);
        });








        edtDate.setOnClickListener(v -> presenter.cvCalandarPressed(edtDate));
        btnRegisterTrip.setOnClickListener(v -> getSelectedTime());

        presenter.spinnerTripReasonPressed(spnReason);
        presenter.spinnerTripImportancePressed(spnImportance);


        autoCompleteTxtDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0)
                    ivWarningDes.setVisibility(View.VISIBLE);
                else
                    ivWarningDes.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        autoCompleteTxtDes.setOnTouchListener((v, e) -> {
//            autoCompleteTxtDes.showDropDown();
//            return false;
//        });
//
//        autoCompleteTxtOrigin.setOnTouchListener((v, e) -> {
//            autoCompleteTxtDes.showDropDown();
//            return false;
//        });


        autoCompleteTxtOrigin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0)
                    ivWarningOrigin.setVisibility(View.VISIBLE);
                else
                    ivWarningOrigin.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private void getSelectedTime() {

        if (!autoCompleteTxtOrigin.getText().toString().isEmpty()
                && (!autoCompleteTxtDes.getText().toString().isEmpty() || chkBoxInService.isChecked())) {

            numberPickerTime = (String.format("%s:%s", String.valueOf(npHour.getValue()).length() < 2 ? "0" + npHour.getValue() : npHour.getValue(),
                    String.valueOf(npMin.getValue()).length() < 2 ? "0" + npMin.getValue() : npMin.getValue()));
//            txtTimePicker.setText(numberPickerTime);

            presenter.btnPressed(
                    spnReason.getSelectedItemPosition(), spnImportance.getSelectedItemPosition(),
                    spnCarType.getSelectedItemPosition(),
                    autoCompleteTxtOrigin.getText().toString(), autoCompleteTxtDes.getText().toString(),
                    edtDate.getText().toString(), numberPickerTime, chkBoxInService.isChecked(),
                    chkBxReturn.isChecked(),chkBxMissionary.isChecked(), myRecyclerViewAdapter.getList(), edtCommentAddTrip.getText().toString());

        } else
            Toast.makeText(context, "آدرس می بایست مشخص گردد", Toast.LENGTH_SHORT).show();
    }


    private void bindView() {
        toolbar = findViewById(R.id.toolbar_add_trip);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setTitle("افزودن سفر");

//        txtApplicantName = findViewById(R.id.txtapplicantNameRegisterTrip);
        edtDate = findViewById(R.id.edtDateRegisterTrip);
        edtDate.setText(Time.getNowPersianDate());

//        txtMobile = findViewById(R.id.txtMobileRegisterTrip);
        spnReason = findViewById(R.id.spinnerReason);
        spnImportance = findViewById(R.id.spinnerImportance);
        pbRegisterTrip = findViewById(R.id.pbRegisterTrip);
        btnRegisterTrip = findViewById(R.id.btnRegisterTrip);

        rlTextDes = findViewById(R.id.rlTextDes);
        rlACtextDes = findViewById(R.id.rlACtextDes);

        autoCompleteTxtOrigin = findViewById(R.id.autoCompleteTxtOrigin);
        autoCompleteTxtDes = findViewById(R.id.autoCompleteTxtDes);

        edtEnterNamePassenges = findViewById(R.id.edtEnterNamePassenges);
        imgAddPassengers = findViewById(R.id.imgAdd);
        rvAddPassenges = findViewById(R.id.recyclerViewAddPassengers);


        npHour = findViewById(R.id.npHour);
        npMin = findViewById(R.id.npMin);
        calander = Calendar.getInstance();
        npHour.setMinValue(0);
        npHour.setMaxValue(23);
        npHour.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });
        npHour.setValue(calander.get(Calendar.HOUR_OF_DAY));


        npMin.setMinValue(0);
        npMin.setMaxValue(59);
        npMin.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });
        npMin.setValue(calander.get(Calendar.MINUTE));


        spnCarType = findViewById(R.id.spinnerCarType);

        edtCommentAddTrip = findViewById(R.id.edtCommentAddTrip);
//        txtApplicantName.setText(String.format("%s %s", App.userInfo.getStrName(), App.userInfo.getStrFamily()));
//        txtMobile.setText(Cache.getString("mobileNumber"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setSpnReasonData(List<String> spnReasonData) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spnReasonData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnReason.setAdapter(dataAdapter);
    }

    @Override
    public void setSpnImportanceData(List<String> importanceData) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, importanceData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnImportance.setAdapter(dataAdapter);
    }


    @Override
    public void setSpnCarType(List<String> spnTypeCar) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spnTypeCar);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCarType.setAdapter(dataAdapter);
    }


    @Override
    public void showIvWarningOrigin() {
        ivWarningOrigin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIvWarningOrigin() {
        ivWarningOrigin.setVisibility(View.GONE);
    }

    @Override
    public void showIvWarningDes() {
        ivWarningDes.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIvWarningDes() {
        ivWarningDes.setVisibility(View.GONE);
    }

    @Override
    public void showIvWarningReason() {
        ivWarningTripReason.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIvWarningReason() {
        ivWarningTripReason.setVisibility(View.GONE);
    }

    @Override
    public void showIvWarningImportance() {
        ivWarningTripImportance.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIvWarningImportance() {
        ivWarningTripImportance.setVisibility(View.GONE);
    }

    @Override
    public void getOriginDes(ArrayAdapter<String> adapter) {
        autoCompleteTxtOrigin.setThreshold(1);
        autoCompleteTxtOrigin.setAdapter(adapter);

        autoCompleteTxtDes.setThreshold(1);
        autoCompleteTxtDes.setAdapter(adapter);
    }

    @Override
    public void setOriginDes(List<String> originDes) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.select_dialog_item_custom, originDes);
        autoCompleteTxtOrigin.setThreshold(1);
        autoCompleteTxtOrigin.setAdapter(adapter);
        autoCompleteTxtDes.setThreshold(1);
        autoCompleteTxtDes.setAdapter(adapter);
    }

    @Override
    public void HideDestination() {

        rlACtextDes.setVisibility(View.GONE);
        rlTextDes.setVisibility(View.GONE);
    }

    @Override
    public void showDestination() {
        rlACtextDes.setVisibility(View.VISIBLE);
        rlTextDes.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        pbRegisterTrip.setVisibility(View.VISIBLE);
        btnRegisterTrip.setVisibility(View.GONE);
    }

    @Override
    public void hidePreogressBar() {
        pbRegisterTrip.setVisibility(View.GONE);
        btnRegisterTrip.setVisibility(View.VISIBLE);
    }


    private void initializeRecyclerViewAddPassengers() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRecyclerViewAdapter = new RecyclerViewAdapter(this);
        myRecyclerViewAdapter.setOnItemClickListener(this);
        rvAddPassenges.setAdapter(myRecyclerViewAdapter);
        rvAddPassenges.setLayoutManager(linearLayoutManager);

        imgAddPassengers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newName = edtEnterNamePassenges.getText().toString();
                if (!newName.equals("")) {
                    if (myRecyclerViewAdapter.getItemCount() > 1) {
                        myRecyclerViewAdapter.add(0, newName);
                        edtEnterNamePassenges.setText("");
                    } else {
                        myRecyclerViewAdapter.add(0, newName);
                        edtEnterNamePassenges.setText("");
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(RecyclerViewAdapter.ItemHolder item, int position) {
//        Toast.makeText(this, "Remove " + position + " : " + item.getItemName(), Toast.LENGTH_SHORT).show();
        myRecyclerViewAdapter.remove(position);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

