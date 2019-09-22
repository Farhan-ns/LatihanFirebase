package com.acer.latihanfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddTrackActivity extends AppCompatActivity {

    TextView textViewArtisName;
    EditText editTextTrackName;
    SeekBar seekBarRating;
    Button buttonAddTrack;

    ListView listViewTracks;
    DatabaseReference databaseTracks;

    List<Track> tracks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        textViewArtisName = findViewById(R.id.textViewArtistName);
        editTextTrackName = findViewById(R.id.editTextTrackName);
        seekBarRating = findViewById(R.id.seekBarRating);
        listViewTracks = findViewById(R.id.listViewTracks);
        buttonAddTrack = findViewById(R.id.buttonAddTrack);


        Intent intent = getIntent();

        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);
        String id = intent.getStringExtra(MainActivity.ARTIST_ID);

        textViewArtisName.setText(name);

        databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        buttonAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTrack();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTracks.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                tracks.clear();

                for (DataSnapshot trackSnapshot : dataSnapshot.getChildren()) {
                    Track track = trackSnapshot.getValue(Track.class);

                    tracks.add(track);
                }

                TrackList trackList = new TrackList(AddTrackActivity.this, tracks);
                listViewTracks.setAdapter(trackList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveTrack() {
        String trackName = editTextTrackName.getText().toString();
        int rating = seekBarRating.getProgress();

        if (!TextUtils.isEmpty(trackName)) {
            String id = databaseTracks.push().getKey();

            Track track = new Track(trackName, id, rating);

            databaseTracks.child(id).setValue(track);

            Toast.makeText(this, "Tracks Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Track name cannot be empty", Toast.LENGTH_LONG).show();
        }
    }
}
