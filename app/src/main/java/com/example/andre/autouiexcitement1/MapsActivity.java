package com.example.andre.autouiexcitement1;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location initialLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // set the initial location to the location received via the intent's extra
        Bundle bundle = getIntent().getExtras();
        String extra = getResources().getString(R.string.extra_location);
        initialLocation = bundle.getParcelable(extra);


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

        // Add a marker to the initial location and move the camera
        LatLng start = new LatLng(initialLocation.getLatitude(), initialLocation.getLongitude());
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(start)
                .title("Is this you?")
                .snippet(String.format("%.3f", start.latitude) + " / " +
                        String.format("%.3f", start.longitude)));
        marker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(start));
    }
}
