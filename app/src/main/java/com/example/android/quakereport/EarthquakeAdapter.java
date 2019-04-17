package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * {@link EarthquakeAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Earthquake} objects.
 * */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

//   We will be use the split(String string) method in the String class to split the original string at the position where the text “ of “ occurs.
//   The result will be a String containing the characters PRIOR to the “ of ” text and a String containing the characters AFTER the “ of “ text.
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * @param context     The current context. Used to inflate the layout file.
     * @param earthquakes A list of Earthquake objects to display in a list.
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Initialize the ArrayAdapter's internal storage for the context and the list.
        super(context, 0, earthquakes);
    }

    /**
     * Provide a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Get the magnitude from the current Earthquake object and
        // set this text on the magnitudeTextView
        magnitudeTextView.setText(currentEarthquake.getMagnitude());

        // Get the original location String from the Earthquake object
        String originalLocation = currentEarthquake.getLocation();
        // Store the resulting Strings as primary location and location offset
        String primaryLocation;
        String locationOffset;
        // If there is the LOCATION_SEPARATOR in the original location String, split the originalLocation String
        // at the position where the text “ of “ occurs. The result will be a String containing the characters
        // PRIOR to the “ of ” text and a String containing the characters AFTER the “ of “ text.
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
            // If there is no LOCATION_SEPARATOR in the original location String, then we can assume
            // that we should we use the “Near the” text as the location offset, and just use
            // the original location String as the primary location.
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        // Display the 2 separate Strings in the 2 TextViews in the list item layout.
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date into readable string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time into readable string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}