package com.rayanandisheh.peysepar.passenger.activities.trip_management;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.rayanandisheh.peysepar.passenger.fragment.trip_cancel_fragment.TripCanceledFragment;
import com.rayanandisheh.peysepar.passenger.fragment.trip_dashboard_fragment.TripDashboardFragment;
import com.rayanandisheh.peysepar.passenger.fragment.trip_new_fragment.TripNewFragment;
import com.rayanandisheh.peysepar.passenger.fragment.trip_confirmed_fragment.TripConfirmedFragment;
import com.rayanandisheh.peysepar.passenger.fragment.trip_draiver_confirmed_fragment.TripDriverConfirmedFragment;
import com.rayanandisheh.peysepar.passenger.fragment.trip_running_fragment.TripRunningFragment;
import com.rayanandisheh.peysepar.passenger.helpers.App;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TripNewFragment();

            case 1:
                return new TripConfirmedFragment();

            case 2:
                return new TripDriverConfirmedFragment();

            case 3:
                return new TripRunningFragment();

            case 4:
                return new TripCanceledFragment();

            case 5:
                return new TripDashboardFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}