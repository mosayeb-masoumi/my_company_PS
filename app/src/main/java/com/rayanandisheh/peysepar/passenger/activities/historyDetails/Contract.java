package com.rayanandisheh.peysepar.passenger.activities.historyDetails;

import android.content.Context;

public interface Contract {
    interface View{

        void showProgressBar();

        void hideProgressBar();

        void setTodayScore();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void sendRate(float rating, int iOfficialTrip);

        void changeScoreResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requestSendTodayRating(float rating, int iOfficialTrip);
    }
}
