package com.example.grudzina_bakowski.Fryzjer.SQLITE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.grudzina_bakowski.Fryzjer.Entity.MojeWizyty;
import com.example.grudzina_bakowski.Fryzjer.Entity.Price;

import com.example.grudzina_bakowski.Fryzjer.Entity.User;
import com.example.grudzina_bakowski.Fryzjer.Entity.Visit;

import java.util.ArrayList;
import java.util.List;

public class SQLiteCRUD {
    SQLiteHandler database;

    public SQLiteCRUD(Context context) {
        database = new SQLiteHandler(context);
    }

    //clears Price
    public void clearPriceListTables() {
        SQLiteDatabase db = database.getWritableDatabase();
        try {
            db.delete(Price.TABLE_NAME, null, null);
            db.delete(Visit.TABLE_NAME, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    public void InsertMojeWizyty(String SERVICE, String DATE, String TIME){
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MojeWizyty.COLUMN_SERVICE_NAME, SERVICE);
        values.put(MojeWizyty.COLUMN_DATE, DATE);
        values.put(MojeWizyty.COLUMN_TIME, TIME);
        db.insert(MojeWizyty.TABLE_NAME, null, values);

        db.close();
    }

    public void InsertPrice(String SERVICE, String PRICE_SHORT, String PRICE_MEDIUM, String PRICE_LONG, String TIME) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Price.COLUMN_SERVICE_NAME, SERVICE);
        values.put(Price.COLUMN_TYPE_SHORT, PRICE_SHORT);
        values.put(Price.COLUMN_TYPE_MEDIUM, PRICE_MEDIUM);
        values.put(Price.COLUMN_TYPE_LONG, PRICE_LONG);
        values.put(Price.COLUMN_TIME, TIME);
        db.insert(Price.TABLE_NAME, null, values);

        db.close();
    }

    public void InsertVisit(String SERVICE, String DATE, String TIMEBEG, String TIMEEND, String NAME) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Visit.COLUMN_SERVICE, SERVICE);
        values.put(Visit.COLUMN_DATE, DATE);
        values.put(Visit.COLUMN_TIMEBEG, TIMEBEG);
        values.put(Visit.COLUMN_TIMEEND, TIMEEND);
        values.put(Visit.COLUMN_NAME, NAME);
        db.insert(Visit.TABLE_NAME, null, values);

        db.close();
    }

    public void SetUser(String USER) {
        //operates only on one row
        //user can be 'user' or 'admin' not both at the same time
        //therefore before adding new row, clear entity content

        // get writable database as we want to write data
        SQLiteDatabase db = database.getWritableDatabase();

//        try {
        db.delete(User.TABLE_NAME, null, null);
//        } catch (Exception e) {
//        }

        //User
        ContentValues value = new ContentValues();

        value.put(User.COLUMN_USER, USER);
        db.insert(User.TABLE_NAME, null, value);

        db.close();
    }

    public Price getPrice(String SERVICE) {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.query(Price.TABLE_NAME,
                new String[]{
                        Price.COLUMN_ID,
                        Price.COLUMN_SERVICE_NAME,
                        Price.COLUMN_TYPE_SHORT,
                        Price.COLUMN_TYPE_MEDIUM,
                        Price.COLUMN_TYPE_LONG,
                        Price.COLUMN_TIME
                },
                Price.COLUMN_SERVICE_NAME + "=?",
                new String[]{SERVICE}, null, null, null, null);

        if (cursor != null)
            cursor.moveToNext();

        // prepare Price object
        Price price = new Price(
                cursor.getInt(cursor.getColumnIndex(Price.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Price.COLUMN_SERVICE_NAME)),
                cursor.getString(cursor.getColumnIndex(Price.COLUMN_TYPE_SHORT)),
                cursor.getString(cursor.getColumnIndex(Price.COLUMN_TYPE_MEDIUM)),
                cursor.getString(cursor.getColumnIndex(Price.COLUMN_TYPE_LONG)),
                cursor.getString(cursor.getColumnIndex(Price.COLUMN_TIME)));

        // close the db connection
        cursor.close();

        return price;
    }

    public User getUser() {
        SQLiteDatabase db = database.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + User.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        // prepare User object
        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_USER)));
        // close the db connection
        cursor.close();
        return user;
    }

    public List<Price> getAllPrices() {
        List<Price> Prices = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + Price.TABLE_NAME;

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Price price = new Price();
                price.setId(cursor.getInt(cursor.getColumnIndex(Price.COLUMN_ID)));
                price.setServiceName(cursor.getString(cursor.getColumnIndex(Price.COLUMN_SERVICE_NAME)));
                price.sethShort(cursor.getString(cursor.getColumnIndex(Price.COLUMN_TYPE_SHORT)));
                price.sethMedium(cursor.getString(cursor.getColumnIndex(Price.COLUMN_TYPE_MEDIUM)));
                price.sethLong(cursor.getString(cursor.getColumnIndex(Price.COLUMN_TYPE_LONG)));
                price.setTime(cursor.getString(cursor.getColumnIndex(Price.COLUMN_TIME)));

                Prices.add(price);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return Prices list
        return Prices;
    }

    public List<MojeWizyty> getAllMojeWizyty(){
        List<MojeWizyty> visitList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MojeWizyty.TABLE_NAME;

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    MojeWizyty myVisit = new MojeWizyty();
                    myVisit.setId(cursor.getInt(cursor.getColumnIndex(MojeWizyty.COLUMN_ID)));
                    myVisit.setService(cursor.getString(cursor.getColumnIndex(MojeWizyty.COLUMN_SERVICE_NAME)));
                    myVisit.setDate(cursor.getString(cursor.getColumnIndex(MojeWizyty.COLUMN_DATE)));
                    myVisit.setTime(cursor.getString(cursor.getColumnIndex(MojeWizyty.COLUMN_TIME)));

                    visitList.add(myVisit);
                } while (cursor.moveToNext());
            }
        // close db connection
        db.close();

        return visitList;
    }

    public List<Visit> getAllVisits() {
        List<Visit> visitsList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Visit.TABLE_NAME;

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Visit visit = new Visit();
                visit.setId(cursor.getInt(cursor.getColumnIndex(Visit.COLUMN_ID)));
                visit.setService(cursor.getString(cursor.getColumnIndex(Visit.COLUMN_SERVICE)));
                visit.setDate(cursor.getString(cursor.getColumnIndex(Visit.COLUMN_DATE)));
                visit.setTimeBeg(cursor.getString(cursor.getColumnIndex(Visit.COLUMN_TIMEBEG)));
                visit.setTimeEnd(cursor.getString(cursor.getColumnIndex(Visit.COLUMN_TIMEEND)));
                visit.setName(cursor.getString(cursor.getColumnIndex(Visit.COLUMN_NAME)));

                visitsList.add(visit);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return Prices list
        return visitsList;
    }
}

class SQLiteHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "Cennik.db";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MojeWizyty.CREATE_IF_NOT_EXIST);
        db.execSQL(Price.CREATE_IF_NOT_EXIST);
        db.execSQL(Visit.CREATE_IF_NOT_EXIST);
        db.execSQL(User.CREATE_IF_NOT_EXIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MojeWizyty.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Price.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Visit.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);
    }
}
