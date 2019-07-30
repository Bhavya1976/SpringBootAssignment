package com.stackroute.MuzixApplication.MuzixRepositoryTest;





import com.stackroute.Model.Album;
import com.stackroute.Model.Track;


import com.stackroute.Repository.MuzixRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.Model.Tracks;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MuzixRepositoryTest {

    @Autowired
    MuzixRepository muzixRepository;

Album album;
Track track;
    @Before
    public void setUp()
    {

        album= new Album("Dookudu","Taman","1");
        album.setArtist("Tamman");
        album.setName("Dookudu");
        album.setMbid("1");
    }

    @After
    public void tearDown(){

        muzixRepository.deleteAll();
    }

    @Test
    public void testSaveTrack(){
        muzixRepository.save(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1,fetchTrack.getTrackId());
    }


    @Test
    public void getAllTracks()
    {
        List<Track> tracks = new ArrayList<>();
        Track track1 = new Track(1,"Dookudu","tamman","www.testurl");
        Track track2 = new Track(2,"Dookudu","tamman","www.testurl");
        tracks.add(track1);
        tracks.add(track2);
        List<Track> trackslist = muzixRepository.findAll();
        Assert.assertEquals(tracks,trackslist);
        Assert.assertEquals("Dookudu",tracks.get(0).getTrackName());
    }
}
