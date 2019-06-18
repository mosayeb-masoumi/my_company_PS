package com.rayanandisheh.peysepar.passenger.activities.history;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;
import butterknife.ButterKnife;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import com.rayanandisheh.peysepar.passenger.helpers.Time;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class HistoryActivity extends PersianAppcompatActivity implements Contract.View {

    RecyclerView recyclerView;
    SwipeRefreshLayout swpHistory;
    HistoryAdapter adapter;
    ImageView imgNoItem ,imgSearch_history;
    EditText edtDateFrom_history,edtDateTo_history;
    ProgressBar progressBar;
//    List<ModelCurrentTrip> modelCurrentTrip =new ArrayList<>();

    RelativeLayout rl_search_history;

    Contract.Presenter presenter = new Presenter();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        context = this;
        presenter.attachView(context, this);
        bindViews();





        edtDateFrom_history.setText(Time.getNowPersianDate());
        edtDateTo_history.setText(Time.getNowPersianDate());



        presenter.viewLoaded(edtDateFrom_history.getText().toString(),
                edtDateTo_history.getText().toString());

        edtDateFrom_history.setOnClickListener(v -> presenter.edtDatePressed(true));
        edtDateTo_history.setOnClickListener(v -> presenter.edtDatePressed(false));

        swpHistory.setOnRefreshListener(() -> presenter.swipRefreshPressed(edtDateFrom_history.getText().toString(),
                edtDateTo_history.getText().toString()));

        rl_search_history.setOnClickListener(v -> presenter.swipRefreshPressed(edtDateFrom_history.getText().toString(),
                edtDateTo_history.getText().toString()));


    }

    private void bindViews() {
        recyclerView = findViewById(R.id.rv_history);
        swpHistory = findViewById(R.id.swpHistory);
        imgNoItem = findViewById(R.id.img_noIconTripHistory);
        imgSearch_history=findViewById(R.id.img_search_history);
        edtDateFrom_history=findViewById(R.id.edtDateFrom_history);
        edtDateTo_history=findViewById(R.id.edtDateTo_history);

        rl_search_history=findViewById(R.id.rl_serach_history);
        progressBar=findViewById(R.id.progressBarHistory);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);


    }



    @Override
    public void showImg_noItem() {
        imgNoItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImg_noItem() {
        imgNoItem.setVisibility(View.GONE);
    }



    @Override
    public void setFromDate(String date) {
        edtDateFrom_history.setText(date);
        Cache.setString("DateFrom",date);
    }


    @Override
    public void setToDate(String date) {
        edtDateTo_history.setText(date);
        Cache.setString("DateTO",date);
    }

    @Override
    public void setAdapter() {

//        adapter.clear();
        adapter = new HistoryAdapter(App.listHistoryTrip,context);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
    }

    @Override
    public void showProgressLoading() {
       swpHistory.setRefreshing(true);
//       progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSwipeRefresh() {
        swpHistory.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefresh() {
        swpHistory.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showImageEmpety() {
        swpHistory.setRefreshing(false);
        imgNoItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressLoading() {
        swpHistory.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }




    @Override
    protected void onResume() {
        super.onResume();
        presenter.swipRefreshPressed(edtDateFrom_history.getText().toString(),
                edtDateTo_history.getText().toString());
    }
}
