package com.example.pomniki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMarkerDetails extends AppCompatActivity {

    Button createButton;
    EditText setTitle;
    EditText setDescription;
    LatLng latlng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marker_details);

        createButton = findViewById(R.id.createButton);
        setTitle = findViewById(R.id.setTitle);
        setDescription = findViewById(R.id.setDescription);

    }

    public void addMarker(View view) {
        latlng = getIntent().getExtras().getParcelable("location");
        int length = getIntent().getExtras().getInt("length");
        //MarkerOptions marker = new MarkerOptions();
        if (setTitle.getText() != null && setDescription.getText() != null) {

//            -- Add marker without adding it to the database --
//            marker.position(latlng)
//                    .title(setTitle.getText().toString())
//                    .snippet(setDescription.getText().toString());

//            -- Adding marker to the database --
            Pomniki pomniki = new Pomniki(setTitle.getText().toString(), setDescription.getText().toString(), "", latlng.latitude, latlng.longitude);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference pomnikNode = database.getReference("pomniki").child(String.valueOf(length+1));

            pomnikNode.setValue(pomniki);
        }

//        -- Send marker to MapsActivity.class --
//        Intent resultIntent = new Intent();
//        //resultIntent.putExtra("marker", marker);
//        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }
}
