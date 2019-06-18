package com.rayanandisheh.peysepar.passenger.fragment.dashboard_trips;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.MyValueFormatter;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardTripsFragment extends Fragment implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    BarChart chart;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;
    BarDataSet Bardataset;
    BarData BARDATA;

    SwipeRefreshLayout swp_refresh;

    TextView txtTotalRunningCarCount,txtTotalReadyCarsCount,txtTotalKMDashboard,txtTotalTripTimeDashboard;

    public DashboardTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard_trips, container, false);

        context = getContext();
        presenter.attachView(context, this);

        bindView(view);
        presenter.viewLoaded();
        return view;
    }


    private void bindView(View view) {
        chart=view.findViewById(R.id.chartDashboardTripFragment);
        swp_refresh=view.findViewById(R.id.swp_refreshDashboardTripFragment);
    }

    public void AddValuesToBarEntryLabels() {
        BarEntryLabels.add("ثبت شده");
        BarEntryLabels.add("تایید");
        BarEntryLabels.add("اختصاص راننده");
        BarEntryLabels.add("لغو شده");
        BarEntryLabels.add("پایان یافته");
    }


    public void AddValuesToBARENTRY() {
        BARENTRY.add(new BarEntry(App.dashboard.getCountInsertTrip() , 0));
        BARENTRY.add(new BarEntry(App.dashboard.getCountConfirmTrip(), 1));
        BARENTRY.add(new BarEntry(App.dashboard.getCountAssignDriverTrip(), 2));
        BARENTRY.add(new BarEntry(App.dashboard.getCountCanceledTrip(), 3));
        BARENTRY.add(new BarEntry(App.dashboard.getCountEndedTrip(), 4));
    }


    @Override
    public void showSwpRefresh() {
        swp_refresh.setRefreshing(true);
    }

    @Override
    public void hideSwipRefresh() {
        swp_refresh.setRefreshing(false);
    }

    @Override
    public void ResultOK() {
//        chart = findViewById(R.id.chart);
        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();
        AddValuesToBARENTRY();
        AddValuesToBarEntryLabels();
        Bardataset = new BarDataSet(BARENTRY, "Projects");
        BARDATA = new BarData(BarEntryLabels, Bardataset);
        int[] colors = new int[]{
                Color.argb(255, 79, 129, 189) //blue
                , Color.argb(255, 155, 187, 89)   //green
                , Color.argb(255, 128, 100, 162)   // violet
                , Color.argb(255, 192, 80, 77)     //red
                , Color.argb(255, 66, 152, 175)   //blue_green mixed
                , Color.argb(255, 221, 157, 157)    //pink
                , Color.argb(255, 219, 132, 61)    //orange
                , Color.argb(255, 66, 152, 175)};     //blue_green mixed
        Bardataset.setColors(colors);

        chart.setData(BARDATA);
        chart.animateY(500);
        chart.setDescription("");
        chart.getLegend().setEnabled(false);
        chart.setDoubleTapToZoomEnabled(false);

//        chart.setDrawBorders(false);


        XAxis xAxis=chart.getXAxis();
        xAxis.setLabelsToSkip(0);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "IRANSansMobile.ttf");
        xAxis.setTypeface(font);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.argb(255, 0, 51 ,102));
        xAxis.setEnabled(true);


        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setDrawLabels(false);
        yAxisRight.setEnabled(false);


        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        BARDATA.setValueFormatter(new MyValueFormatter());
    }


}
