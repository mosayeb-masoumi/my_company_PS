package com.rayanandisheh.peysepar.passenger.activities.trip_management_new;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.trip_management_new.adapter.*;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class TripManagementNewActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    Spinner spn_tripManagementNew;
    ImageView img_arrowBack,img_noItem;
    ProgressBar pb;
    SwipeRefreshLayout swpRefresh;
    RecyclerView recyclerView;


    TripManagementNewAdapter adapterNewTrip;
    TripManagementConfirmedAdapter adapterConfirmed;
    TripManagementDriverConfirmedAdapter adapterDriverConfirmed;
    TripManagementRunningAdapter adapterRunning;
    TripManagementCancelAdapter adapterCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_management_new);

        context = this;
        presenter.attachView(context, this);

        bindView();





//        edtDateFrom.setText(Time.getNowPersianDate());
//        edtDateTo.setText(Time.getNowPersianDate());
//
//        edtDateFrom.setOnClickListener(v -> presenter.edtDatePressed(true));
//        edtDateTo.setOnClickListener(v -> presenter.edtDatePressed(false));


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.trip_management,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_tripManagementNew.setAdapter(adapter);


        spn_tripManagementNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                presenter.spnSelected(parent,view,position,id );


//                String text=parent.getItemAtPosition(position).toString();
//                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        presenter.viewLoded(spn_tripManagementNew.getSelectedItemPosition());




        img_arrowBack.setOnClickListener(v -> presenter.imgArrowBackPressed());


//        rl_search.setOnClickListener(v -> presenter.rl_searchPressed(spn_tripManagementNew.getSelectedItemPosition(),
//                edtDateFrom.getText().toString(),edtDateTo.getText().toString()));

        swpRefresh.setOnRefreshListener(() -> presenter.swpRefreshPressed(spn_tripManagementNew.getSelectedItemPosition()));


    }

    private void bindView() {
        spn_tripManagementNew=findViewById(R.id.spn_tripManagementNew);
        img_arrowBack=findViewById(R.id.img_arrowbackTripManagementNew);
        img_noItem=findViewById(R.id.img_noItemTripManagementNew);
        pb=findViewById(R.id.pbTripManagementNew);
        recyclerView=findViewById(R.id.rv_tripManagementNew);
        swpRefresh=findViewById(R.id.swp_tripManagegmentNew);
    }


    @Override
    public void ShowSwpRefresh() {
        swpRefresh.setRefreshing(true);
    }
    @Override
    public void HideSwpRefresh() {
        swpRefresh.setRefreshing(false);
    }

    @Override
    public void showImg_noItem() {
        img_noItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImg_noItem() {
        img_noItem.setVisibility(View.GONE);
    }




    @Override
    public void setAdapter(int position) {

        if(position==0){
            adapterNewTrip=new TripManagementNewAdapter(  App.listNewTripManagementNew,context,(Presenter)presenter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapterNewTrip);
            recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        }else if(position==1){
            adapterConfirmed=new TripManagementConfirmedAdapter(App.listConfirmedTripManagementNew,context,(Presenter) presenter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapterConfirmed);
            recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        }else if(position==2){
            adapterDriverConfirmed=new TripManagementDriverConfirmedAdapter(App.listWaitingDriverConfirmTripManagementNew,context,(Presenter)presenter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapterDriverConfirmed);
            recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        }else if(position==3){
            adapterRunning=new TripManagementRunningAdapter(App.listRunningTripManagementNew,context,(Presenter)presenter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapterRunning);
            recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        }else if(position==4){
            adapterCancel=new TripManagementCancelAdapter(App.listCanceledTripManagementNew ,context,(Presenter)presenter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapterCancel);
            recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
        }
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
    protected void onResume() {
        super.onResume();
        presenter.updateList(spn_tripManagementNew.getSelectedItemPosition());
    }
}
