package com.christopherbare.mobileappinclass10;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    InputStream is = getResources().openRawResource(R.raw.trip);
    Writer writer = new StringWriter();
    char[] buffer = new char[1024];
    ArrayList<String> pointsArray = new ArrayList<>();


    /**
     *
     *  //The JSON file in string format
     String json = IOUtils.toString(connection.getInputStream(), "UTF-8");

     //The entire JSON object
     JSONObject root = new JSONObject(json);

     //The array within the JSON object
     JSONObject feed = root.getJSONObject("feed");

     JSONArray resultApps = feed.getJSONArray("results");

     genreArray = new ArrayList<>();

     genreArray.add("All");

     for (int i = 0; i < feed.length(); i++) {

     //Initialize objects
     app = new App();

     //Get the JSON "App"
     JSONObject appJSON = resultApps.getJSONObject(i);

     //get the JSON "genres"
     JSONArray genresJSON = appJSON.getJSONArray("genres");

     //Set the fields for object from the JSON one
     app.setName(appJSON.getString("name"));
     app.setReleaseDate(appJSON.getString("releaseDate"));
     app.setArtistName(appJSON.getString("artistName"));
     app.setArtworkUrl100(appJSON.getString("artworkUrl100"));

     String genreName;
     //get genres
     if (genresJSON != null) {
     for (int j = 0; j < genresJSON.length(); j++) {
     JSONObject genre = genresJSON.getJSONObject(j);
     genreName = genre.getString("name");
     app.addGenre(genreName);
     if (!genreArray.contains(genreName))
     genreArray.add(genreName);
     }
     }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String json = writer.toString();

        JSONObject root = null;
        try {
            root = new JSONObject(json);


            JSONArray points = root.getJSONArray("points");




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



    }
}
