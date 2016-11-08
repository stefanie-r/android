package com.example.stefanie.condiments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by Stefanie on 24/10/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    public static final String TABLE_NAME = "condiments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_PRICE = "price";

    private static final String DATABASE_NAME = "condiments";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DESC + " text not null,"
            + COLUMN_PRICE + " double not null);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 /*   public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            if (db.isOpen()) {
                db.execSQL(DATABASE_CREATE);
            }
        } catch (Exception e) {
            Log.d("onCreateDB", e.getMessage());
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getItems(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
        sqliteQueryBuilder.setTables(TABLE_NAME);

        if(id != null) {
            sqliteQueryBuilder.appendWhere(COLUMN_ID + " = " + id);
        }

        if(sortOrder == null || sortOrder == "") {
            sortOrder = "desc";
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
        long id = getWritableDatabase().insert(TABLE_NAME, "", values);
        if(id <=0 ) {
            throw new SQLException("Failed to add an image");
        }

        return id;
    }
}
