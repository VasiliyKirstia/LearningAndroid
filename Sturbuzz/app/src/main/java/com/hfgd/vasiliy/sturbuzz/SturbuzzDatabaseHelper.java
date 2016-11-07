package com.hfgd.vasiliy.sturbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vasiliy on 11/7/16.
 */

public class SturbuzzDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "sturbuzz";
    public static final int DB_VERSION = 2;

    public SturbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static void insertDrink(SQLiteDatabase db, String name, String description, int resourceId){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("image_resource_id", resourceId);
        db.insert("drink", null, contentValues);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion < 1){
            db.execSQL(
                    "create database drink (" +
                            "_id integer primary key autoincrement," +
                            "name text," +
                            "description text," +
                            "image_resource_id integer" +
                            ");"
            );

            insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
            insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);
        }
        if(oldVersion < 2){
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }

    }
}
