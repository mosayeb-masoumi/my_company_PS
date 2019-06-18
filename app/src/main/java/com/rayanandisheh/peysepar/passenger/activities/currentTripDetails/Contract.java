package com.rayanandisheh.peysepar.passenger.activities.currentTripDetails;

import android.content.Context;
import com.rayanandisheh.peysepar.passenger.models.Trip;

public interface Contract {
    interface View{

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void btnCanceledPressed(Trip iOfficialTrip);

        void cencelTripResult(Integer cancelTripResult);

        void btnCallPressed(String driverMobile);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void cancelTripRequest(Trip iOfficialTrip);

        void btnCallPressed(String driverMobile);
    }
}
