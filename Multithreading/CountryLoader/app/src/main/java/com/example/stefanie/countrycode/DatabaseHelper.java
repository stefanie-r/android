package com.example.stefanie.countrycode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by stefanie on 11/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String TABLE_Name = "countries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DISPLAYNAME = "displayname";
    public static final String COLUMN_ISO2 = "iso2";
    public static final String COLUMN_ISO3 = "iso3";
    public static final String COLUMN_NUMCODE = "numcode";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "country_data";
    private static final String DATABASE_CREATE =
            "create table "
            + TABLE_Name + " (" +
            COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text, "
            + COLUMN_DISPLAYNAME + " text, "
            +COLUMN_ISO2 + " text, "
            +COLUMN_ISO3 + " text, "
            +COLUMN_NUMCODE + " text);";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            if (sqLiteDatabase.isOpen()) {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Name);
                sqLiteDatabase.execSQL(DATABASE_CREATE);
            }
        } catch (Exception e) {
            Log.d("onCreateDB", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Name);
        onCreate(sqLiteDatabase);
    }

    public Cursor getItems(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
        sqliteQueryBuilder.setTables(TABLE_Name);

        if(id != null) {
            sqliteQueryBuilder.appendWhere(COLUMN_ID + " = " + id);
        }

        if(sortOrder == null || sortOrder == "") {
            sortOrder = COLUMN_NAME;
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

    public long addItem(ContentValues values) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = getWritableDatabase().insert(TABLE_Name, "", values);
        if(id <=0 ) {
            throw new SQLException("Failed to add an image");
        }
        return id;
    }
}
