package com.example.stefanie.ioudatabank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefanie on 25/10/2016.
 */

public class Helper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "contracts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_OWEDTO = "owedto";
    public static final String COLUMN_OWEDBY = "owedby";
    public static final String COLUMN_CONTEXT = "context";

    private static final String DATABASE_NAME = "IOU";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_AMOUNT + " double not null,"
            + COLUMN_OWEDTO + " text not null,"
            + COLUMN_OWEDBY + " text not null,"
            + COLUMN_CONTEXT + " text not null);";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /*
        public void createDatabase() {
            try {
                if (getReadableDatabase().isOpen()) {
                    getReadableDatabase().execSQL(DATABASE_CREATE);
                }
            } catch (Exception e) {
                Log.d("onCreateDB", e.getMessage());
            }
        }
    */
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
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
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

    public long addContract(ContentValues contract) throws java.sql.SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues v = new ContentValues();
        //v.put(COLUMN_AMOUNT, contract.getAmount());
        //v.put(COLUMN_OWEDTO, contract.getOwedTo());
        //v.put(COLUMN_OWEDBY, contract.getOwedBy());
        //v.put(COLUMN_CONTEXT, contract.getContext());
        long id = db.insert(TABLE_NAME, "", contract);
        if(id <=0 ) {
            throw new java.sql.SQLException("Failed to add an image");
        }

        return id;
    }

    public int deleteContract(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] {id});
    }
}
