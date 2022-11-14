package com.austinhague.musicstorecatalog.controllers;

import com.austinhague.musicstorecatalog.models.Artist;
import com.austinhague.musicstorecatalog.repos.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    ArtistRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getArtists() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtistById(@PathVariable Integer id) {
        Optional<Artist> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Artist addArtist(@RequestBody @Valid Artist artist) {
        return repo.save(artist);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody @Valid Artist artist) {
        if (!repo.findById(artist.getArtistId()).isPresent()) {
            throw new EmptyResultDataAccessException("No Console with an id of " + artist.getArtistId() + " exists", 0);
        }
        repo.save(artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
