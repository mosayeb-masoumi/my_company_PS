package com.rayanandisheh.peysepar.passenger.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

public class DeviceInfo {
    public static String getDeviceIMEI(Context context) {

        String deviceUniqueIdentifier = null;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, 3);
            else
                deviceUniqueIdentifier = tm.getDeviceId();
            if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length())
                deviceUniqueIdentifier = "0";
        }
        return deviceUniqueIdentifier;

    }
}
