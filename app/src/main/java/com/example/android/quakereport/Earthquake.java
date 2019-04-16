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

    /** Time of the earthquake */
    private long mTimeInMilliseconds;

    /*
     * Construct a new {link Earthquake} object.
     *
     *  @param magnitude is the magnitude (size) of the earthquake
     * @param location is the city location of the earthquake
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *  earthquake happened
     * */
    public Earthquake(String magnitude, String location, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
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
     * Get the time of the earthquake.
     */
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}