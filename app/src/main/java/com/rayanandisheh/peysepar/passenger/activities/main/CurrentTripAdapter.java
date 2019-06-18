package com.rayanandisheh.peysepar.passenger.activities.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.currentTripDetails.CurrentTripDetailsActivity;
import com.rayanandisheh.peysepar.passenger.models.Trip;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CurrentTripAdapter extends RecyclerView.Adapter<CurrentTripAdapter.ViewHolder> {


    private List<Trip> modelFrCurrentTrip;
    private Context context;

    public CurrentTripAdapter(List<Trip> modelFrCurrentTrip, Context context) {
        this.modelFrCurrentTrip = modelFrCurrentTrip;
        this.context = context;
    }


    public void setModelFrCurrentTrip(List<Trip> modelFrCurrentTrip) {
        this.modelFrCurrentTrip = modelFrCurrentTrip;
        notifyDataSetChanged();
    }


    //for clearingd data when use swipeRefresh
    public void clear() {
        modelFrCurrentTrip.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_current_trip, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trip model = modelFrCurrentTrip.get(position);

//        holder.txt_startPoint.setText(model.getStrOriginName());
//        holder.txt_destination.setText(model.getStrDestinationName());
        holder.txt_startPoint.setText(model.getStrOriginAddress());
        holder.txt_destination.setText(model.getStrDestinationAddress());


        holder.txt_clock.setText(model.getStrTripTime());
        holder.txt_date.setText(model.getStrTripDate());
        holder.row_tripStatus.setText(model.getStrOfficialStatus_strComment());
        holder.row_startpoint_TripCode.setText(String.valueOf(model.getiOfficialTrip()));


        if (model.getStrDriverName().equals(""))
            holder.txt_driver_name.setText("راننده اختصاص نیافته");
        else
            holder.txt_driver_name.setText(model.getStrDriverName());



        if(model.getStrTripImportance_strComment().equals("عادی"))
            holder.img_star.setBackground(ContextCompat.getDrawable(context,R.drawable.star1));
        else if(model.getStrTripImportance_strComment().equals("فوری"))
            holder.img_star.setBackground(ContextCompat.getDrawable(context,R.drawable.star2));
        else if(model.getStrTripImportance_strComment().equals("خیلی فوری"))
            holder.img_star.setBackground(ContextCompat.getDrawable(context,R.drawable.star3));





        //todo url driverImage
//        Glide.with(context).load(url).into(holder.img_driver);

        holder.itemView.setOnClickListener(v -> {
            CurrentTripDetailsActivity.model = model;
            Intent intent = new Intent(context, CurrentTripDetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        switch (model.getTiTripStatus()) {
            case 0:
                holder.ivStateCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_white));
                break;
            case 1:
                holder.ivStateCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_yellow));
                break;
            case 2:
                holder.ivStateCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_orange));
                break;
            case 3:
                holder.ivStateCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_blue_light));
                break;
            case 5:
              break;
            case 6:
                holder.ivStateCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_green));
                break;
        }
    }


    @Override
    public int getItemCount() {
        if (modelFrCurrentTrip != null) {
            return modelFrCurrentTrip.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_driver_name, txt_startPoint, txt_destination, txt_clock, txt_date, row_tripStatus,row_startpoint_TripCode;
        ImageView img_driver, ivStateCircle, img_star;
        CardView row_history_cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            row_startpoint_TripCode=itemView.findViewById(R.id.row_startpoint_TripCode);
            row_history_cv = itemView.findViewById(R.id.row_currentTrip_cv);
            txt_driver_name = itemView.findViewById(R.id.row_driverNameCurrentTrip);
            txt_startPoint = itemView.findViewById(R.id.row_startpoint_CurrentTrip);
            txt_destination = itemView.findViewById(R.id.row_destination_currentTrip);
            txt_clock = itemView.findViewById(R.id.row_clock_currentTrip);
            txt_date = itemView.findViewById(R.id.row_date_currentTrip);
            img_driver = itemView.findViewById(R.id.img_row_current);
            row_tripStatus = itemView.findViewById(R.id.row_tripStatus_currentTrip);
            ivStateCircle = itemView.findViewById(R.id.img_row_ivStateCircle_currentTrip);
            img_star = itemView.findViewById(R.id.row_currentTrip_star);
        }
    }
}

