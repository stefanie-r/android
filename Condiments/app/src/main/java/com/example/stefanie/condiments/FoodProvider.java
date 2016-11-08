package com.example.stefanie.condiments;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Stefanie on 24/10/2016.
 */

public class FoodProvider extends ContentProvider {
  private DataBaseHelper mOpenHelper;
    private SQLiteDatabase db;

    private static final String PROVIDER_NAME = "com.example.stefanie.condiments.FoodProvider";//is authority
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/items");
    private static final int ITEMS = 1;
    private static final int ITEM_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(PROVIDER_NAME, "items", ITEMS);
        uriMatcher.addURI(PROVIDER_NAME, "items/#", ITEM_ID);
    }

    @Override
    public boolean onCreate() {
        Context context = this.getContext();
        mOpenHelper = new DataBaseHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = null;
        if (uriMatcher.match(uri) == ITEM_ID) {
            //Query is for one single image. Get the ID from the URI.
            id = uri.getPathSegments().get(1);
        }
        return mOpenHelper.getItems(id, projection, selection, selectionArgs, sortOrder);
    }

        @Nullable
    @Override
    public String getType(Uri uri) {
        return "";
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            long id = mOpenHelper.addItem(values);
            Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
            return returnUri;
        } catch(Exception e) {
            Log.e("CondimentsProvider", "Add item failed. " + e.getMessage());
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
        }
}


