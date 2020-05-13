package com.example.pomniki;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseDatabase database;
    DatabaseReference number;
    Double latitude;
    Double longitude;
    String miasto;
    String rodzaj;
    String nazwa;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //--MAP PART--
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //--DATABASE PART--
        database = FirebaseDatabase.getInstance();

        //Getting Reference to Root Node
        DatabaseReference number = database.getReference().child("pomniki");

        // Read from the database
        number.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Getting the string value of that node
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //Getting User object from dataSnapshot
                    Pomniki pomnik = data.getValue(Pomniki.class);
                    miasto = pomnik.getMiasto();
                    rodzaj = pomnik.getRodzaj();
                    nazwa = pomnik.getNazwa();
                    latitude = pomnik.getLatitude();
                    longitude = pomnik.getLongitude();

                    Log.i("onDataChange: ", miasto + ", " + nazwa + ", " + rodzaj + ", " + latitude + ", " + longitude);

                    LatLng loc = new LatLng(latitude,longitude);
                    mMap.addMarker(new MarkerOptions().position(loc).title(nazwa));
                    i++;

                }

                Toast toast = Toast.makeText(getApplicationContext(), "Załadowano " + i + " miejsc/a", Toast.LENGTH_SHORT);
                toast.show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("Failed to read value.", String.valueOf(error.toException()));
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng center = new LatLng(52.065162,19.252522);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,5));
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
