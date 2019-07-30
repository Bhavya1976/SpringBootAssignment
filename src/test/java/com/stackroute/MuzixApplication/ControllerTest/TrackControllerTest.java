package com.stackroute.MuzixApplication.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Controller.TrackController;
import com.stackroute.Exception.TrackAlreadyExistsException;
import com.stackroute.Model.Track;
import com.stackroute.Service.MuzixService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
  MuzixService muzixService;

    @InjectMocks
    TrackController trackController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
    }

    @Test
    public void saveTrackTest() throws Exception {
       Track track =new Track(1,"Priyathama priyathama","Good","www.testurl");
        when(muzixService.saveTrack(track)).thenReturn(track);
        mockMvc.perform(post("/track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isCreated());
        verify(muzixService, times(1)).saveTrack(Mockito.any(Track.class));
        verifyNoMoreInteractions(muzixService);
    }

    @Test
    public void getAllTracks() throws Exception{
        List<Track> list = new ArrayList<>();
        Track track= new Track(1,"Priyathama Priyathama","Good","www.testurl");
        list.add(track);
        when(muzixService.getAllTracks()).thenReturn(list);
        mockMvc.perform(post("/track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(track)))
                .andExpect(status().isCreated());
        // verify(trackService, times(1)).getAllTracks();
        // verifyNoMoreInteractions(trackService);
    }

    @Test
    public void deleteTrackTest() throws Exception
    {
        when(muzixService.deleteTrack(1)).thenReturn(true);
        mockMvc.perform(delete("/track/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(muzixService, times(1)).deleteTrack(1);
        verifyNoMoreInteractions(muzixService);
    }

    @Test
    public void getTracksByNameTest() throws Exception
    {
        List<Track> tracks = new ArrayList<>();
        Track track1 = new Track(1,"Priyathama Priyathama","Good","www.testurl");
        tracks.add(track1);
        when(muzixService.trackByName("Priyathama Priyathama")).thenReturn(tracks);
        mockMvc.perform(get("/trackByName?name=Priyathama Priyathama")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(muzixService, times(1)).trackByName("Priyathama Priyathama");
        verifyNoMoreInteractions(muzixService);
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }










