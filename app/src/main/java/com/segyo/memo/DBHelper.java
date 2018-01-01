package com.segyo.memo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public ArrayList<ListViewItem> getAllMemo(){
        final String SQL_SELECT_ALL = "SELECT * FROM MEMO";
        ArrayList<ListViewItem> listViewItems = new ArrayList<ListViewItem>();
        Cursor cursor = db.rawQuery(SQL_SELECT_ALL, null);

        while (cursor.moveToNext()){
            ListViewItem listViewItem = new ListViewItem();
            listViewItem.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
            listViewItem.setContent(cursor.getString(cursor.getColumnIndex("CONTENT")));
            listViewItems.add(listViewItem);
        }

        return listViewItems;
    }

    public void deleteMemo(String time){
        final String SQL_DELETE = "DELETE FROM MEMO WHERE TIME = '" + time + "'";
    }

    public void updateMemo(String time, String content){
        final String SQL_UPDATE = "UPDATE MEMO SET CONTENT = '" + content + "' WHERE TIME = '" + time + "'";
        db.execSQL(SQL_UPDATE);
    }

    public void insertMemo(String content){
        long time = System.currentTimeMillis();

        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy년mm월dd일 hh:mm:ss");

        String str = dayTime.format(new Date(time));

        final String SQL_INSERT = "INSERT INTO MEMO VALUES ('" + str + "','"+ content + "')";



        db.execSQL(SQL_INSERT);
    }

    public void resetDB(){
        final String SQL_TABLE_DROP = "DROP TABLE IF EXISTS MEMO";
        db.execSQL(SQL_TABLE_DROP);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS MEMO "
                + "("
                + "TIME varchar(25) NOT NULL, "
                + "CONTENT TEXT NOT NULL, "
                + "PRIMARY KEY (TIME) "
                + ")";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    // for single tone
    private static void initialize(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
            db = dbHelper.getWritableDatabase();
        }
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
