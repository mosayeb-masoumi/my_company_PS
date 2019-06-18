package com.rayanandisheh.peysepar.passenger.activities.dashboard;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.PersianAppcompatActivity;

public class DashboardActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerDashboardAdapter pageAdapter;
    TabItem tabCars;
    TabItem tabTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        context = this;
        presenter.attachView(context, this);

        bindView();



        pageAdapter = new PagerDashboardAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //to change font of tabs in tablayout
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView)LayoutInflater.from(this).inflate(R.layout.custom_tab_font,null);
            Typeface typeface = Typeface.createFromAsset(getAssets(),"IRANSansMobile.ttf");
            tv.setTypeface(typeface);
            tabLayout.getTabAt(i).setCustomView(tv);
        }

    }

    private void bindView() {

        toolbar=findViewById(R.id.toolbar_dashboardActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setTitle("داشبورد");

        tabLayout=findViewById(R.id.tablayout_Dashboard);
        viewPager=findViewById(R.id.viewPagerDashboard);
        tabCars=findViewById(R.id.tabDashboardCarsFragment);
        tabTrips=findViewById(R.id.tabDashboardTripsFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
