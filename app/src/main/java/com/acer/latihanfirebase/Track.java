package com.acer.latihanfirebase;

public class Track {
    private String trackName;
    private String trackId;
    private int trackRating;

    public Track() {}

    public Track(String trackName, String trackId, int trackRating) {
        this.trackName = trackName;
        this.trackId = trackId;
        this.trackRating = trackRating;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackId() {
        return trackId;
    }

    public int getTrackRating() {
        return trackRating;
    }
}
