package com.livrexpress.main;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.livrexpress.R;
import com.livrexpress.parseur.Livraison;
import com.livrexpress.parseur.Tournee;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends Activity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener
{
    private GoogleMap mMap;
    private LocationClient mLocationClient;
    private static final LocationRequest REQUEST = LocationRequest.create().setInterval(5000).setFastestInterval(16).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
    }

    public Location getLocation()
    {
        if (mLocationClient != null && mLocationClient.isConnected())
            return mLocationClient.getLastLocation();
        return null;
    }

    private void draw()
    {
        mMap.clear();
        Geocoder fwdGeocoder = new Geocoder(this, Locale.FRENCH);

        Livraison liv = Tournee.getInstance().getPileLivraison().peek();
        List<Address> adresses = null;
        try
        {
            adresses = fwdGeocoder.getFromLocationName(liv.getDestinataire().getAdresse(), 1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (adresses != null && !adresses.isEmpty())
        {
            try
            {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.6044, 1.44194), 5.0f));
                mMap.addMarker(new MarkerOptions().position(new LatLng(adresses.get(0).getLatitude(), adresses.get(0).getLongitude())));
                //                Location location = getLocation();
                //                if (location != null)
                //                    mMap.addPolyline(new PolylineOptions().add(new LatLng(location.getLatitude(), location.getLongitude())).add(new LatLng(adresses.get(0).getLatitude(), adresses.get(0).getLongitude())).width(5).color(0xFFFF0000));

            }
            catch (NullPointerException e)
            {
                //Continuer
            }
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setUpLocationClientIfNeeded();
        setUpMapIfNeeded();
        mLocationClient.connect();
        draw();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mLocationClient != null)
            mLocationClient.disconnect();
    }

    private void setUpMapIfNeeded()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                mMap.setMyLocationEnabled(true);
        }
    }

    private void setUpLocationClientIfNeeded()
    {
        if (mLocationClient == null)
            mLocationClient = new LocationClient(getApplicationContext(), this, this);
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        mLocationClient.requestLocationUpdates(REQUEST, this);
    }

    @Override
    public void onDisconnected()
    {
    }

    @Override
    public void onLocationChanged(Location location)
    {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        if (connectionResult.hasResolution())
        {
            try
            {
                connectionResult.startResolutionForResult(this, 9000);
            }
            catch (IntentSender.SendIntentException e)
            {
                e.printStackTrace();
            }
        }
        else
            Log.e("Error code", String.valueOf(connectionResult.getErrorCode()));
    }

    public void onClickBtnBonLivraison(View view)
    {
        startActivity(new Intent(view.getContext(), BonLivraison.class));
    }
}