package com.rayanandisheh.peysepar.passenger.activities.map_choose_origin;

import android.content.Context;
import com.google.android.gms.maps.model.LatLng;

public interface Contract {

    interface View{

    }

    interface Presenter {

        void attachView (Context context,View view);

        void btnRegisterPressed(LatLng position);

        void saved();

    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void saveSelectedLatLng(LatLng position);




    }

}
