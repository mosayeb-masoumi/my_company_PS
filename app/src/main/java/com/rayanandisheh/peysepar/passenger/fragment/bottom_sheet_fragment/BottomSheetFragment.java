package com.rayanandisheh.peysepar.passenger.fragment.bottom_sheet_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Time;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    TextView txtRequestTodayTrip,txtTotalTimeTodayTrip,txtKMTodayTrip ,currentDateFragment;
//
//    float km;
    public BottomSheetFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        context = getContext();
        presenter.attachView(context, this);

        bindView(view);


        presenter.viewLoaded();

        currentDateFragment.setText(Time.getNowPersianDate());


//        if(App.report.getTime() !=0)
//         txtTotalTimeTodayTrip.setText(Time.minutesToHours((int) App.report.getTime()) );
//         else{
//            txtTotalTimeTodayTrip.setText("0"+" " + "دقیقه");
//        }



//         txtKMTodayTrip.setText(String.valueOf(App.report.getFkm()));
//         txtRequestTodayTrip.setText(String.valueOf(App.report.getTripCount()));

        return view;
    }

    private void bindView(View view) {
        txtTotalTimeTodayTrip=view.findViewById(R.id.txtTotalTimeTodayTrip);
        txtRequestTodayTrip=view.findViewById(R.id.txtRequestTodayTrip);
        txtKMTodayTrip=view.findViewById(R.id.txtKMTodayTrip);
        currentDateFragment=view.findViewById(R.id.currentDateFragment);
    }

    @Override
    public void setText() {
        if(App.report.getTime() !=0)
            txtTotalTimeTodayTrip.setText(Time.minutesToHours((int) App.report.getTime()) );
        else
            txtTotalTimeTodayTrip.setText("0"+" " + "دقیقه");



        //        txtKMTodayTrip.setText(String.valueOf(Float.parseFloat(FloatLimitation.formatFloatLimitation(App.report.getFkm()))));

//          km =App.report.getFkm();
//          if(km<1000){
//              String km1=String.valueOf(App.report.getFkm()) + "متر";
//          }


//
        if(App.report.getFkm() !=0){

            txtKMTodayTrip.setText(String.format("%.2f", App.report.getFkm())+" "+ "کیلومتر");

//            if(App.report.getFkm()<1000 && App.report.getFkm()>0)
//            txtKMTodayTrip.setText(String.format("%.2f", App.report.getFkm())+" "+ "کیلومتر");
//            else if(App.report.getFkm()>1000){
//                txtKMTodayTrip.setText(String.format("%.2f", App.report.getFkm()/1000)+" "+ " کیلومتر");
//            }
        }

//        if(App.report.getFkm() !=0)
//                txtKMTodayTrip.setText(String.format("%.1f", App.report.getFkm()));
        else
            txtKMTodayTrip.setText("0"+" " + "کیلومتر");
        txtRequestTodayTrip.setText(String.valueOf(App.report.getTripCount()));
    }


    @Override
    public void onResume() {
        super.onResume();

        if(App.report.getTime() !=0)
            txtTotalTimeTodayTrip.setText(Time.minutesToHours((int) App.report.getTime()) );
        else
            txtTotalTimeTodayTrip.setText("0"+" " + "دقیقه");



        if(App.report.getFkm() !=0){
            txtKMTodayTrip.setText(String.format("%.2f", App.report.getFkm())+" "+ "کیلومتر");
//            if(App.report.getFkm()<1000 && App.report.getFkm()>0)
//                txtKMTodayTrip.setText(String.format("%.0f", App.report.getFkm())+" "+ "متر");
//            else if(App.report.getFkm()>1000){
//                txtKMTodayTrip.setText(String.format("%.1f", App.report.getFkm()/1000)+" "+ " کیلومتر");
//            }
        }
        else
            txtKMTodayTrip.setText("0"+" "+ "کیلومتر");





            txtRequestTodayTrip.setText(String.valueOf(App.report.getTripCount()));
    }
}
