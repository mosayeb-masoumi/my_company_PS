package com.rayanandisheh.peysepar.passenger.fragment.trip_running_fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.fragment.trip_draiver_confirmed_fragment.TripDriverConfirmedAdapter;
import com.rayanandisheh.peysepar.passenger.fragment.trip_new_fragment.TripNewFragmentAdapter;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TripRunningFragment extends Fragment implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;


    ProgressBar pb;
    RecyclerView recyclerView;
    SwipeRefreshLayout swp_refresh;
    TripRunningFragmentAdapter adapter;
    ImageView img_noItem;






    public TripRunningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip_running, container, false);

        context = getContext();
        presenter.attachView(context, this);
        bindView(view);



        presenter.viewLoaded();
        swp_refresh.setOnRefreshListener(() -> presenter.swp_refreshPressed());

        return view;
    }



    private void bindView(View view) {

       swp_refresh=view.findViewById(R.id.swp_runningTabLayout);
       recyclerView=view.findViewById(R.id.rv_runningTabLayout);
        pb=view.findViewById(R.id.progressBarRunningTabLayout);
        img_noItem=view.findViewById(R.id.img_noIconRunningTabLayout);

    }


    @Override
    public void hideImg_noItem() {
        img_noItem.setVisibility(View.GONE);
    }

    @Override
    public void showImg_noItem() {
    img_noItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSwipeRefresh() {
     swp_refresh.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefresh() {
        swp_refresh.setRefreshing(false);
    }

    @Override
    public void setAdapter() {
        adapter = new TripRunningFragmentAdapter(App.listRunningTabLayout,context, (Presenter) presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.swp_refreshPressed();
    }


//    //to solve the problem of not refreshing the neighbour tabs
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            presenter.swp_refreshPressed();
//        } else {
//
//        }
//    }
}
