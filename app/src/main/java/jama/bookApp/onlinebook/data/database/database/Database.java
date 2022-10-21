package jama.bookApp.onlinebook.data.database.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import jama.bookApp.onlinebook.data.database.libs.DataBaseHelper;
import jama.bookApp.onlinebook.data.model.HazratModel;

public class Database {
    private static final String TAG = "TAG";
    @SuppressLint("StaticFieldLeak")
    private static Database database;
    private final Context mContext;
    private final DataBaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public Database(Context mContext) {
        this.mContext = mContext;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public static Database init(Context context) {
        if (database == null) {
            database = new Database(context);
        }
        return database;
    }

    public static Database getDatabase() {
        return database;
    }

    public Database createDatabase() {
        try {
            mDbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Database open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException);
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    @SuppressLint("Range")
    public ArrayList<HazratModel> getHazratAbout(){
        ArrayList<HazratModel> data =new ArrayList<>();
        Cursor cursor = mDb.rawQuery("select * from hazratim", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            data.add(new HazratModel(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("text"))));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }
}