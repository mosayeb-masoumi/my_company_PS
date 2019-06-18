package com.rayanandisheh.peysepar.passenger.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.activities.signature.SignatureActivity;
import com.rayanandisheh.peysepar.passenger.activities.splash.SplashActivity;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.models.NotificationModel;

public class MFirebaseMessagingService extends FirebaseMessagingService {

    public MFirebaseMessagingService() {
        super();
    }

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setStrDriverName(bundle.getString("strDriverName") != null ? bundle.getString("strDriverName") : "");
            notificationModel.setDriverImage(bundle.getString("driverImage") != null ? bundle.getString("driverImage") : "");
            notificationModel.setOriginAddress(bundle.getString("OriginAddress") != null ? bundle.getString("OriginAddress") : "");
            notificationModel.setDestinationAddress(bundle.getString("DestinationAddress") != null ? bundle.getString("DestinationAddress") : "");
            notificationModel.setTitle(bundle.getString("title") != null ? bundle.getString("title") : "");
            notificationModel.setiOfficialTrip(bundle.getString("iOfficialTrip") != null ? Integer.parseInt(bundle.getString("iOfficialTrip")) : 0);
            notificationModel.setType(bundle.getString("Type") != null ? bundle.getString("Type") : "");
            notificationModel.setMessage(bundle.getString("message") != null ? bundle.getString("message") : "");

            handleDataMessage(notificationModel);
        }
    }

    private void handleDataMessage(NotificationModel notificationModel) {

        switch (notificationModel.getType()) {
            case "NewRequest":

//                save sended data from notification model in app
                App.notifModel = notificationModel;

                getApplicationContext().startActivity(new Intent(getApplicationContext(), SignatureActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;

                                 /*---------------------*/

            case "":

                break;








            default:
                Bundle myBundle = new Bundle();
                myBundle.putString("title", "myBundleTitle");
                // myBundle.putString("message", "myBundleMessage");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentTitle(notificationModel.getTitle())
                        .setContentTitle(notificationModel.getTitle())
                        .setContentText(notificationModel.getMessage())
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true)
                        .setContentIntent(PendingIntent.getActivity(this, 111, new Intent(this, SplashActivity.class)
                                .putExtras(myBundle)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK), 0))
//                            .setLargeIcon(BitmapFactory.decodeResource(Core.context.getResources(), R.mipmap.ic_launcher))
                        .setDefaults(Notification.DEFAULT_ALL) // must requires VIBRATE permission
                        .setPriority(NotificationCompat.PRIORITY_HIGH); //must give priority to High, Max which will considered as heads-up notification

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                assert notificationManager != null;
                notificationManager.notify(0, builder.build());
                break;
        }
    }
}