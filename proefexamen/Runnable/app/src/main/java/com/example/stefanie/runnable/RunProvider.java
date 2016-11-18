package com.example.stefanie.runnable;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by stefanie on 15/11/2016.
 */

public class RunProvider extends ContentProvider {
    private DbHelper mOpenHelper;

    private static final String PROVIDER_NAME =  "run.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/runs");
    private static final int RUNS = 1;
    private static final int RUN_ID = 2;
    private final UriMatcher uriMatcher = getUriMatcher();
    private UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "runs", RUNS);
        uriMatcher.addURI(PROVIDER_NAME, "runs/#", RUN_ID);
        return uriMatcher;
    }
    @Override
    public boolean onCreate() {
        mOpenHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = null;
        if(uriMatcher.match(uri) == RUN_ID) {
            //Query is for one single image. Get the ID from the URI.
            id = uri.getPathSegments().get(1);
        }
        return mOpenHelper.getRuns(id, projection, selection, selectionArgs, sortOrder);

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        try {
            long id = mOpenHelper.addRun(contentValues);
            Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
            return returnUri;
        } catch (Exception e) {
            Log.e("RunProvider", "Insert failed: " + e.getMessage());
            return null;
        }
    }
    public Uri alterJoinedJoined(Uri uri, ContentValues contentValues) {
        String s = null;
        String[] strings = null;
        delete(uri, s,strings);
        Uri uriReturn = insert(uri, contentValues);
        return uriReturn;
    }
    @Override
    public int delete(Uri uri, String s, String[] strings) {
        String id = null;
        if (uriMatcher.match(uri) == RUN_ID) {
            id = uri.getPathSegments().get(1);
        }
        return mOpenHelper.deleteRun(id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
