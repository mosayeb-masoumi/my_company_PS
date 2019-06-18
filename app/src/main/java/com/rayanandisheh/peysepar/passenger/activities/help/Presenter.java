package com.rayanandisheh.peysepar.passenger.activities.help;

import android.content.Context;
import android.webkit.WebView;

public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this,context);
    }

    @Override
    public void viewLoaded(WebView webView) {
        model.viewLoded(webView);
    }

    @Override
    public void loadUrl(WebView webView) {
        view.loadUrl(webView);
    }

}
