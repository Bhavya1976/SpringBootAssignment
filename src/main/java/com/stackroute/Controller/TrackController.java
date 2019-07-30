package com.stackroute.Controller;

import com.stackroute.Exception.TrackAlreadyExistsException;
import com.stackroute.Exception.TrackNotFoundException;
import com.stackroute.Model.Track;
import com.stackroute.Service.MuzixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@RestController
@RequestMapping(value="api/v2")
public class TrackController {

    MuzixService muzixService;
    public TrackController (MuzixService muzixService){
        this.muzixService = muzixService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack (@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            muzixService.saveTrack(track);
            responseEntity = new ResponseEntity<String >("Successfully created", HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return  responseEntity;
    }


    @GetMapping("track")
    public ResponseEntity<?> getTracks() throws Exception{

        //getting all tracks
        return new ResponseEntity<List<Track>>(muzixService.getAllTracks(),HttpStatus.OK);

    }

    @PutMapping("/track/{id}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track,@PathVariable("id") int id) {

        try {
            muzixService.updateTrack(track, id);
            return new ResponseEntity<String>("updated successfully",HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }


    }

    @DeleteMapping("/track/{id}")
    public void deleteTrack(@PathVariable int trackId) {
        try {
            muzixService.deleteTrack(trackId);
        } catch (TrackNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/track/{name}")
    public ResponseEntity<?> trackByName(@PathVariable String name){

        return new ResponseEntity<List<Track>>(muzixService.trackByName(name),HttpStatus.OK);

    }


    public static String GET_URL = "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=e2b3561d25a2ac7d5836db843cc277ea&artist=Cher&album=Believe&format=json";

    public String getAllTrack() throws Exception {
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
    }

