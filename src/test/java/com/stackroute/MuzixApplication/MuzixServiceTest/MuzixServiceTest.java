package com.stackroute.MuzixApplication.MuzixServiceTest;

import com.stackroute.Exception.TrackAlreadyExistsException;
import com.stackroute.Exception.TrackNotFoundException;
import com.stackroute.Model.Track;

import com.stackroute.Repository.MuzixRepository;
import com.stackroute.Service.MuzixServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class MuzixServiceTest {


    @Autowired
    MockMvc mockMvc;

    @Mock
    MuzixRepository muzixRepository;

    @InjectMocks
    MuzixServiceImpl muzixService;

    Track track;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixService).build();
        track = new Track(1, "Dookudu", "Enjoy", "Excellent");
    }

    @Test
    public void saveTrackTest() throws Exception {
        when(muzixRepository.save(track)).thenReturn(track);
        Track savedTrack = muzixService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);
        verify(muzixRepository, times(1)).save(Mockito.any(Track.class));
    }


    @Test
    public void getAllTracksTest() {
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        when(muzixRepository.findAll()).thenReturn(trackList);
        List<Track> retrievedTracks = muzixService.getAllTracks();
        Assert.assertEquals(trackList, retrievedTracks);

    }

    @Test
    public void getTracksByNameTest() {
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        when(muzixRepository.findTitleByName("majili")).thenReturn(trackList);
        List<Track> retrievedTracks = muzixService.trackByName("majili");
        Assert.assertEquals(trackList, retrievedTracks);

    }

    @Test
    public void updateTrackTest() throws TrackNotFoundException {
        Optional<Track> optionalTrack = Optional.of(track);
        when(muzixRepository.findById(1)).thenReturn(optionalTrack);
        when(muzixRepository.save(track)).thenReturn(track);
        boolean updatedTrack = muzixService.updateTrack(track, 1);
        Assert.assertEquals(track, updatedTrack);

        verify(muzixRepository, times(1)).save(Mockito.any(Track.class));

    }

    @Test
    public void deleteTrackTest() throws TrackNotFoundException {
        Optional<Track> optionalTrack = Optional.of(track);
        when(muzixRepository.findById(1)).thenReturn(optionalTrack);
        Boolean result = muzixService.deleteTrack(1);
        Assert.assertTrue(result);
        verify(muzixRepository, times(1)).delete(Mockito.any(Track.class));

    }
}
