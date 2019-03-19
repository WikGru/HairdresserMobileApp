package com.example.grudzina_bakowski.Fryzjer.Entity;

public class Visit {
    public static final String TABLE_NAME = "Visit";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIMEBEG = "timeBeg";
    public static final String COLUMN_TIMEEND = "timeEnd";
    public static final String COLUMN_SERVICE = "service";


    private int id;
    private String name;
    private String date;
    private String timeBeg;
    private String timeEnd;
    private String service;

    public static final String CREATE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " VARCHAR(50),"
            + COLUMN_DATE + " VARCHAR(50),"
            + COLUMN_TIMEBEG + " VARCHAR(50),"
            + COLUMN_TIMEEND + " VARCHAR(50),"
            + COLUMN_SERVICE + " VARCHAR(50)"
            + ")";


    public Visit(int id, String service, String date, String timeBeg, String timeEnd, String name) {
        this.id = id;
        this.service = service;
        this.date = date;
        this.timeBeg = timeBeg;
        this.timeEnd = timeEnd;
        this.name = name;
    }

    public Visit() {
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public static String getColumnName() {
        return COLUMN_NAME;
    }

    public static String getColumnDate() {
        return COLUMN_DATE;
    }

    public static String getColumnTimebeg() {
        return COLUMN_TIMEBEG;
    }

    public static String getColumnService() {
        return COLUMN_SERVICE;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeBeg() {
        return timeBeg;
    }

    public void setTimeBeg(String timeBeg) {
        this.timeBeg = timeBeg;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }


}


