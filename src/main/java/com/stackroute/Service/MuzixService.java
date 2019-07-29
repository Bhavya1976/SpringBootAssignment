package com.stackroute.Service;

import com.stackroute.Exception.TrackAlreadyExistsException;
import com.stackroute.Exception.TrackNotFoundException;
import com.stackroute.Model.Track;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public interface MuzixService {


    public Track saveTrack(Track track)throws TrackAlreadyExistsException;

    public String getAllTracks() throws Exception;

    public boolean updateTrack(Track track, int trackId) throws TrackNotFoundException;

    public void deleteTrack(@PathVariable("id") int trackId);

    public List<Track> trackByName(String name);

//    public JSONObject loadJSONObject(String string) ;
}