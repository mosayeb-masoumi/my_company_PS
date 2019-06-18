package com.rayanandisheh.peysepar.passenger.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.profile.ProfileActivity;
import com.rayanandisheh.peysepar.passenger.activities.trip_management.TripManagementActivity;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;
import com.rayanandisheh.peysepar.passenger.fragment.bottom_sheet_fragment.BottomSheetFragment;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Time;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class MainActivity extends mAppCompatActivity implements Contract.View, NavigationView.OnNavigationItemSelectedListener {
    Contract.Presenter presenter = new Presenter();
    Context context;
    Toolbar toolbar;
    public static boolean exit = false;


    //    ImageView ivMenu;
    FloatingActionButton fabADD, fabInfo;
    RecyclerView recyclerView;
    CurrentTripAdapter adapter;

    SwipeRefreshLayout swipeRefresh;

//    List<Trip> modelCurrentTrip =new ArrayList<>();

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    View navHeader;
    CircleImageView ivMenuProfilePicture;
    ProgressBar progressBar;
    ImageView img_noCurrentItem;
    TextView tvMenuName,txtDateHeadMain;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        presenter.attachView(context, this);
        bindViews();

        tvMenuName.setText(String.format("%s %s", App.userInfo.getStrName(), App.userInfo.getStrFamily()));

        txtDateHeadMain.setText(Time.getNowPersianDate());
        swipeRefresh.setOnRefreshListener(() -> {
//            adapter.clear();
            presenter.swipRefreshPressed();
        });

        ivMenuProfilePicture.setOnClickListener(v -> {
             startActivity(new Intent(context,ProfileActivity.class));
            drawerLayout.closeDrawer(Gravity.START);
        });


     //todo TripManagement Access
//       if(App.userInfo.getDashboard()==1) {
//           Menu menu = navigationView.getMenu();
//           MenuItem target = menu.findItem(R.id.nav_tripManagement);
//           target.setVisible(true);
//       }else{
//           Menu menu = navigationView.getMenu();
//           MenuItem target = menu.findItem(R.id.nav_tripManagement);
//           target.setVisible(false);
//       }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (exit)
            exitApp();

        presenter.checkNightMode();
        tvMenuName.setText(String.format("%s %s", App.userInfo.getStrName(), App.userInfo.getStrFamily()));
        presenter.viewLoaded();


        try{
            Picasso.get()
                    .load(App.userInfo.getImgLink())
                    .placeholder(R.drawable.ic_account3)// Place holder image from drawable folder
                    .error(R.drawable.ic_account3)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .resize(110, 110)
                    .centerCrop()
                    .into(ivMenuProfilePicture);
        }catch (Exception e){

        }

    }


    private void bindViews() {

        toolbar = findViewById(R.id.toolbar_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
//
        swipeRefresh = findViewById(R.id.swipeRefreshCurrentTrip);

        img_noCurrentItem = findViewById(R.id.img_noCurrentItem);
        progressBar = findViewById(R.id.progressBar_main);
//        ivMenu = findViewById(R.id.ivMenu);
        navigationView = findViewById(R.id.navigationView);
        navHeader = navigationView.getHeaderView(0);
        tvMenuName = navHeader.findViewById(R.id.tvMenuName);
        navigationView.setNavigationItemSelectedListener(this);


        txtDateHeadMain = findViewById(R.id.txtDateHeadMain);
        ivMenuProfilePicture = navHeader.findViewById(R.id.ivMenuProfilePicture);

        fabADD = findViewById(R.id.fabADD);
        fabInfo = findViewById(R.id.fabInfo);

//initializing RecyclerView
        recyclerView = findViewById(R.id.rvCurrentTrip);
//        adapter=new CurrentTripAdapter(modelCurrentTrip,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0 || dy < 0 && fabADD.isShown() && fabInfo.isShown()) {
                    fabADD.hide();
                    fabInfo.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fabADD.show();
                    fabInfo.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        fabADD.setOnClickListener(v -> presenter.fabADDpressed());
        fabInfo.setOnClickListener(v -> showFabInfo());


    }

    private void showFabInfo() {
        BottomSheetFragment bottomFragment = new BottomSheetFragment();
        bottomFragment.show(getSupportFragmentManager(), bottomFragment.getTag());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        presenter.menuItemSelected(item.getItemId());

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void showAvailableUpdate() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(R.string.newUpdateAvailable);
        dialog.setPositiveButton(R.string.update, (dialog15, which) -> presenter.updateRequest());
        dialog.setNegativeButton(R.string.cancel, (dialog16, which) -> dialog16.dismiss());
        dialog.show();
    }

    @Override
    public void showForceUpdate() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(R.string.forceUpdateAvailable);
        dialog.setPositiveButton(R.string.update, (dialog1, which) -> presenter.updateRequest());
        dialog.setNegativeButton(R.string.exit, (dialog12, which) -> presenter.exitApp());
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void showPermissionAlert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(R.string.externalStorageAccess);
        dialog.setPositiveButton(R.string.accessPermission, (dialog13, which) -> presenter.requestPermission());
        dialog.setNegativeButton(R.string.cancel, (dialog14, which) -> dialog14.dismiss());
        dialog.show();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setAdapter() {

        progressBar.setVisibility(View.GONE);
        adapter = new CurrentTripAdapter(App.listCurrentTrip, context);
        adapter.setModelFrCurrentTrip(App.listCurrentTrip);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
    }


    @Override
    public void showProgressBar() {
//        progressBar.setVisibility(View.VISIBLE);
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void showSwipeRefresh() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefresh() {
        swipeRefresh.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showImageEmpety() {
        img_noCurrentItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageEmpety() {
        img_noCurrentItem.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            presenter.requestPermissionAccessed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menuOption) {
//            drawerLayout.openDrawer(Gravity.RIGHT);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            exitApp();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "برای خروج مجددا دکمه ی بازگشت را بزنید", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    /*---------- close all app---------*/
    private void exitApp() {
        finish();
        startActivity(new Intent(Intent.ACTION_MAIN).
                addCategory(Intent.CATEGORY_HOME).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        super.finish();

    }
    
}