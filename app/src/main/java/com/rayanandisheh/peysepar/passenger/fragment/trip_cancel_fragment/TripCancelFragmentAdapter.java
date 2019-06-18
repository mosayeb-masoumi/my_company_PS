package com.rayanandisheh.peysepar.passenger.fragment.trip_cancel_fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.map_choose_origin_tablayout.MapsActivityTabLayout;
import com.rayanandisheh.peysepar.passenger.fragment.trip_new_fragment.TripNewFragmentAdapter;
import com.rayanandisheh.peysepar.passenger.models.Trip;

import java.util.List;

public class TripCancelFragmentAdapter extends RecyclerView.Adapter<TripCancelFragmentAdapter.ViewHolder> {

    private List<Trip> modelCanceledFragments;
    Context context;
    Presenter presenter;


    public TripCancelFragmentAdapter(List<Trip> modelCanceledFragments, Context context, Presenter presenter) {
        this.modelCanceledFragments = modelCanceledFragments;
        this.context = context;
        this.presenter = presenter;
    }

    public void clear(){
        modelCanceledFragments.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TripCancelFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewNewFragment= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trip_new_fragment,parent,false);
        return new ViewHolder(viewNewFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TripCancelFragmentAdapter.ViewHolder holder, int position) {
        Trip model = modelCanceledFragments.get(position);
        holder.txt_persuadeCode.setText(String.valueOf(model.getiOfficialTrip()));
        holder.txt_applicantName.setText(model.getStrApplicantName());
        holder.txt_applicantFamily.setText(model.getStrApplicantFamily());
//        holder.txt_tripImportance.setText(model.getTripImportance());
        holder.txt_tripReason.setText(model.getStrTripReason_strComment());
        holder.txt_requestDate.setText(model.getStrRequestDate());
        holder.txt_requestTime.setText(model.getStrRequestTime());
        holder.txt_supposeDate.setText(model.getStrTripDate());
        holder.txt_supposeTime.setText(model.getStrTripTime());
        holder.txt_originAddress.setText(String.format("%s", model.getStrOriginName()));
        holder.txt_desAddress.setText(String.format("%s", model.getStrDestinationName()));


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




        holder.itemView.setOnClickListener(v -> {

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.inflate_alertdialog_canceltablayout);
            dialog.setTitle("Title...");

            TextView txtTripReason= dialog.findViewById(R.id.txtTripReasonAlertCanceledTabLayout);
            txtTripReason.setText(model.getStrTripReason_strComment());

            TextView txPersuateCode=dialog.findViewById(R.id.txtPersuateCodeAlertCanceledTabLayout);
            txPersuateCode.setText(String.valueOf(model.getiOfficialTrip()));

            TextView txt_alertdialogNameFamily=dialog.findViewById(R.id.txt_applicantNameFamilyCanceledTablayout);
            txt_alertdialogNameFamily.setText(model.getStrApplicantName()+" "+ model.getStrApplicantFamily());
//                txt_alertdialogNameFamilyNewFragment.setText(model.getStrApplicantName());

            RelativeLayout rlDelete=dialog.findViewById(R.id.rlDeleteAlertCanceledTabLayout);
            rlDelete.setOnClickListener(v1 -> dialog.dismiss());

            TextView txt_ApplicatntPhone=dialog.findViewById(R.id.txt_ApplicantPhoneAlertCanceledTabLayout);
            txt_ApplicatntPhone.setText(model.getStrApplicantMobile());

            TextView txt_origin=dialog.findViewById(R.id.txt_originAlertCanceledTabLayout);
            txt_origin.setText(model.getStrOriginName()+ " , "+model.getStrOriginAddress());

            TextView txt_des=dialog.findViewById(R.id.txt_desAlertCanceledTabLayout);
            txt_des.setText(model.getStrDestinationName()+ " , "+model.getStrDestinationAddress());


            TextView txt_passengers=dialog.findViewById(R.id.txt_passengersAlertCanceledTabLayout);
            txt_passengers.setText(model.getStrPassengers());



            ImageView img=dialog.findViewById(R.id.imgStarAlertCancelTabLayout);
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

            TextView txt_tripImportance=dialog.findViewById(R.id.txtTripImportanceAlertCancelTabLayout);
            txt_tripImportance.setText(model.getStrTripImportance_strComment());

            TextView txt_confirm = dialog.findViewById(R.id.txt_confirmation_alertCanceledTabLayout);

            txt_confirm.setOnClickListener(v12 -> {
                dialog.dismiss();
                openAlertDialog(model);
            });
            dialog.show();
        });
    }

    private void openAlertDialog(Trip model) {
        AlertDialog.Builder dialog2=new AlertDialog.Builder(context);
        dialog2.setMessage("آیا از لغو سفر با کد:"+ model.getiOfficialTrip()+" مطمئن هستید؟");
        dialog2.setPositiveButton("بله", (dialog21, which) -> {
            presenter.cancelFragmentConfirmAlert(model.getiOfficialTrip());
            dialog21.dismiss();
        });
        dialog2.setNegativeButton("خیر", (dialog212, which) -> dialog212.dismiss());
        dialog2.create().show();
    }


    @Override
    public int getItemCount() {
        return modelCanceledFragments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_persuadeCode,txt_applicantName,txt_applicantFamily,txt_tripImportance,txt_tripReason,
                txt_requestDate,txt_requestTime,txt_supposeDate,txt_supposeTime,txt_originAddress,txt_desAddress;

        ImageView img_tripImportance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_persuadeCode=itemView.findViewById(R.id.txt_row_txtPersuadeCodeNewFragment);
            txt_applicantName=itemView.findViewById(R.id.txt_row_txtApplicantNameNewFragment);
            txt_applicantFamily=itemView.findViewById(R.id.txt_row_txtFamilyNewFragment);
//            txt_tripImportance=itemView.findViewById(R.id.txt_row_TripImportanceNewFragment);
            txt_tripReason=itemView.findViewById(R.id.txt_row_TripReasonNewFragment);
            txt_requestDate=itemView.findViewById(R.id.txt_row_RequestDateNewFragment);
            txt_requestTime=itemView.findViewById(R.id.txt_row_RequestTimeNewFragment);
            txt_supposeDate=itemView.findViewById(R.id.txt_row_supposeDateNewFragment);
            txt_supposeTime=itemView.findViewById(R.id.txt_row_supposeTimeNewFragment);
            txt_originAddress=itemView.findViewById(R.id.txt_row_originAddressNewFragment);
            txt_desAddress=itemView.findViewById(R.id.txt_row_DesAddressNewFragment);
            img_tripImportance=itemView.findViewById(R.id.imgTripImportanceNewFragment);
        }
    }
}
