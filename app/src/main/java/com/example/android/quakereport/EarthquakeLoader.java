package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * This loader prevents memory leak from async task when activity is destroyed
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeData>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String mUrl;

    public EarthquakeLoader(Context context) {
        super(context);
    }

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<EarthquakeData> loadInBackground() {
        if(mUrl == null) {
            return null;
        }

        List<EarthquakeData> earthquakes = QueryUtils.fetchEarthquakes(mUrl);
        return earthquakes;
    }
}
