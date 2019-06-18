package com.rayanandisheh.peysepar.passenger.helpers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Converter {

    public static String Encoder(String forConvert) {
        switch (App.encodingFormat) {
            case "base64":
                return Base64.encodeToString(forConvert.getBytes(), Base64.DEFAULT);
            case "md5":
                MessageDigest mdEnc;
                try {
                    mdEnc = MessageDigest.getInstance("MD5");
                    mdEnc.update(forConvert.getBytes(), 0, forConvert.length());
                    StringBuilder sUnMD5Builder = new StringBuilder(new BigInteger(1, mdEnc.digest()).toString(16));
                    while (sUnMD5Builder.length() < 16)
                        sUnMD5Builder.insert(0, "0");
                    return sUnMD5Builder.toString();
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                    return forConvert;
                }
            default:
                return forConvert;
        }
    }


    public static String bitmapToString(Bitmap bitmap) {
        if(bitmap!=null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte [] b=baos.toByteArray();
            return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        }else{
            return "";
        }
    }


    public static BitmapDescriptor drawableToBitmap(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}