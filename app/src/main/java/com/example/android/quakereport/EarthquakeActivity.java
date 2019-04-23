/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake objects from {link QueryUtils}
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

//        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
//        earthquakes.add(new Earthquake("1", "San Francisco", "Feb 2, 2016"));
//        earthquakes.add(new Earthquake("1", "London", "Feb 2, 2016"));
//        earthquakes.add(new Earthquake("1", "Tokyo", "Feb 2, 2016"));
//        earthquakes.add(new Earthquake("1", "Mexico City", "Feb 2, 2016"));
//        earthquakes.add(new Earthquake("1", "Moscow", "Feb 2, 2016"));
//        earthquakes.add(new Earthquake("1", "Rio de Janeiro", "Feb 2, 2016"));
//        earthquakes.add(new Earthquake("1", "Paris", "Feb 2, 2016"));

        // Create a {@link EarthquakeAdapter}, whose data source is a list of
        // {@link Earthquake}s. The adapter knows how to create list item views for each item
        // in the list.
        final EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(earthquakeAdapter);

        // Access the URL when the list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake object that was clicked on
                Earthquake currentEarthquake = earthquakeAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor).
                // Get the URL from the current Earthquake object.
                // The Intent constructor (that we want to use) requires a Uri object, so we need to convert
                // our URL (in the form of a String) into a URI.
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI.
                // Once we have the website URL in a Uri object, we can create a new intent.
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity.
                // Start a new activity with that intent, so that a web browser app on the device
                // will handle the intent and display the website for that earthquake.
                startActivity(websiteIntent);
            }
        });
    }
}