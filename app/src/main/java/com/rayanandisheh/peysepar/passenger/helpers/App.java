package com.rayanandisheh.peysepar.passenger.helpers;

import android.app.Application;
import android.content.Context;
import cn.like.nightmodel.NightModelManager;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.maps.model.LatLng;
import com.rayanandisheh.peysepar.passenger.models.*;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    public static String ServerURL = "";
//    public static String ServerURL = "http://192.168.1.200:1000/Passenger/";
//    public static String ServerURL = "http://192.168.1.109:1000";
//    public static String ServerURL = "";
//      public static String ServerURL = "http://79.127.36.117:1000/Passenger/";

    public static Context context;
    public static String encodingFormat = "md5"; //used for encoding passwords in helpers/Converter/encoder
    public static User user = new User();
    public static double lastLat = 0;
    public static double lastLng = 0;
    public static boolean isRun = false;
    public static boolean historySuccess = true;
//    public static List<Trip> historyList = new ArrayList<>();

//    public static boolean modeChanged = false;
    public static boolean modeChanged = false;
    public static NotificationModel notifModel = new NotificationModel();


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        NightModelManager.getInstance().init(this);
        context = this;
    }






  //real

   public static Register register=new Register();
   public static UserInfo userInfo=new UserInfo();


    public static Integer pursuitCode=0;

    public static boolean historyTripSuccess = true;
    public static boolean currentTripSuccess = true;

    public static boolean newTabLayoutListSuccess = true;
    public static boolean confirmedTripTabLayoutListSuccess = true;
    public static boolean waitingDriverConfirmTabLayoutListSuccess = true;
    public static boolean RunningTabLayoutListSuccess = true;
    public static boolean CanceledTripTabLayoutListSuccess = true;
    public static boolean assignDriverListSuccess = true;


    public static List<Trip> listCurrentTrip = new ArrayList<>();
    public static List<Trip> listHistoryTrip = new ArrayList<>();


    /*------------------retated to managementTrip fragments-----------------*/
    public static List<Trip> listNewTabLayoutTrip = new ArrayList<>();
    public static List<Trip> listConfirmedTripTabLayout= new ArrayList<>();
    public static List<Trip> listWaitingDriverConfirmTabLayout= new ArrayList<>();
    public static List<Trip> listRunningTabLayout= new ArrayList<>();
    public static List<Trip> listCanceledTabLayout= new ArrayList<>();
    /*-----------------------------------------------------------------------*/



    /*------------------retated to TripManagementNew-----------------*/
    public static List<Trip> listNewTripManagementNew = new ArrayList<>();
    public static List<Trip> listConfirmedTripManagementNew= new ArrayList<>();
    public static List<Trip> listWaitingDriverConfirmTripManagementNew= new ArrayList<>();
    public static List<Trip> listRunningTripManagementNew= new ArrayList<>();
    public static List<Trip> listCanceledTripManagementNew= new ArrayList<>();
    /*-----------------------------------------------------------------------*/
    public static boolean newTripManagementSuccess = true;
    public static boolean confirmedTripManagementSuccess = true;
    public static boolean waitingDriverConfirmTripManagementSuccess = true;
    public static boolean RunningTripManagementSuccess = true;
    public static boolean CanceledTripManagementSuccess = true;
//    public static boolean assignDriverListSuccess = true;









    public static List<DriverInfo> listAssignDriverConfirmedTabLayout= new ArrayList<>();







    public static String selectedPositionName = "";
    public static LatLng selectedPosition = new LatLng(0,0);

    public static boolean map =false;

    public static String mobileFirstRunning="";

    public static boolean marker_existance = false;

    //to check autoCompleteTextView
    public static List<TripSAD> tripSAD = new ArrayList<>();

    public static Report report=new Report();


    public static Data data=new Data();
    public static AssignDriver assignDriver=new AssignDriver();


    public static Score score=new Score();


    public static String dateFromTripManagement="";
    public static String dateToTripManagement="";


    public static boolean diffrentIMEI = false;
    public static String formerMobilePhoneDiffrentIMEI="";
    public static boolean formerMobileDiffrentIMEI = false;


    public static Replace replace=new Replace();

    public static String mobileNumber_inActiveUser="";

    public static Dashboard dashboard=new Dashboard();

    public static boolean dateRengeTripManagement = false;

}