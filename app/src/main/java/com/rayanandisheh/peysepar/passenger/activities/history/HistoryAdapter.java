package com.rayanandisheh.peysepar.passenger.activities.history;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.currentTripDetails.CurrentTripDetailsActivity;
import com.rayanandisheh.peysepar.passenger.activities.historyDetails.HistoryDetailsActivity;
import com.rayanandisheh.peysepar.passenger.models.Trip;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
//    private List<Trip> modelHistory;
private List<Trip> modelFrHistorytTrip;
    private Context context;

    public HistoryAdapter(List<Trip> modelFrHistorytTrip, Context context) {
        this.modelFrHistorytTrip = modelFrHistorytTrip;
        this.context = context;
    }

//    public void setModelHistory(List<Trip> modelHistory) {
//        this.modelHistory = modelHistory;
//        notifyDataSetChanged();
//    }


    public void setModelFrHistorytTrip(List<Trip> modelFrHistorytTrip) {
        this.modelFrHistorytTrip = modelFrHistorytTrip;
        notifyDataSetChanged();
    }

    public void clear(){
        modelFrHistorytTrip.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Trip model= modelFrHistorytTrip.get(position);
        holder.txtDriverName.setText(model.getStrDriverName());
        holder.txtOrigin.setText(model.getStrOriginName());
        holder.txtDestination.setText(model.getStrDestinationName());
//        holder.txtDateTime.setText(String.format("%s-%s", model.getStrTripDate(), model.getStrTripTime()));
        holder.txtDateTime.setText(model.getStrTripDate() +" "+"-"+" "+model.getStrTripTime());

//        Picasso.with(context)
////                .load(App.ServerURL + "/file/ImageProfile/" + Constants.car.getStrMobile() + ".jpg")
//                .load(App.ServerURL + "/file/ImageProfile/" + " " + ".jpg")
//                .error(R.drawable.ic_account3)
//                .placeholder(R.drawable.ic_account3)
//                .into(holder.img);


        holder.itemView.setOnClickListener(v -> {
            HistoryDetailsActivity.model=model;
            Intent intent=new Intent(context,HistoryDetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });


//        holder.txtDetail.setOnClickListener(v -> {
//            HistoryDetailsActivity.model=model;
//            Intent intent=new Intent(context,HistoryDetailsActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        if(modelFrHistorytTrip != null){
            return modelFrHistorytTrip.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDriverName,txtOrigin,txtDestination,txtDateTime,txtDetail;
        CircleImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDriverName=itemView.findViewById(R.id.txt_row_historyName);
            txtOrigin=itemView.findViewById(R.id.txt_row_historyOrigin);
            txtDestination=itemView.findViewById(R.id.txt_row_historyDestination);
            txtDateTime=itemView.findViewById(R.id.txt_row_historyDateTime);
            txtDetail=itemView.findViewById(R.id.txt_row_historyDetail);
            img=itemView.findViewById(R.id.img_row_history);


        }
    }
}
