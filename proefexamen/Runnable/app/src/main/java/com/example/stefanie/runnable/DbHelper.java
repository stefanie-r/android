package com.example.stefanie.runnable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

/**
 * Created by stefanie on 15/11/2016.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "runs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PLACE = "place";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DISTANCE = "distance";
    public static final String COLUMN_JOINED = "joined";
    public static final String COLUMN_ORGANISATION = "organisation";
    public static final String COLUMN_EVENTURL= "eventUrl";
    public static final String COLUMN_TITLE = "title";

    private static final String DATABASE_NAME = "IOU";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_PRICE + " float not null,"
            + COLUMN_PLACE + " text not null,"
            + COLUMN_DISTANCE + " integer not null"
            + COLUMN_DATE + " text not null,"
            + COLUMN_TITLE + " text not null,"
            + COLUMN_JOINED + " boolean not null"
            + COLUMN_EVENTURL + " text not null,"
            + COLUMN_ORGANISATION + " text not null);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            if (db.isOpen()) {
                db.execSQL(DATABASE_CREATE);
            }
        } catch (Exception e) {
            Log.e("onCreateDB", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Cursor getRuns(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
        sqliteQueryBuilder.setTables(TABLE_NAME);

        if(id != null) {
            sqliteQueryBuilder.appendWhere(COLUMN_ID + " = " + id);
        }

        if(sortOrder == null || sortOrder == "") {
            sortOrder = COLUMN_ID;
        }
        Cursor cursor = sqliteQueryBuilder.query(getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        return cursor;
    }
    public long addRun(ContentValues run) throws java.sql.SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(TABLE_NAME, "", run);
        if(id <=0 ) {
            throw new java.sql.SQLException("Failed to add an image");
        }
        return id;
    }

    public int deleteRun(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] {id});
    }
   }

