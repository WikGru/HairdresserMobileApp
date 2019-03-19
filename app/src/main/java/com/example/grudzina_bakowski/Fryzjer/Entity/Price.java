package com.example.grudzina_bakowski.Fryzjer.Entity;


public class Price {
    public static final String TABLE_NAME = "Price";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SERVICE_NAME = "service";
    public static final String COLUMN_TYPE_SHORT = "krotkie";
    public static final String COLUMN_TYPE_MEDIUM = "srednie";
    public static final String COLUMN_TYPE_LONG = "dlugie";
    public static final String COLUMN_TIME = "czas";


    private int id;
    private String serviceName;
    private String hShort;
    private String hMedium;
    private String hLong;
    private String time;

    public static final String CREATE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SERVICE_NAME + " VARCHAR(50),"
            + COLUMN_TYPE_SHORT + " VARCHAR(50),"
            + COLUMN_TYPE_MEDIUM + " VARCHAR(50),"
            + COLUMN_TYPE_LONG + " VARCHAR(50),"
            + COLUMN_TIME + " VARCHAR(50)"
            + ")";

    public Price() {
    }

    public Price(int id, String serviceName, String hShort, String hMedium, String hLong, String time) {
        this.id = id;
        this.serviceName = serviceName;
        this.hShort = hShort;
        this.hMedium = hMedium;
        this.hLong = hLong;
        this.time = time;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String gethShort() {
        return hShort;
    }

    public void sethShort(String hShort) {
        this.hShort = hShort;
    }

    public String gethMedium() {
        return hMedium;
    }

    public void sethMedium(String hMedium) {
        this.hMedium = hMedium;
    }

    public String gethLong() {
        return hLong;
    }

    public void sethLong(String hLong) {
        this.hLong = hLong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
