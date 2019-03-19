package com.example.grudzina_bakowski.Fryzjer.Entity;


public class User {
    private int id;
    private String user;

    public static final String TABLE_NAME = "User";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER = "user";

    public static final String CREATE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER + " VARCHAR(50)"
            + ")";

    public User(int id, String user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
