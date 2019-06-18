package com.rayanandisheh.peysepar.passenger.activities.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.main.MainActivity;
import com.rayanandisheh.peysepar.passenger.helpers.*;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.listeners.IPickResult;
import de.hdodenhof.circleimageview.CircleImageView;

///12/2

public class ProfileActivity extends PersianAppcompatActivity implements Contract.View, IPickResult {
    Contract.Presenter presenter = new Presenter();
    Context context;

    //    Toolbar toolbar;
    TextView txtMobileProfile, tvProfilePic,txtSelectedPositionName,txtOrgPosition;
    CircleImageView ivProfilePic;
    Bitmap bmProfile;
    EditText edtNameProfile, edtFamilyProfile, edtNationalIDProfile,edtOptionalOrigin;
    //    RadioGroup radioGroup;
    RadioButton rbMan;
    Button btn;
    ProgressBar pb;
    ImageView ivWarningProfileName, ivWarningProfileFamily, ivWarningProfileNcode;
    RelativeLayout rlChoosePermanentOrigin;

    CheckBox chkProfile;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        context = this;
        presenter.attachView(context, this);

        bindView();


        edtNameProfile.setText(App.userInfo.getStrName());
        edtFamilyProfile.setText(App.userInfo.getStrFamily());
        edtNationalIDProfile.setText(App.userInfo.getNationalCode());


        presenter.viewLoaded(edtNameProfile, edtFamilyProfile, edtNationalIDProfile);

        if(App.userInfo.getStrChart() !=null && !App.userInfo.getStrChart().equals("")  )
        txtOrgPosition.setText(App.userInfo.getStrChart());
        else
            txtOrgPosition.setText("اختصاص نیافته");


        if (App.marker_existance) {
            chkProfile.setChecked(true);
            edtOptionalOrigin.setText(Cache.getString("selectedPositionName"));
        }
        else{
            chkProfile.setChecked(false);
            edtOptionalOrigin.setText("");
        }


        try{
            Picasso.get()
                    .load(App.userInfo.getImgLink())
                    .placeholder(R.drawable.ic_account3)// Place holder image from drawable folder
                    .error(R.drawable.ic_account3)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .resize(110, 110)
                    .centerCrop()
                    .into(ivProfilePic);
        }catch (Exception e){

        }



        tvProfilePic.setOnClickListener(v -> presenter.showSelectPicDialog());
        ivProfilePic.setOnClickListener(v -> presenter.showSelectPicDialog());
        txtMobileProfile.setText(Cache.getString("mobileNumber"));

        btn.setOnClickListener(v -> btnClicked());

        rlChoosePermanentOrigin.setOnClickListener(v -> presenter.rlChoosePermanentOriginPressed());
        txtSelectedPositionName.setOnClickListener(v -> presenter.rlChoosePermanentOriginPressed());


//        if(Cache.getLat("LAT" ,0)!=0 && Cache.getLat("LNG" ,0)!=0 ){
//            chkProfile.setChecked(true);
//        }else{
//            chkProfile.setChecked(false);
//        }

//        chkProfile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(!isChecked){
//                    Cache.setLng("LNG",0);
//                    Cache.setLat("LAT",0);
//                }
//            }
//        });
    }



    private void btnClicked() {
            presenter.btnPressed(edtNameProfile.getText().toString(), edtFamilyProfile.getText().toString(),
                    edtNationalIDProfile.getText().toString(),edtOptionalOrigin.getText().toString(), rbMan.isChecked(), bmProfile);
    }

    private void bindView() {

        toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setTitle("پروفایل");



        btn = findViewById(R.id.btnProfile);
        pb = findViewById(R.id.pbProfile);
        edtNameProfile = findViewById(R.id.edtNameProfile);
        edtFamilyProfile = findViewById(R.id.edtFamilyProfile);
        edtNationalIDProfile = findViewById(R.id.edtNationalIDProfile);
        rbMan = findViewById(R.id.rbMan);
        txtMobileProfile = findViewById(R.id.txtMobileProfile);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        tvProfilePic = findViewById(R.id.tvProfilePic);
        ivWarningProfileName = findViewById(R.id.ivWarningProfileName);
        ivWarningProfileFamily = findViewById(R.id.ivWarningProfileFamily);
        ivWarningProfileNcode = findViewById(R.id.ivWarningProfileNcode);
        rlChoosePermanentOrigin=findViewById(R.id.rlChoosePermanentOrigin);
        txtSelectedPositionName=findViewById(R.id.txtSelectedPositionName);
        edtOptionalOrigin=findViewById(R.id.edtOptionalOrigin);
        chkProfile=findViewById(R.id.chkProfile);

        txtOrgPosition=findViewById(R.id.txtOrgPosition);

    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            ivProfilePic.setImageBitmap(r.getBitmap());
            bmProfile = r.getBitmap();
        } else
            Toaster.shorter(r.getError().getMessage());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.menuOption:
                openDialogDeleteCache();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openDialogDeleteCache() {

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("مطمین هستید که از برنامه می خواهید خارج شوید؟");
        builder.setPositiveButton("بله", (dialog, which) -> {
            Cache.setString("mobileNumber","");

            exitApp();
        });
        builder.setNegativeButton("خیر", (dialog, which) -> {

        });
        builder.create().show();
    }


    @Override
    public void hideProgressBar() {
        pb.setVisibility(View.GONE);
        btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProggressBar() {
        pb.setVisibility(View.VISIBLE);
        btn.setVisibility(View.GONE);
    }

    @Override
    public void showNameError() {
        ivWarningProfileName.setVisibility(View.VISIBLE);
        Toaster.shorter("نام وارد شده صحیح نمی باشد");
    }

    @Override
    public void showFamilyError() {
        ivWarningProfileFamily.setVisibility(View.VISIBLE);

//        if(Validate.lastName(edtFamilyProfile.getText().toString()))
//            ivWarningProfileFamily.setVisibility(View.GONE);
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                ivWarningProfileFamily.setVisibility(View.GONE);
//            }
//        }, 5000);
        Toaster.shorter("نام خانوادگی وارد شده صحیح نمی باشد");
    }


    @Override
    public void showNcodeError() {
        ivWarningProfileNcode.setVisibility(View.VISIBLE);
        Toaster.shorter("کد ملی وارد شده صحیح نمی باشد");
    }



    @Override
    public void showImageErrorName() {
        ivWarningProfileName.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageErrorName() {
        ivWarningProfileName.setVisibility(View.GONE);
    }

    @Override
    public void showImageErrorFamily() {
        ivWarningProfileFamily.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageErrorFamily() {
        ivWarningProfileFamily.setVisibility(View.GONE);
    }

    @Override
    public void showImageErrorNcode() {
ivWarningProfileNcode.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageErrorNcode() {
        ivWarningProfileNcode.setVisibility(View.GONE);
    }

    @Override
    public void showDialogeToTurnOnGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Turn On GPS");
        builder.setMessage("لطفا GPS را فعال نمایید");

        // add the buttons
        builder.setPositiveButton("فعالسازی", (dialog, which) -> {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            presenter.showGPSsetting();
        });
        builder.setNegativeButton("انصراف", (dialog, which) ->{});

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showErrorEmpetyEdtPermanentAddress() {
        edtOptionalOrigin.setError("لطفا مبدا را تایپ کنید");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.requestResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onResume() {
        super.onResume();









        //todo permanentAddress must be saved is server then back to app to use here
        if (App.marker_existance) {

            if(App.selectedPositionName.length()!=0)
            edtOptionalOrigin.setText(App.selectedPositionName);
            else if(Cache.getString("selectedPositionName") !="")
                edtOptionalOrigin.setText(Cache.getString("selectedPositionName"));
            else
                edtOptionalOrigin.setText("");


        }else if(!App.marker_existance && Cache.getString("selectedPositionName").equals("")){
            edtOptionalOrigin.setText("");
        }else if(!Cache.getString("selectedPositionName").equals("") && App.userInfo.getfLat()!=0 && App.userInfo.getfLon()!=0){
            edtOptionalOrigin.setText(Cache.getString("selectedPositionName"));
        }



        else if(App.userInfo.getfLat()==0 && App.userInfo.getfLon()==0)
        {
            edtOptionalOrigin.setText("");
        }


//        if((App.userInfo.getfLat()!=0 && App.userInfo.getfLon()!=0) && App.marker_existance){
//            chkProfile.setChecked(true);
//        }else if((App.userInfo.getfLat() ==0 && App.userInfo.getfLon()==0) || !App.marker_existance){
//            chkProfile.setChecked(false);
//        }
//

        if(edtOptionalOrigin.length()>0){
            chkProfile.setChecked(true);
        }else{
            chkProfile.setChecked(false);
        }

    }


         private void exitApp() {
             MainActivity.exit = true;

             finish();

        }
    }



