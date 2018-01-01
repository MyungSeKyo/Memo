package com.segyo.memo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sekyo on 2018-01-02.
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_FILE, null, DB_VERSION) {

    val memos: ArrayList<ListViewItem>
        get() {
            val SQL_SELECT_ALL = "SELECT * FROM MEMO"
            val listViewItems = ArrayList<ListViewItem>()
            val cursor = db!!.rawQuery(SQL_SELECT_ALL, null)

            cursor.moveToLast()
            while (cursor.moveToPrevious()) {
                val listViewItem = ListViewItem()
                listViewItem.time = cursor.getString(cursor.getColumnIndex("TIME"))
                listViewItem.content = cursor.getString(cursor.getColumnIndex("CONTENT"))
                listViewItems.add(listViewItem)
            }

            return listViewItems
        }

    fun deleteMemo(time: String) {
        val SQL_DELETE = "DELETE FROM MEMO WHERE TIME = '$time'"
        db!!.execSQL(SQL_DELETE)
    }

    fun updateMemo(time: String, content: String) {
        val SQL_UPDATE = "UPDATE MEMO SET CONTENT = '$content' WHERE TIME = '$time'"
        db!!.execSQL(SQL_UPDATE)
    }

    fun insertMemo(content: String) {
        val date = Date()
        val simpleDateFormat = SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss a")

        val SQL_INSERT = "INSERT INTO MEMO VALUES ('" + simpleDateFormat.format(date).toString() + "','" + content + "')"

        db!!.execSQL(SQL_INSERT)
    }

    fun resetDB() {
        val SQL_TABLE_DROP = "DROP TABLE IF EXISTS MEMO"
        db!!.execSQL(SQL_TABLE_DROP)
    }

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_TABLE = ("CREATE TABLE IF NOT EXISTS MEMO "
                + "("
                + "TIME varchar(25) NOT NULL, "
                + "CONTENT TEXT NOT NULL, "
                + "PRIMARY KEY (TIME) "
                + ")")
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    override fun close() {
        if (dbHelper != null) {
            db!!.close()
            dbHelper = null
        }
    }

    companion object {
        val DB_VERSION = 1
        val DB_FILE = "memo.db"
        var dbHelper: DBHelper? = null
        var db: SQLiteDatabase? = null

        // for single tone
        private fun initialize(context: Context) {
            if (dbHelper == null) {
                dbHelper = DBHelper(context)
                db = dbHelper!!.writableDatabase
            }
        }

        fun getInstance(context: Context): DBHelper? {
            initialize(context)
            return dbHelper
        }
    }
}