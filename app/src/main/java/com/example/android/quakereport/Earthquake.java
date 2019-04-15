package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a single earthquake data.
 * Each object has 3 properties: magnitude, location and date.
 */
public class Earthquake {

    // Magnitude of earthquake
    private String mMagnitude;

    // Location of earthquake
    private String mLocation;

    // Date of earthquake
    private String mDate;

    /*
     * Create a new Earthquake object.
     *
     * @param vName is the name of the Android version (e.g. Gingerbread)
     * @param vNumber is the corresponding Android version number (e.g. 2.3-2.7)
     * @param image is drawable reference ID that corresponds to the Android version
     * */
    public Earthquake(String magnitude, String location, String date)
    {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    /**
     * Get the magnitude of earthquake
     */
    public String getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the location of earthquake
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Get the date of earthquake
     */
    public String getDate() {
        return mDate;
    }
}