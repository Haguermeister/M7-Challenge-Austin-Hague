package com.austinhague.musicstorecatalog.controllers;

import com.austinhague.musicstorecatalog.models.Track;
import com.austinhague.musicstorecatalog.repos.AlbumRepo;
import com.austinhague.musicstorecatalog.repos.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    TrackRepo repo;
    @Autowired
    AlbumRepo albumRepo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getTracks() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track getTrackById(@PathVariable Integer id) {
        Optional<Track> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Track addTrack(@RequestBody @Valid Track track) {
        if(albumRepo.findById(track.getAlbumId()).isPresent()){
            return repo.save(track);
        }
        else{
            throw new RuntimeException("Album does not exist to add track too.");
        }
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@RequestBody @Valid Track track) {
        if (!repo.findById(track.getTrackId()).isPresent()) {
            throw new EmptyResultDataAccessException("No Console with an id of " + track.getTrackId() + " exists", 0);
        }
        repo.save(track);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
