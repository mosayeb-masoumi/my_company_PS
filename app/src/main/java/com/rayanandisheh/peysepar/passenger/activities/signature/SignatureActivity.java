package com.rayanandisheh.peysepar.passenger.activities.signature;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.DrawView;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignatureActivity extends PersianAppcompatActivity implements Contract.View {


    Contract.Presenter presenter = new Presenter();
    Context context;
    CircleImageView imgView;
    TextView txtDriverName, txtOriginAddress, txtDestinationAddress;
    Button btnRegister;
    ProgressBar pbSignature;
    FloatingActionButton fab;
    RelativeLayout parent;
    RatingBar ratingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        context = this;
        presenter.attachView(context, this);
        bindView();

        presenter.loadView(parent, fab);

        txtDriverName.setText(App.notifModel.getStrDriverName());
        txtOriginAddress.setText(App.notifModel.getOriginAddress());
        txtDestinationAddress.setText(App.notifModel.getDestinationAddress());


//        imgView.setImageURI(App.notifModel.getDriverImage());


        btnRegister.setOnClickListener(v -> presenter.btnRegisterPressed(ratingBar.getRating()));

    }

    private void bindView() {
        imgView = findViewById(R.id.ivSignature);
        txtDriverName = findViewById(R.id.txtDriverName);
        txtOriginAddress = findViewById(R.id.txtOrigin);
        txtDestinationAddress = findViewById(R.id.txtDestination);
        btnRegister = findViewById(R.id.btnSignature);
        pbSignature = findViewById(R.id.pbSiggnature);
        fab = findViewById(R.id.fabSignature);
        parent = findViewById(R.id.rlDrawing);
        ratingBar = findViewById(R.id.rateSignature);
    }


    @Override
    public void showSignatureAvailable(DrawView drawView) {
        parent.addView(drawView);
    }

    @Override
    public void showProgressBar() {
        pbSignature.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressbar() {
        pbSignature.setVisibility(View.GONE);
        btnRegister.setVisibility(View.VISIBLE);
    }


}
