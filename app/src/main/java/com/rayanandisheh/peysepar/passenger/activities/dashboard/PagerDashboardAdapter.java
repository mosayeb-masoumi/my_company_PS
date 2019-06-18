package com.rayanandisheh.peysepar.passenger.activities.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.rayanandisheh.peysepar.passenger.fragment.dashboard_trips.DashboardTripsFragment;
import com.rayanandisheh.peysepar.passenger.fragment.dashboard_vehicles.DashboardVehiclesFragment;

public class PagerDashboardAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    PagerDashboardAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DashboardTripsFragment();

            case 1:
                return new DashboardVehiclesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
