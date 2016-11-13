package com.example.stefanie.ioudatabank;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by stefanie on 13/11/2016.
 */

public class ContractProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.stefanie.ioudatabank";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contracts");
    private static final int CONTRACTS = 1;
    private static final int CONTRACT_ID = 2;
    private final UriMatcher uriMatcher = getUriMatcher();
    private UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "contracts", CONTRACTS);
        uriMatcher.addURI(AUTHORITY, "contracts/#", CONTRACT_ID);
        return uriMatcher;
    }

    private DatabaseHelper helper;

    @Override
    public boolean onCreate() {
        helper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = null;
        if(uriMatcher.match(uri) == CONTRACT_ID) {
            id = uri.getPathSegments().get(1);
        }
        return helper.getContracts(id, projection, selection, selectionArgs, sortOrder);
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
            long id = helper.addContract(values);
            Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
            return returnUri;
        } catch (Exception e) {
            Log.e("ContractProvider", "Insert failed: " + e.getMessage());
            return null;
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String id = null;
        if (uriMatcher.match(uri) == CONTRACT_ID) {
            id = uri.getPathSegments().get(1);
        }
        return helper.deleteContract(id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
