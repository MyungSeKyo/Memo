package com.segyo.memo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sekyo on 2017-12-31.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_FILE = "memo.db";
    public static DBHelper dbHelper = null;
    public static SQLiteDatabase db = null;

    public DBHelper(Context context){
        super(context, DB_FILE, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
    private static void initialize(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }
        db = dbHelper.getWritableDatabase();
    }
    public static final DBHelper getInstance(Context context){
        initialize(context);
        return dbHelper;
    }
    public void close(){
        if(dbHelper != null){
            db.close();
            dbHelper = null;
        }
    }

}
