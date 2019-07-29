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
    //Constructor for MuzixServiceImpl
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

    @Override
    public void deleteTrack(@PathVariable("id") int trackId) {

        muzixRepository.deleteById(trackId);
//        return deleteTrack(trackId);
    }


    @Override
    public List<Track> trackByName(String name) {
        List<Track> trackList = muzixRepository.findTitleByName(name);
        return trackList;
    }

    public static String GET_URL = "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=e2b3561d25a2ac7d5836db843cc277ea&artist=Cher&album=Believe&format=json";

    public String getAllTracks() throws Exception {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            return "GET request not worked";

        }


    }


//    JSONObject json;
//
//    void setup() {
//
//        int Values[];
//        int size;
//
//        json = loadJSONObject("data.json");
//
//        JSONArray values = null;
//        try {
//            values = json.getJSONArray("animals");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < values.size(); i++) {
//
//            JSONObject animal = values.getJSONObject(i);
//
//            int id = animal.getInt("id");
//            String species = animal.getString("species");
//            String name = animal.getString("name");
//
//            println(id + ", " + species + ", " + name);
//        }
//    }
//
//    private JSONObject loadJSONObject(String s) {
//    }
//

}