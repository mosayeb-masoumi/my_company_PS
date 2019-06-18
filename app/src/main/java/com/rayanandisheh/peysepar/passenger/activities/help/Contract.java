package com.rayanandisheh.peysepar.passenger.activities.help;

import android.content.Context;
import android.webkit.WebView;

public interface Contract {
    interface View{

        void loadUrl(WebView webView);
    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded(WebView webView);

        void loadUrl(WebView webView);
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void viewLoded(WebView webView);
    }
}
