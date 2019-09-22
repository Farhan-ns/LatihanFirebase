package com.acer.latihanfirebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TrackList extends ArrayAdapter<Track> {

    private Activity context;
    private List<Track> trackList;

    public TrackList(Activity context, List<Track> trackList) {
        super(context, R.layout.layout_track_list, trackList);
        this.context = context;
        this.trackList = trackList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_track_list, null, true);

        TextView textViewTrackName = listViewItem.findViewById(R.id.textViewTrackName);
        TextView textViewTrackRating = listViewItem.findViewById(R.id.textViewTrackRating);

        Track track = trackList.get(position);

        textViewTrackName.setText(track.getTrackName());
        textViewTrackRating.setText(String.valueOf(track.getTrackRating()));

        return listViewItem;
    }

}
