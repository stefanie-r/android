package com.example.stefanie.ioudatabank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

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

    // Adding new contact
    public void addContact(DatabaseContract contract) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_OWED_BY, contract.getOwedBy());
        values.put(COLUMN_OWED_TO, contract.getOwedTo());
        values.put(COLUMN_AMOUNT, contract.getAmount());
        values.put(COLUMN_CONTEXT, contract.getContext());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Getting single contact
    public DatabaseContract getContract(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                COLUMN_ID, COLUMN_AMOUNT, COLUMN_OWED_TO,
                COLUMN_OWED_BY, COLUMN_CONTEXT}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null,null);
        if (cursor!= null){
            cursor.moveToFirst();}
            DatabaseContract contract = new DatabaseContract(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        return contract;
    }

    // Getting All Contacts
    public List<DatabaseContract> getAllContracts() {
        List<DatabaseContract> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseContract contract = new DatabaseContract();
                contract.setId(Integer.parseInt(cursor.getString(0)));
                contract.setAmount(Integer.parseInt(cursor.getString(1)));
                contract.setOwedTo(cursor.getString(2));
                contract.setOwedBy(cursor.getString(3));
                contract.setContext(cursor.getString(4));
                // Adding contact to list
                contactList.add(contract);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

   // Deleting single contact
    public void deleteContact(DatabaseContract contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
}
