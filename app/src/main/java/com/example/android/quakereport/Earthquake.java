package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a single earthquake data.
 * Each object has 3 properties: magnitude, location and date.
 */
public class Earthquake {

    // Magnitude of earthquake
    private double mMagnitude;

    // Location of earthquake
    private String mLocation;

    /** Time of the earthquake */
    private long mTimeInMilliseconds;

    /** Website URL of the earthquake */
    private String mUrl;

    /*
     * Construct a new {@link Earthquake} object.
     *
     *  @param magnitude is the magnitude (size) of the earthquake
     *  @param location is the city location of the earthquake
     *  @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *  earthquake happened
     *  @param url is the website URL to find more details about the earthquake
     * */
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }
    /**
     * Get the magnitude of earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the location of earthquake
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Get the time of the earthquake.
     */
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }
}