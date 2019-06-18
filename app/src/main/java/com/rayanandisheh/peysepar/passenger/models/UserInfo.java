package com.rayanandisheh.peysepar.passenger.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    @SerializedName("strName")
    private String strName;
    @SerializedName("strToken")
    private String strToken;
    @SerializedName("strFamily")
    private String strFamily;
    @SerializedName("strMobile")
    private String strMobile;
    @SerializedName("tiSex")
    private short tiSex;
    @SerializedName("Result")
    private int Result;
    @SerializedName("Type")
    private int Type;
    @SerializedName("strIMEI")
    private String strIMEI;
    @SerializedName("TripImportance")
    private List<TripImportance> TripImportance = new ArrayList<>();
    @SerializedName("TripReason")
    private List<TripReason> TripReason = new ArrayList<>();
    @SerializedName("TripSAD")
    private List<TripSAD> TripSAD=new ArrayList<>();
    @SerializedName("MobileType")
    private List<MobileType> MobileType =new ArrayList<>();
    @SerializedName("DateFrom")
    private String DateFrom ;
    @SerializedName("DateTo")
    private String DateTo ;
    @SerializedName("VersionCode")
    private int VersionCode ;
    @SerializedName("VersionCodeMin")
    private int VersionCodeMin ;
    @SerializedName("VersionName")
    private String VersionName ;
    @SerializedName("DownloadLinkApp")
    private String DownloadLinkApp ;
    @SerializedName("Url")
    private String Url ;
    @SerializedName("ImgLink")
     private String ImgLink;
    @SerializedName("fLat")
      private float fLat;
    @SerializedName("fLon")
     private float fLon;
    @SerializedName("NationalCode")
     private  String NationalCode;
    @SerializedName("iofficialtrip")
    private  int iofficialtrip;
    @SerializedName("strChart")
    private String strChart ;
    @SerializedName("strComment")
    public String strComment ;
    @SerializedName("Dashboard")
    private int Dashboard;
    @SerializedName("iMobileDomain")
    private int iMobileDomain ;
    @SerializedName("strUrlHelp")
    private String strUrlHelp ;

    public String getStrUrlHelp() {
        return strUrlHelp;
    }

    public void setStrUrlHelp(String strUrlHelp) {
        this.strUrlHelp = strUrlHelp;
    }

    public int getiMobileDomain() {
        return iMobileDomain;
    }

    public void setiMobileDomain(int iMobileDomain) {
        this.iMobileDomain = iMobileDomain;
    }

    public int getDashboard() {
        return Dashboard;
    }

    public void setDashboard(int dashboard) {
        Dashboard = dashboard;
    }

    public List<com.rayanandisheh.peysepar.passenger.models.MobileType> getMobileType() {
        return MobileType;
    }

    public void setMobileType(List<com.rayanandisheh.peysepar.passenger.models.MobileType> mobileType) {
        MobileType = mobileType;
    }

    public String getStrComment() {
        return strComment;
    }

    public void setStrComment(String strComment) {
        this.strComment = strComment;
    }

    public String getStrChart() {
        return strChart;
    }

    public void setStrChart(String strChart) {
        this.strChart = strChart;
    }

    public int getIofficialtrip() {
        return iofficialtrip;
    }

    public void setIofficialtrip(int iofficialtrip) {
        this.iofficialtrip = iofficialtrip;
    }

    public String getNationalCode() {
        return NationalCode;
    }

    public void setNationalCode(String nationalCode) {
        NationalCode = nationalCode;
    }

    public float getfLat() {
        return fLat;
    }
    public void setfLat(float fLat) {
        this.fLat = fLat;
    }

    public float getfLon() {
        return fLon;
    }

    public void setfLon(float fLon) {
        this.fLon = fLon;
    }

    public String getImgLink() {
        return ImgLink;
    }

    public void setImgLink(String imgLink) {
        ImgLink = imgLink;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDownloadLinkApp() {
        return DownloadLinkApp;
    }

    public void setDownloadLinkApp(String downloadLinkApp) {
        DownloadLinkApp = downloadLinkApp;
    }

    public int getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(int versionCode) {
        VersionCode = versionCode;
    }

    public int getVersionCodeMin() {
        return VersionCodeMin;
    }

    public void setVersionCodeMin(int versionCodeMin) {
        VersionCodeMin = versionCodeMin;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }

    public List<com.rayanandisheh.peysepar.passenger.models.TripSAD> getTripSAD() {
        return TripSAD;
    }

    public void setTripSAD(List<com.rayanandisheh.peysepar.passenger.models.TripSAD> tripSAD) {
        TripSAD = tripSAD;
    }

    public String getStrIMEI() {
        return strIMEI;
    }

    public void setStrIMEI(String strIMEI) {
        this.strIMEI = strIMEI;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrToken() {
        return strToken;
    }

    public void setStrToken(String strToken) {
        this.strToken = strToken;
    }

    public String getStrFamily() {
        return strFamily;
    }

    public void setStrFamily(String strFamily) {
        this.strFamily = strFamily;
    }

    public String getStrMobile() {
        return strMobile;
    }

    public void setStrMobile(String strMobile) {
        this.strMobile = strMobile;
    }

    public short getTiSex() {
        return tiSex;
    }

    public void setTiSex(short tiSex) {
        this.tiSex = tiSex;
    }

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public List<com.rayanandisheh.peysepar.passenger.models.TripImportance> getTripImportance() {
        return TripImportance;
    }

    public void setTripImportance(List<com.rayanandisheh.peysepar.passenger.models.TripImportance> tripImportance) {
        TripImportance = tripImportance;
    }

    public List<com.rayanandisheh.peysepar.passenger.models.TripReason> getTripReason() {
        return TripReason;
    }

    public void setTripReason(List<com.rayanandisheh.peysepar.passenger.models.TripReason> tripReason) {
        TripReason = tripReason;
    }
}
