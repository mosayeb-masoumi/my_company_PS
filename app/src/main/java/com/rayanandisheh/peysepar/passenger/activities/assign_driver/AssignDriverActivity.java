package com.rayanandisheh.peysepar.passenger.activities.assign_driver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.fragment.trip_new_fragment.TripNewFragmentAdapter;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

public class AssignDriverActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    RecyclerView recyclerView;
    AssignDriverAdapter adapter;
    SwipeRefreshLayout swp_refresh;
    ImageView noImgItem;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_driver);

        context = this;
        presenter.attachView(context, this);

        bindView();


        Intent intent = getIntent();
        int iOfficialTrip = (intent.getIntExtra("iOfficialTrip",0));




        presenter.viewLoaded(iOfficialTrip);

        swp_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.swpRefreshPressed(iOfficialTrip);
            }
        });


//        adapter = new AssignDriverAdapter(assignDriverListModel, context, (Presenter) presenter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
//        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));

//        getData();


    }

    private void bindView() {
        recyclerView = findViewById(R.id.rv_assignDrivers);
        swp_refresh = findViewById(R.id.swp_assignDrivers);
        noImgItem = findViewById(R.id.img_noIconAssignDrivers);
        toolbar=findViewById(R.id.toolbar_driverList);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setTitle("لیست رانندگان");
    }


//    private void getData() {
//        assignDriverListModel.add(new ModelAssignDriverTest("علی احمدی","سمند","سواری","آماده ارائه سرویس",3));
//        assignDriverListModel.add(new ModelAssignDriverTest("محمد رضایی","پراید","سواری","خارج از سرویس",2));
//        assignDriverListModel.add(new ModelAssignDriverTest("مهدی پوری","پژو","سواری","آماده ارائه سرویس",0));
//        assignDriverListModel.add(new ModelAssignDriverTest("میلار حقی","وانت","باری","خارج از سرویس",1));
//        assignDriverListModel.add(new ModelAssignDriverTest("رضا علیپور","پارس","سواری","آماده ارائه سرویس",2));
//        assignDriverListModel.add(new ModelAssignDriverTest("محمد رضایی","پراید","سواری","خارج از سرویس",2));
//        assignDriverListModel.add(new ModelAssignDriverTest("مهدی پوری","پژو","سواری","آماده ارائه سرویس",0));
//    }

    @Override
    public void showSwipeRefresh() {
        swp_refresh.setRefreshing(true);
    }

    @Override
    public void hideImg_noItem() {
        noImgItem.setVisibility(View.GONE);
    }

    @Override
    public void showImg_noItem() {
        noImgItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSwipeRefresh() {
        swp_refresh.setRefreshing(false);
    }


    @Override
    public void setAdapter() {
        adapter = new AssignDriverAdapter(App.listAssignDriverConfirmedTabLayout,context, (Presenter) presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
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
}
