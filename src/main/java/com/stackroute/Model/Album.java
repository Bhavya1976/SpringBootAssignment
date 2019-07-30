package com.stackroute.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {


    public Album(String name, String artist, String mbid) {
        this.name = name;
        this.artist = artist;
        this.mbid = mbid;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }



    private String name;
    private String artist;
    private String mbid;
    Tracks tracks;



}
