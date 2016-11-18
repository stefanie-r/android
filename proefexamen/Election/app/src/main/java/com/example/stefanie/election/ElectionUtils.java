package com.example.stefanie.election;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stilkin on 4/11/15.
 * Do not forget to add 'com.google.code.gson:gson:2.4' to your dependencies!
 * Do not forget to add the Strings to your strings.xml file
 */
public class ElectionUtils {
    public static final String RESTYPE_DRAWABLE = "drawable";
    public static final String RESTYPE_COLOR = "color";

    public static List<Candidate> getCandidates(Context ctx) {
        final Gson jsonUtils = new Gson();
        final Type listType = new TypeToken<List<Candidate>>() {
        }.getType();
        List<Candidate> candidateList;

        // note: this is not a proper way to load json resources
        final String jsonCandidates = ctx.getResources().getString(R.string.candidates);
        Log.d("getCandidates", jsonCandidates);

        try {
            candidateList = (List<Candidate>) jsonUtils.fromJson(jsonCandidates, listType);
        } catch (JsonSyntaxException jse) {
            jse.printStackTrace();
            candidateList = new ArrayList<>();
        }

        return candidateList;
    }

    public static int getResourceByName(Context ctx, String resourceName, String resourceType){
        return ctx.getResources().getIdentifier(resourceName, resourceType, ctx.getPackageName());
    }
}
