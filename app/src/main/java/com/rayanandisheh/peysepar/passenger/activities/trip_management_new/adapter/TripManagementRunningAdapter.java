package com.rayanandisheh.peysepar.passenger.activities.trip_management_new.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.map_choose_origin_tablayout.MapsActivityTabLayout;

import com.rayanandisheh.peysepar.passenger.activities.trip_management_new.Presenter;
import com.rayanandisheh.peysepar.passenger.models.Trip;

import java.util.List;

public class TripManagementRunningAdapter extends RecyclerView.Adapter<TripManagementRunningAdapter.ViewHolder> {

    private List<Trip> modelRunningFragments;
    Context context;
    Presenter presenter;

    public TripManagementRunningAdapter(List<Trip> modelRunningFragments, Context context, Presenter presenter) {
        this.modelRunningFragments = modelRunningFragments;
        this.context = context;
        this.presenter = presenter;
    }

    public void clear() {
        modelRunningFragments.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TripManagementRunningAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int typeView) {
        View viewNewFragment= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trip_waiting_driver_confirmation,parent,false);
        return new ViewHolder(viewNewFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TripManagementRunningAdapter.ViewHolder holder, int position) {

        Trip model=modelRunningFragments.get(position);

        holder.txt_persuadeCode.setText(String.valueOf(model.getiOfficialTrip()));
        holder.txt_applicantName.setText(model.getStrApplicantName());
        holder.txt_applicantFamily.setText(model.getStrApplicantFamily());
//        holder.txt_tripImportance.setText(model.getTripImportance());
        holder.txt_tripReason.setText(model.getStrTripReason_strComment());
        holder.txt_requestDate.setText(model.getStrRequestDate());
        holder.txt_requestTime.setText(model.getStrRequestTime());
        holder.txt_supposeDate.setText(model.getStrTripDate());
        holder.txt_supposeTime.setText(model.getStrTripTime());
        holder.txt_driverName.setText(model.getStrDriverName());
//        holder.txt_origin.setText(model.getStrOriginName()+" , "+model.getStrOriginAddress());
//        holder.txt_des.setText(model.getStrDestinationName()+" , "+model.getStrDestinationAddress());
        holder.txt_origin.setText(model.getStrOriginName());
        holder.txt_des.setText(model.getStrDestinationName());
//        holder.txt_driverPhone.setText(model.getDriverMobile());


        switch (model.getStrTripImportance_strComment()) {
            case "عادی":
                holder.img_tripImportance.setBackground(ContextCompat.getDrawable(context, R.drawable.star1));
                break;
            case "فوری":
                holder.img_tripImportance.setBackground(ContextCompat.getDrawable(context, R.drawable.star2));
                break;
            case "خیلی فوری":
                holder.img_tripImportance.setBackground(ContextCompat.getDrawable(context, R.drawable.star3));
                break;
        }





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.inflate_alertdialog_runningtablayout);
                dialog.setTitle("Title...");

                TextView txtTripReason= dialog.findViewById(R.id.txtTripReasonAlertRunningTabLayout);
                txtTripReason.setText(model.getStrTripReason_strComment());

                TextView txPersuateCode=dialog.findViewById(R.id.txtPersuateCodeAlertRunningTabLayout);
                txPersuateCode.setText(String.valueOf(model.getiOfficialTrip()));

                TextView txt_NameFamily=dialog.findViewById(R.id.txt_applicantNameFamilyRunningTablayout);
                txt_NameFamily.setText(model.getStrApplicantName()+" "+ model.getStrApplicantFamily());
//                txt_alertdialogNameFamilyNewFragment.setText(model.getStrApplicantName());

                RelativeLayout rlDelete=dialog.findViewById(R.id.rlDeleteAlertRunningTabLayout);
                rlDelete.setOnClickListener(v1 -> dialog.dismiss());

                TextView txt_ApplicatntPhone=dialog.findViewById(R.id.txt_ApplicantPhoneAlertRunningTabLayout);
                txt_ApplicatntPhone.setText(model.getStrApplicantMobile());



                TextView txt_driverNameFamily=dialog.findViewById(R.id.txt_DriverNameFamilyAlertRunningTablayout);
                txt_driverNameFamily.setText(model.getStrDriverName());


                TextView txt_driverPhone=dialog.findViewById(R.id.txt_DriverPhoneAlertRunningTabLayout);
                txt_driverPhone.setText(model.getDriverMobile());



                TextView txt_origin=dialog.findViewById(R.id.txt_originAlertRunningTabLayout);
                txt_origin.setText(model.getStrOriginName()+ " , "+model.getStrOriginAddress());

                TextView txt_des=dialog.findViewById(R.id.txt_desAlertRunningTabLayout);
                txt_des.setText(model.getStrDestinationName()+ " , "+model.getStrDestinationAddress());


                TextView txt_passengers=dialog.findViewById(R.id.txt_passengersAlertRunningTabLayout);
                txt_passengers.setText(model.getStrPassengers());



                ImageView img=dialog.findViewById(R.id.imgStarAlertRunningTabLayout);
                switch (model.getStrTripImportance_strComment()) {
                    case "عادی":
                        img.setBackground(ContextCompat.getDrawable(context, R.drawable.star1));
                        break;
                    case "فوری":
                        img.setBackground(ContextCompat.getDrawable(context, R.drawable.star2));
                        break;
                    case "خیلی فوری":
                        img.setBackground(ContextCompat.getDrawable(context, R.drawable.star3));
                        break;
                }

                TextView txt_tripImportance=dialog.findViewById(R.id.txtTripImportanceAlertRunningTabLayout);
                txt_tripImportance.setText(model.getStrTripImportance_strComment());







                TextView txt_location= dialog.findViewById(R.id.txt_location_alertRunningTabLayout);
                txt_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context,MapsActivityTabLayout.class);
                        intent.putExtra("flatSourceTabLayout",model.getfLatSource());
                        intent.putExtra("flngSourceTabLayout",model.getfLonSource());
                        intent.putExtra("flatDesTabLayout",model.getfLatDestination());
                        intent.putExtra("flngDesTabLayout",model.getfLonDestination());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        dialog.dismiss();

                    }
                });

                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelRunningFragments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_persuadeCode,txt_applicantName,txt_applicantFamily,txt_tripImportance,txt_tripReason,
                txt_requestDate,txt_requestTime,txt_supposeDate,txt_supposeTime,txt_driverName,txt_driverPhone
                ,txt_origin,txt_des;
        ImageView img_tripImportance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_persuadeCode=itemView.findViewById(R.id.txt_row_txtPersuadeCodeWaitingDriverFragment);
            txt_applicantName=itemView.findViewById(R.id.txt_row_txtApplicantNameWaitingDriverFragment);
            txt_applicantFamily=itemView.findViewById(R.id.txt_row_txtFamilyWaitingDriverFragment);
//            txt_tripImportance=itemView.findViewById(R.id.txt_row_TripImportanceNewFragment);
            txt_tripReason=itemView.findViewById(R.id.txt_row_TripReasonWaitingDriverFragment);
            txt_requestDate=itemView.findViewById(R.id.txt_row_RequestDateWaitingDriverFragment);
            txt_requestTime=itemView.findViewById(R.id.txt_row_RequestTimeWaitingDriverFragment);
            txt_supposeDate=itemView.findViewById(R.id.txt_row_TripDateWaitingDriverFragment);
            txt_supposeTime=itemView.findViewById(R.id.txt_row_TripTimeWaitingDriverFragment);
            txt_driverName=itemView.findViewById(R.id.txt_row_driverNameWaitingDriverFragment);
            txt_origin=itemView.findViewById(R.id.txt_row_originAddressWaitingDriverTabLayout);
            txt_des=itemView.findViewById(R.id.txt_row_desAddressWaitingDriverTabLayout);
            img_tripImportance=itemView.findViewById(R.id.imgTripImportanceConfirmedTripFragment);
//            txt_driverPhone=itemView.findViewById(R.id.txt_row_driverPhoneWaitingDriverFragment);

        }
    }
}
