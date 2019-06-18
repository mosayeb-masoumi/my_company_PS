package com.rayanandisheh.peysepar.passenger.activities.history;

import android.content.Context;


public interface Contract {
    interface View{

        void showImg_noItem();
        void hideImg_noItem();

        void stopProgressLoading();

        void setFromDate(String date);

        void setToDate(String date);

        void setAdapter();

        void showProgressLoading();

        void showSwipeRefresh();

        void hideSwipeRefresh();

        void showImageEmpety();
    }

    interface Presenter {

        void attachView (Context context, View view);
        

//        void swipeClicked();

//        void viewLoaded();

        void loadDataResult(int result);

        void edtDatePressed(boolean isFrom);


        void showPregressbar();


        void swipRefreshPressed(String dateFrom, String dateTo);

        void viewLoaded(String dateFrom, String dateTo);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

//        void DataLoaded(RecyclerView recyclerView);

//        void loadData();


//        void loadHistoryTripList();

        void loadHistoryTripList(String dateFrom, String dateTo);
    }
}
