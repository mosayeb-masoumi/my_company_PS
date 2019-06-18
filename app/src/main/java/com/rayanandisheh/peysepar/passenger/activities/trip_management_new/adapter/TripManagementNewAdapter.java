package com.rayanandisheh.peysepar.passenger.activities.trip_management_new.adapter;

import android.app.AlertDialog;
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

public class TripManagementNewAdapter extends RecyclerView.Adapter<TripManagementNewAdapter.ViewHolder> {

    public List<Trip> modelTripNewTabLayout;
    Context context;
    Presenter presenter;

    public TripManagementNewAdapter(List<Trip> modelTripNewTabLayout, Context context, Presenter presenter) {
        this.modelTripNewTabLayout = modelTripNewTabLayout;
        this.context = context;
        this.presenter = presenter;
    }

    public void clear() {
        modelTripNewTabLayout.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TripManagementNewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewNewFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trip_new_fragment, parent, false);
        return new ViewHolder(viewNewFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TripManagementNewAdapter.ViewHolder holder, int position) {

        Trip model = modelTripNewTabLayout.get(position);
        holder.txt_persuadeCode.setText(String.valueOf(model.getiOfficialTrip()));
        holder.txt_applicantName.setText(model.getStrApplicantName());
        holder.txt_applicantFamily.setText(model.getStrApplicantFamily());
        holder.txt_tripReason.setText(model.getStrTripReason_strComment());
        holder.txt_requestDate.setText(model.getStrRequestDate());
        holder.txt_requestTime.setText(model.getStrRequestTime());
        holder.txt_supposeDate.setText(model.getStrTripDate());
        holder.txt_supposeTime.setText(model.getStrTripTime());
        holder.txt_originAddress.setText(model.getStrOriginAddress());
        holder.txt_desAddress.setText(model.getStrDestinationAddress());
//        holder.txt_originAddress.setText(model.getStrOriginName()+" , "+model.getStrOriginAddress());
//        holder.txt_desAddress.setText(model.getStrDestinationName()+" , "+model.getStrDestinationAddress());
//        holder.txt_applicantPhone.setText(model.getStrApplicantMobile());

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
            dialog.setContentView(R.layout.inflate_alertdialog_newtablayout);
            dialog.setTitle("Title...");

            TextView txtTripReasonInflateAlertDialogTabLayout = dialog.findViewById(R.id.txtTripReasonInflateAlertDialogTabLayout);
            txtTripReasonInflateAlertDialogTabLayout.setText(model.getStrTripReason_strComment());

            TextView txPersuateCodeInflateAlertDialogTabLayout = dialog.findViewById(R.id.txPersuateCodeInflateAlertDialogTabLayout);
            txPersuateCodeInflateAlertDialogTabLayout.setText(String.valueOf(model.getiOfficialTrip()));

            TextView txt_alertdialogNameFamilyNewFragment = dialog.findViewById(R.id.txt_alertdialogNameFamilyNewFragment);
            txt_alertdialogNameFamilyNewFragment.setText(model.getStrApplicantName() + " " + model.getStrApplicantFamily());

            RelativeLayout rlDelete = dialog.findViewById(R.id.rlDeleteInflateAlertDialogNewTabLayout);
            rlDelete.setOnClickListener(v1 -> dialog.dismiss());

            TextView txt_ApplicatntPhone = dialog.findViewById(R.id.txt_ApplicantPhoneAlertDialogNewTabLayout);
            txt_ApplicatntPhone.setText(model.getStrApplicantMobile());

            TextView txt_origin = dialog.findViewById(R.id.txt_originAlertDialogNewTabLayout);
            txt_origin.setText(model.getStrOriginName() + " , " + model.getStrOriginAddress());

            TextView txt_des = dialog.findViewById(R.id.txt_desAlertDialogNewTabLayout);
            txt_des.setText(model.getStrDestinationName() + " , " + model.getStrDestinationAddress());

            TextView txt_passengers = dialog.findViewById(R.id.txt_passenggersAlertDialogNewTabLayout);
            txt_passengers.setText(model.getStrPassengers());

            ImageView img = dialog.findViewById(R.id.imgStarAlertNewTabLayout);
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

            TextView txt_tripImportance = dialog.findViewById(R.id.txtTripImportanceAlertNewTabLayout);
            txt_tripImportance.setText(model.getStrTripImportance_strComment());

            TextView txt_confirm = dialog.findViewById(R.id.txt_ConfirmationNewFragmentDialog);
            TextView txt_cancel = dialog.findViewById(R.id.txt_cancelNewFragmentDialog);
            TextView txt_location = dialog.findViewById(R.id.txt_locationNewFragmentDialog);
            txt_confirm.setOnClickListener(v14 -> {




                //todo unComment
                presenter.confirmNewAlertTripManagement(model.getiOfficialTrip());




                dialog.dismiss();
            });

            txt_cancel.setOnClickListener(v12 -> {
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(context);
                dialog2.setMessage("آیا از لغو سفر با کد:" + model.getiOfficialTrip() + " مطمئن هستید؟");



                dialog2.setPositiveButton("بله", (dialog1, which) -> presenter.cancelNewAlertTripManagement(model.getiOfficialTrip() ,model.getTiTripStatus()));







                dialog2.setNegativeButton("خیر", (dialog12, which) -> dialog12.dismiss());
                dialog2.create().show();
                dialog.dismiss();
            });

            txt_location.setOnClickListener(v13 -> {
                Intent intent = new Intent(context, MapsActivityTabLayout.class);
                intent.putExtra("flatSourceTabLayout", model.getfLatSource());
                intent.putExtra("flngSourceTabLayout", model.getfLonSource());
                intent.putExtra("flatDesTabLayout", model.getfLatDestination());
                intent.putExtra("flngDesTabLayout", model.getfLonDestination());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                dialog.dismiss();
            });
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return modelTripNewTabLayout.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_persuadeCode, txt_applicantName, txt_applicantFamily, txt_tripReason,
                txt_requestDate, txt_requestTime, txt_supposeDate, txt_supposeTime, txt_originAddress, txt_desAddress;
        ImageView img_tripImportance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_persuadeCode = itemView.findViewById(R.id.txt_row_txtPersuadeCodeNewFragment);
            txt_applicantName = itemView.findViewById(R.id.txt_row_txtApplicantNameNewFragment);
            txt_applicantFamily = itemView.findViewById(R.id.txt_row_txtFamilyNewFragment);
            txt_tripReason = itemView.findViewById(R.id.txt_row_TripReasonNewFragment);
            txt_requestDate = itemView.findViewById(R.id.txt_row_RequestDateNewFragment);
            txt_requestTime = itemView.findViewById(R.id.txt_row_RequestTimeNewFragment);
            txt_supposeDate = itemView.findViewById(R.id.txt_row_supposeDateNewFragment);
            txt_supposeTime = itemView.findViewById(R.id.txt_row_supposeTimeNewFragment);
            txt_originAddress = itemView.findViewById(R.id.txt_row_originAddressNewFragment);
            txt_desAddress = itemView.findViewById(R.id.txt_row_DesAddressNewFragment);
            img_tripImportance = itemView.findViewById(R.id.imgTripImportanceNewFragment);
        }
    }
}
