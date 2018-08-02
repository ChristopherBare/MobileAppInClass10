package com.christopherbare.mobileappinclass10;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
        String json;
        LatLng start = null;
        LatLng end = null;
        PolylineOptions options = null;

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.trip);

            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);

            inputStream.close();

            json = new String(buffer, "UTF-8");

            JSONObject root = new JSONObject(json);
            JSONArray points = root.getJSONArray("points");

            options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);

            for (int i = 0; i < points.length(); i++) {

                double latitude = points.getJSONObject(i).getDouble("latitude");
                double longitude = points.getJSONObject(i).getDouble("longitude");
                LatLng point = new LatLng(latitude, longitude);
                options.add(point);

                if (i==0) start = point;
                if (i==points.length()-1) end = point;
            }
        }
        catch (IOException ex) { ex.printStackTrace(); Log.d("demo", "IOException"); }
        catch (JSONException e) { e.printStackTrace(); Log.d("demo", "JSONException"); }


        // Add a marker in Sydney and move the camera
        mMap.addPolyline(options);
        mMap.addMarker(new MarkerOptions().position(start).title("Start"));
        mMap.addMarker(new MarkerOptions().position(end).title("End"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 9));


    }
}
