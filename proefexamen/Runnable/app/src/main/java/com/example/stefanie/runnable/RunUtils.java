package com.example.stefanie.runnable;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stilkin in november 2016
 */
public class RunUtils {
    public static final String RESTYPE_MIPMAP = "mipmap";
    public static final String RESTYPE_DRAWABLE = "drawable";
    public static final String RESTYPE_COLOR = "color";

    public static List<Run> getRuns(final Context ctx) {
        final List<Run> runList = new ArrayList<Run>();
        runList.add(new Run("Dwars door Hasselt", "09-10-2016", "Hasselt", "www.sport.be", 15f, 5, "HBVL"));
        runList.add(new Run("Combat Run", "05-06-2016", "Koningshooikt", "www.combat-run.be", 25f, 10, "Stad Lier"));
        runList.add(new Run("Santa Run", "12-12-2015", "Antwerpen", "www.sport.be", 22f, 5, "GVA"));
        runList.add(new Run("Neptunus Run", "13-11-2016", "Nieuwpoort", "www.sport.be", 48f, 10, "KBC"));
        runList.add(new Run("Halve Marathon Eindhoven", "08-10-2017", "Eindhoven", "www.marathoneindhoven.nl", 30f, 21, "DLL"));
        runList.add(new Run("In Flanders Fields Marathon", "10-09-2017", "Westhoek", "marathons.be", 50f, 42, "Lotto"));
        return runList;
    }

    public static int[] getDistances() {
        return new int[]{1, 5, 10, 21, 42};
    }

    public static int getResourceByName(final Context ctx, final String resourceName, final String resourceType) {
        return ctx.getResources().getIdentifier(resourceName, resourceType, ctx.getPackageName());
    }
}
