package com.example.stefanie.ioudatabank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefanie on 25/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "IOU";
    private static final int DATABASE_VERSION = 1;
        public static final String TABLE_NAME = "IOUTable";
    public static final String COLUMN_ID="id";
        public static final String COLUMN_OWED_TO = "owned_to";
        public static final String COLUMN_OWED_BY = "owned_by";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_CONTEXT = "context";

    public static final String SQL_CREATE_ENTRIES = "create table "
            + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_AMOUNT + " double not null,"
            + COLUMN_OWED_TO + " text not null,"
            + COLUMN_OWED_BY + " text not null,"
            + COLUMN_CONTEXT + " text not null);";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public int deleteContract(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { id});
    }
//TODO
    public long addContract(ContentValues contract) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(TABLE_NAME, "", contract);
        if(id <=0 ) {
            throw new SQLException("Failed to add an image");
        }
        return id;
    }

    public Cursor getContracts(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
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
}
