package com.rayanandisheh.peysepar.passenger.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rayanandisheh.peysepar.passenger.models.Locations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static String SDK_DIR = "";
    public static String APP_DIR = "";
    public static SQLiteDatabase database;


    public Database(Context context) {
        SDK_DIR = context.getFilesDir().getAbsolutePath();
        //SDK_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
        APP_DIR = SDK_DIR + "/Andisheh/Radyab/";
        if (database == null) {
            DBCreate(context);
        }
    }

    public void DBCreate(Context context) {
        try {
            String filename = "rs_db.db3";

            File file = new File(APP_DIR);
            if (!file.exists()) {
                try {
                    file.mkdirs();
                    file.createNewFile();
                    copyFromAssets(context.getAssets().open(filename),
                            new FileOutputStream(APP_DIR + "/" + filename));

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

            database = SQLiteDatabase.openOrCreateDatabase(APP_DIR + "/" + filename, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS gps " +
                    "(id INTEGER PRIMARY KEY NOT NULL,strIMEI TEXT,lat TEXT ,lon TEXT,viSpeed TEXT" +
                    ",steps TEXT,Accurecy TEXT,LogDate TEXT,LogTime TEXT,batteryLevel TEXT)");

        } catch (Exception e) {
            Log.i("LOG", e.toString());
        }
    }


    public void copyFromAssets(InputStream inputStream, OutputStream outputStream) {
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveData(Context context, Locations location) {
        if (database == null) {
            new Database(context);
        }

        database.execSQL("CREATE TABLE IF NOT EXISTS gps " +
                "(id INTEGER PRIMARY KEY NOT NULL,strIMEI TEXT,lat TEXT ,lon TEXT,viSpeed TEXT" +
                ",steps TEXT,Accurecy TEXT,LogDate TEXT,LogTime TEXT,batteryLevel TEXT)");

        ContentValues cv = new ContentValues();
        cv.put("strIMEI", location.getStrIMEI());
        cv.put("lat", location.getLat());
        cv.put("lon", location.getLon());
        cv.put("viSpeed", location.getViSpeed());
        cv.put("steps", location.getSteps());
        cv.put("Accurecy", location.getAccuracy());
        cv.put("LogDate", location.getLogDate());
        cv.put("LogTime", location.getLogTime());
        cv.put("batteryLevel", location.getBatteryLevel());
        database.insert("gps", null, cv);
    }


    public List<Locations> openData(Context context) {
        if (database == null) {
            new Database(context);
        }
        List<Locations> locations = new ArrayList<>();
        Cursor cursor = Database.database.rawQuery("SELECT * FROM 'gps'", null);
        if (cursor.getCount() > 10) {
            for (int i = 0; i <= 10; i++) {
                Locations location = new Locations();
                cursor.moveToNext();
                location.setId(cursor.getInt(cursor.getColumnIndex("id")));
                location.setStrIMEI(cursor.getString(cursor.getColumnIndex("strIMEI")));
                location.setLat(cursor.getDouble(cursor.getColumnIndex("lat")));
                location.setLon(cursor.getDouble(cursor.getColumnIndex("lon")));
                location.setViSpeed(cursor.getString(cursor.getColumnIndex("viSpeed")));
                location.setSteps(cursor.getString(cursor.getColumnIndex("steps")));
                location.setAccuracy(cursor.getString(cursor.getColumnIndex("Accurecy")));
                location.setLogDate(cursor.getString(cursor.getColumnIndex("LogDate")));
                location.setLogTime(cursor.getString(cursor.getColumnIndex("LogTime")));
                location.setBatteryLevel(cursor.getString(cursor.getColumnIndex("batteryLevel")));
                locations.add(location);
            }
            return locations;
        } else {
            return null;
        }

    }

    public List<Locations> openData(Context context, String loc) {
        if (database == null) {
            new Database(context);
        }
        List<Locations> lstLocations = new ArrayList<>();
        Cursor cursor = Database.database.rawQuery("SELECT * FROM 'gps'", null);
        int dataCount = cursor.getCount();
        if (dataCount > 10) {
            dataCount = 10;
        }
        if (dataCount > 0) {
            for (int i = 0; i <= dataCount; i++) {
                Locations locations = new Locations();
                try {
                    cursor.moveToNext();
                    locations.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    locations.setStrIMEI(cursor.getString(cursor.getColumnIndex("strIMEI")));
                    locations.setLat(Double.parseDouble(cursor.getString(cursor.getColumnIndex("lat"))));
                    locations.setLon(Double.parseDouble(cursor.getString(cursor.getColumnIndex("lon"))));
                    locations.setViSpeed(cursor.getString(cursor.getColumnIndex("viSpeed")));

                    locations.setSteps(cursor.getString(cursor.getColumnIndex("steps")));
                    locations.setAccuracy(cursor.getString(cursor.getColumnIndex("Accurecy")));
                    locations.setLogDate(cursor.getString(cursor.getColumnIndex("LogDate")));
                    locations.setLogTime(cursor.getString(cursor.getColumnIndex("LogTime")));
                    locations.setBatteryLevel(cursor.getString(cursor.getColumnIndex("batteryLevel")));
                    lstLocations.add(locations);
                } catch (Exception e) {
                    Log.i("", e.toString());
                }
            }
            return lstLocations;

        } else {
            return null;
        }

    }
}






