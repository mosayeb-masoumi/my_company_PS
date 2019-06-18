package com.rayanandisheh.peysepar.passenger.activities.internet_check;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.splash.SplashActivity;

public class InternetCheckActivity extends AppCompatActivity {

    CheckNetwork checkNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_check);
    }


    @Override
    protected void onStart() {
        super.onStart();
        checkNetwork = new CheckNetwork();
        registerReceiver(checkNetwork, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(checkNetwork);
        super.onStop();
    }

    private class CheckNetwork extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

            if(isConnected){
//                Toast.makeText(context, "اینترنت وصل است", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InternetCheckActivity.this,SplashActivity.class));
                finish();

            }else{
//                Toast.makeText(context, "لطفا اتصال دستگاه  به اینترنت را چک کنید", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
