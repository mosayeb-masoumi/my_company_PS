package com.rayanandisheh.peysepar.passenger.activities.about;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;

public class AboutActivity extends mAppCompatActivity implements Contract.View {

    Contract.Presenter presenter = new Presenter();
    Context context;

    ImageView ivIcon;
    TextView tvCoName,tvCoUrl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        context = this;

        bindView();

        presenter.attachView(context, this);
        presenter.activityLoaded();

        tvCoUrl.setOnClickListener(v -> presenter.tvCoUrlPressed());

    }

    private void bindView() {
        ivIcon=findViewById(R.id.ivIcon);
        tvCoName=findViewById(R.id.tvCoName);
        tvCoUrl=findViewById(R.id.tvCoUrl);
    }

    @Override
    public void showAppVersion(String appVersion) {
//        ((TextView)findViewById(R.id.tvAppVersion)).setText(appVersion);
    }
}