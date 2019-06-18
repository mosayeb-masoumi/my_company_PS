package com.rayanandisheh.peysepar.passenger.activities.activation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "###SMSReceiver";

    private OnSmsReceivedListener listener;

    public SmsReceiver(@NonNull OnSmsReceivedListener listener){
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                if (pdusObj != null) {
                    for (Object aPdusObj : pdusObj) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                        String senderAddress = currentMessage.getDisplayOriginatingAddress();
                        String message = currentMessage.getDisplayMessageBody();

                        Log.v(TAG, "Received SMS: " + message + ", Sender: " + senderAddress);

                        // Ensure the SMS is for your app by checking the sender
                        // if (!senderAddress.toUpperCase().contains("MYAPP")) {
                        //    return;
                        // }

                        // get the verification code from the SMS
                        String verificationCode = getVerificationCode(message);
                        listener.onSmsReceived(verificationCode);
                        if (verificationCode != null) {
                            Log.v(TAG, "Verification Code: " + verificationCode);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Looks for a 6 digit verification code and returns it if found
     * @param message The sms message to get parse the code from
     * @return Verification Code if found, else returns null
     */
    private String getVerificationCode(String message) {
        Pattern pattern = Pattern.compile("([\\d]{6})");
        Matcher m = pattern.matcher(message);
        if(m.find()){
            return m.group(1);
        }
        return null;
    }

    public interface OnSmsReceivedListener {
        /**
         * Called when an SMS is received from APP_NAME
         * @param code The verification code (null if the code was not found)
         */
        void onSmsReceived(String code);
    }
}
