package com.rayanandisheh.peysepar.passenger.activities.trip_management;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.test.TestActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.Time;

public class TripManagementActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

//    Toolbar toolbar;

    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabNewFragment,tabConfirmedFragment,
            tabDriverConfirmationFragment,tabRunningFragment,tabCanceledFragment,tabDashboardFragment;

    EditText edtDateFrom_tripManagement,edtDateTo_tripManagement;
    ImageView imgBack,arrowBackTripManagementDashboard;

    RelativeLayout rlShowDateRangeDashboard,rlHideDateRangeDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_management);

        context = this;
        presenter.attachView(context, this);

            bindView();


//            if(App.dateRengeTripManagement){
//                rlShowDateRangeDashboard.setVisibility(View.GONE);
//                rlHideDateRangeDashboard.setVisibility(View.VISIBLE);
//            }else if(!App.dateRengeTripManagement){
//                rlShowDateRangeDashboard.setVisibility(View.VISIBLE);
//                rlHideDateRangeDashboard.setVisibility(View.GONE);
//            }

        rlShowDateRangeDashboard.setVisibility(View.VISIBLE);
        rlHideDateRangeDashboard.setVisibility(View.GONE);

        edtDateFrom_tripManagement.setText(Time.getNowPersianDate());
        edtDateTo_tripManagement.setText(Time.getNowPersianDate());


          edtDateFrom_tripManagement.setOnClickListener(v -> presenter.edtDatePressed(true));
          edtDateTo_tripManagement.setOnClickListener(v -> presenter.edtDatePressed(false));


//        //save in app to use chose date in trip management fargments
//        App.dateFromTripManagement=edtDateFrom_tripManagement.getText().toString();
//        App.dateFromTripManagement=edtDateTo_tripManagement.getText().toString();


        imgBack.setOnClickListener(v -> finish());

        arrowBackTripManagementDashboard.setOnClickListener(v -> finish());


    }

    private void bindView() {

//        toolbar = findViewById(R.id.toolbar_management);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//        setTitle("مدیریت سفر");

        tabLayout = findViewById(R.id.tablayout);
        tabNewFragment=findViewById(R.id.tabNewFragment);
        tabConfirmedFragment=findViewById(R.id.tabConfirmedFragment);
        tabDriverConfirmationFragment=findViewById(R.id.tabDriverConfirmationFragment);
        tabRunningFragment=findViewById(R.id.tabRunningFragment);
        tabCanceledFragment=findViewById(R.id.tabCanceledFragment);
        tabDashboardFragment=findViewById(R.id.tabDashboardFragment);

        edtDateFrom_tripManagement=findViewById(R.id.edtDateFrom_tripManagement);
        edtDateTo_tripManagement=findViewById(R.id.edtDateTo_tripManagement);

        imgBack=findViewById(R.id.arrowBackTripManagement);
        arrowBackTripManagementDashboard=findViewById(R.id.arrowBackTripManagementDashboard);

        rlShowDateRangeDashboard=findViewById(R.id.rlShowDateRangeDashboard);
        rlHideDateRangeDashboard=findViewById(R.id.rlHideDateRangeDashboard);


        viewPager = findViewById(R.id.viewPager);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        //to change font of tabs in tablayout
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView)LayoutInflater.from(this).inflate(R.layout.custom_tab_font,null);
            Typeface typeface = Typeface.createFromAsset(getAssets(),"IRANSansMobile.ttf");
            tv.setTypeface(typeface);
            tabLayout.getTabAt(i).setCustomView(tv);
        }





        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
             viewPager.setCurrentItem(tab.getPosition());
             if(tab.getPosition()==5){
                 rlShowDateRangeDashboard.setVisibility(View.GONE);
                 rlHideDateRangeDashboard.setVisibility(View.VISIBLE);
             }else{
                 rlShowDateRangeDashboard.setVisibility(View.VISIBLE);
                 rlHideDateRangeDashboard.setVisibility(View.GONE);
             }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


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
    public void setFromDate(String date) {
        edtDateFrom_tripManagement.setText(date);

        App.dateFromTripManagement=date;

    }

    @Override
    public void setToDate(String date) {
         edtDateTo_tripManagement.setText(date);
         App.dateToTripManagement=date;
    }


}
