package com.rayanandisheh.peysepar.passenger.activities.feedback;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.customes.mAppCompatActivity;

public class FeedbackActivity extends mAppCompatActivity implements Contract.View {

    Contract.Presenter presenter = new Presenter();
    Context context;
    Button btFeedback;
    ProgressBar pbFeedback;
    RatingBar ratingBar;
    EditText etFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        bindViews();
        context = this;
        presenter.attachView(context, this);
    }

    private void bindViews() {
        context = this;
        btFeedback = findViewById(R.id.btFeedback);
        pbFeedback = findViewById(R.id.pbFeedback);
        ratingBar = findViewById(R.id.ratingBar);
        etFeedback = findViewById(R.id.etFeedback);

        btFeedback.setOnClickListener(v -> presenter.submit(etFeedback.getText(), ratingBar.getRating()));
    }

    @Override
    public void showLoading() {
        btFeedback.setVisibility(View.GONE);
        pbFeedback.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        pbFeedback.setVisibility(View.GONE);
        btFeedback.setVisibility(View.VISIBLE);
    }
}
