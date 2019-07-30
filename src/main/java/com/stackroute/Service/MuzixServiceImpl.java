package com.stackroute.Service;

import com.stackroute.Exception.TrackAlreadyExistsException;
import com.stackroute.Exception.TrackNotFoundException;
import com.stackroute.Model.Track;
import com.stackroute.Repository.MuzixRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;


import static org.springframework.http.HttpHeaders.USER_AGENT;


public class MuzixServiceImpl implements MuzixService {

    MuzixRepository muzixRepository;

    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Autowired
    public MuzixServiceImpl() {
    }



    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (muzixRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException("Track Already Exists");

        }
        Track saveTrack = muzixRepository.save(track);

//
//      if(saveTrack==null){
//          throw new TrackNotFoundException("")
//      }
        return saveTrack;
    }


    //method for update a new track
    @Override
    public boolean updateTrack(Track track, int trackId) throws TrackNotFoundException {
        Optional<Track> track1 = muzixRepository.findById(trackId);

        if (muzixRepository.save(track) == null) {
            throw new TrackNotFoundException("Track not found");
        } else if (!track1.isPresent()) {
            return false;
        }
        track.setTrackId(trackId);
        muzixRepository.save(track);
        return true;

    }


    public boolean deleteTrack(int id) throws TrackNotFoundException
    {
        Optional<Track> track1 = muzixRepository.findById(id);

        if(!track1.isPresent())
        {
            throw new TrackNotFoundException("Track Not Found");
        }

        try {

            muzixRepository.delete(track1.get());

            return true;

        }
        catch (Exception exception)
        {
            return false;
        }
    }

    @Override
    public List<Track> trackByName(String name) {
        List<Track> trackList = muzixRepository.findTitleByName(name);
        return trackList;
    }




    @Override
    public List<Track> getAllTracks() {

        return muzixRepository.findAll();
    }
    }





