package com.rayanandisheh.peysepar.passenger.activities.help;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;

public class HelpActivity extends AppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    Toolbar toolbar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        context = this;
        presenter.attachView(context, this);

        bindView();
        presenter.viewLoaded(webView);
    }

    private void bindView() {

        toolbar=findViewById(R.id.tb_help);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setTitle("راهنما");
        webView=findViewById(R.id.webView);
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


    @Override
    public void loadUrl(WebView webView) {
//        webView.loadUrl("https://google.com");
        webView.loadUrl(App.userInfo.getStrUrlHelp());
    }

}
