package com.rayanandisheh.peysepar.passenger.fragment.trip_new_fragment;


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
import android.widget.Toast;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.history.HistoryAdapter;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripNewFragment extends Fragment implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    ProgressBar progressBarNewTabLayout;
    RecyclerView recyclerView;
    SwipeRefreshLayout swp_newTabLayout;
    TripNewFragmentAdapter adapter;
    ImageView img_noItem;

/////////////////////////////////

//    List<ModelTestNewFragment> modelTestNewFragments=new ArrayList<>();

//////////////////////////////////



    public TripNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trip_new, container, false);

        context = getContext();
        presenter.attachView(context, this);
        bindView(view);


        presenter.viewLoaded();

        swp_newTabLayout.setOnRefreshListener(() -> presenter.swpNewTabLayoutPressed());









        /////////////////////////////////////////////
//        adapter=new TripManagementNewAdapter(modelTestNewFragments,context, (Presenter) presenter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
////        recyclerView.setAdapter(adapter);
//        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
//        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));

//        sendDataToRecyclerNewFragment();

       //////////////////////////////////////////////////

        return view;
    }



    private void bindView(View view) {
        recyclerView=view.findViewById(R.id.rv_newFragment);
        progressBarNewTabLayout=view.findViewById(R.id.progressBarNewTabLayout);
        img_noItem=view.findViewById(R.id.img_noIconNewTabLayout);
        swp_newTabLayout=view.findViewById(R.id.swp_newTabLayout);
    }


    @Override
    public void showSwipeRefresh() {
        swp_newTabLayout.setRefreshing(true);
//        Toast.makeText(context, "new", Toast.LENGTH_SHORT).show();
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
    public void hideSwipeRefresh() {
        swp_newTabLayout.setRefreshing(false);
    }

    @Override
    public void setAdapter() {
//        adapter = new TripManagementNewAdapter(App.listHistoryTrip,context);
        adapter = new TripNewFragmentAdapter(App.listNewTabLayoutTrip,context, (Presenter) presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.swpNewTabLayoutPressed();
    }


    ////////////////////////////////////////////////////////////////
//    private void sendDataToRecyclerNewFragment() {
//
//        if(modelTestNewFragments.size()>0){
//            adapter.clear();
//            addData();
//        }else{
//            addData();
//        }
//    }

//    private void addData(){
//        modelTestNewFragments.add(new ModelTestNewFragment(51,35,0,"1566","مهران","احمدی","اداری",
//                "فوری","1397/11/01","09:45","1397/11/01","10:45",
//                "تهران , میدان انقلاب","تهران , میدان آزادی"));
//        modelTestNewFragments.add(new ModelTestNewFragment(52,31,1,"1211","علی","رضایی","ماموریتی",
//                "عادی","1397/11/02","10:10","1397/11/01","11:12",
//                "تهران , نواب","تهران , کمیل"));
//        modelTestNewFragments.add(new ModelTestNewFragment(51,34,2,"1754","میلاد","نوری","اداری",
//                "فوری","1397/11/03","14:20","1397/11/03","15:16",
//                "تهران , رسالت","تهران , تهرانپارس"));
//        modelTestNewFragments.add(new ModelTestNewFragment(52,33,3,"5642","محمد","عبدی","ماموریتی",
//                "عادی","1397/11/04","12:30","1397/11/04","13:15",
//                "تهران , تجریش","تهران , چهار راه ولیعصر"));
//    }

}
