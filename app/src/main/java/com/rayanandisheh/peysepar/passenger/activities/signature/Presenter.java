package com.rayanandisheh.peysepar.passenger.activities.signature;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.widget.RelativeLayout;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.DrawView;
import com.rayanandisheh.peysepar.passenger.helpers.Toaster;


public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();
    private DrawView drawView;

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this, context);
    }

    @Override
    public void loadView(RelativeLayout parent, FloatingActionButton fab) {
        drawView = new DrawView(context);
//        parent.setDrawingCacheEnabled(true);
        view.showSignatureAvailable(drawView);
        fab.setOnClickListener(v -> drawView.clear());
    }

    @Override
    public void btnRegisterPressed(float rating) {

        if (rating == 0.0) {
            Toaster.shorter("لطفا امتیاز دهید");
            view.hideProgressbar();

        } else if (!drawView.isDrawed()) {
            Toaster.shorter("لطفا امضای خود را وارد کنید");
            view.hideProgressbar();
        } else {
            drawView.setDrawingCacheEnabled(true);
            drawView.buildDrawingCache(true);
            Bitmap b = Bitmap.createBitmap(drawView.getDrawingCache());
            model.saveAndFinish(b, rating);
            drawView.setDrawingCacheEnabled(false);
        }
    }

    @Override
    public void endTripResult(int result) {

        view.hideProgressbar();
        if(result==1)
            Toaster.shorter("سفر شما به اتمام رسید");
        else if(result==-1)
            Toaster.shorter(context.getString(R.string.connectionFaield));
        else if(result==-2)
            Toaster.shorter(context.getString(R.string.serverFaield));
    }

    @Override
    public void requestStart() {
        view.showProgressBar();
    }
}
