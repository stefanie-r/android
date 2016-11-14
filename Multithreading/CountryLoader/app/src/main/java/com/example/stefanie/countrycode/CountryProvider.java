package com.example.stefanie.countrycode;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by stefanie on 11/11/2016.
 */

public class CountryProvider extends ContentProvider {
    private DatabaseHelper mOpenHelper;
    private static final String PROVIDER_NAME = "com.example.stefanie.countrycode.CountryProvider";//is authority
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/items");
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int COUNTRIES = 1;
    public static final int COUNTRY_ID = 2;

    static {
        uriMatcher.addURI(PROVIDER_NAME, "countries", COUNTRIES);
        uriMatcher.addURI(PROVIDER_NAME, "countries/#", COUNTRY_ID);};
    @Override
    public boolean onCreate() {
        Context context = this.getContext();
        mOpenHelper = new DatabaseHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = null;
        if (uriMatcher.match(uri) == COUNTRY_ID) {
            //Query is for one single image. Get the ID from the URI.
            id = uri.getPathSegments().get(1);
        }
        return mOpenHelper.getItems(id, projection, selection, selectionArgs, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            long id = mOpenHelper.addItem(values);
            Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
            return returnUri;
        } catch(Exception e) {
            Log.e("CountryProvider", "Add item failed. " + e.getMessage());
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
