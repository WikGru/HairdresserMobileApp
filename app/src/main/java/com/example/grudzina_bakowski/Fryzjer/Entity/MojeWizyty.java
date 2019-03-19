package com.example.grudzina_bakowski.Fryzjer.Entity;

public class MojeWizyty {
    public static final String TABLE_NAME = "MojeWizyty";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SERVICE_NAME = "service";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "czas";

    private int id;
    private String service;
    private String date;
    private String time;

    public static final String CREATE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SERVICE_NAME + " VARCHAR(50),"
            + COLUMN_DATE + " VARCHAR(50),"
            + COLUMN_TIME + " VARCHAR(50)"
            + ")";

    public MojeWizyty(){}

    public MojeWizyty(int id, String service, String date, String time){
        this.id = id;
        this.service = service;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
