package com.rayanandisheh.peysepar.passenger.activities.assign_driver;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.models.DriverInfo;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class AssignDriverAdapter extends RecyclerView.Adapter<AssignDriverAdapter.ViewHolder> {
    public List<DriverInfo> driverInfoModel;
    Context context;
    Presenter presenter;

    public AssignDriverAdapter(List<DriverInfo> driverInfoModel, Context context, Presenter presenter) {
        this.driverInfoModel = driverInfoModel;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public AssignDriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewNewFragment= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_assign_driver,parent,false);
        return new ViewHolder(viewNewFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignDriverAdapter.ViewHolder holder, int position) {

        DriverInfo model=driverInfoModel.get(position);
        holder.txt_driverName.setText(model.getDrivername());
        holder.txt_carName.setText(model.getMobileName());
        holder.txt_carType.setText(model.getMobiletype());
        holder.txt_status.setText(model.getDriverStatus());
        holder.txt_todayServiceCount.setText(String.valueOf(model.getTripcount()));


        //todo driver_image must be put via picasso


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                dialog.setMessage( "راننده انتخابی شما : "+model.getDrivername() +"\n");
                dialog.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //request to server
//                        Toaster.shorter("تایید");
                        presenter.confirmAlertSelectedDriver(model.getStrunitid() , model.getIofficialtrip());
                    }
                });
                dialog.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toaster.shorter("لغو");
                    }
                });
                dialog.create().show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return driverInfoModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_driverName,txt_carType,txt_carName,txt_status,txt_todayServiceCount;
        CircleImageView driverImagge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_driverName=itemView.findViewById(R.id.row_driverNameAssignDriver);
            txt_carName=itemView.findViewById(R.id.row_carNameAssignDriver);
            txt_carType=itemView.findViewById(R.id.row_carTypeAssignDriver);
            txt_status=itemView.findViewById(R.id.row_statusAssignDriver);
            txt_todayServiceCount=itemView.findViewById(R.id.row_todayServiceCountAssignDriver);
            driverImagge=itemView.findViewById(R.id.row_assignDriver_img);
        }
    }
}
