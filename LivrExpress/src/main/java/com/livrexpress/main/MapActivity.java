package com.livrexpress.main;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.livrexpress.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import com.google.android.gms.maps.*;

public class MapActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        GoogleMap mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        Geocoder fwdGeocoder = new Geocoder(this, Locale.FRENCH);
        String adresse = "3 cours Alsace Lorraine 33000 BORDEAUX";
        List<Address> adresses = null;
        try {
            adresses = fwdGeocoder.getFromLocationName(adresse, 10);
        }
        catch (IOException e)
        {
        }

        mMap.addMarker(new MarkerOptions().position(new LatLng(adresses.get(0).getLatitude(), adresses.get(0).getLongitude())));
        mMap.setMyLocationEnabled(true);
    }
}