package com.stackroute.Service;

import com.stackroute.Exception.TrackAlreadyExistsException;
import com.stackroute.Exception.TrackNotFoundException;
import com.stackroute.Model.Track;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public interface MuzixService {


    public Track saveTrack(Track track)throws TrackAlreadyExistsException;

    public List<Track> getAllTracks() throws Exception;

    public boolean updateTrack(Track track, int trackId) throws TrackNotFoundException;

    public boolean deleteTrack(@PathVariable("id") int trackId) throws TrackNotFoundException;

    public List<Track> trackByName(String name);




//    public JSONObject loadJSONObject(String string) ;
}