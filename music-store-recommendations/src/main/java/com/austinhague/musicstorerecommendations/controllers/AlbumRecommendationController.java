package com.austinhague.musicstorerecommendations.controllers;

import com.austinhague.musicstorerecommendations.models.AlbumRecommendation;
import com.austinhague.musicstorerecommendations.repos.AlbumRecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/albumRecommendation")
public class AlbumRecommendationController {
    @Autowired
    AlbumRecommendationRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAlbumRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation getAlbumRecommendationById(@PathVariable Integer id) {
        Optional<AlbumRecommendation> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation addAlbumRecommendation(@RequestBody @Valid AlbumRecommendation albumRecommendation) {
        return repo.save(albumRecommendation);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumRecommendation(@RequestBody @Valid AlbumRecommendation albumRecommendation) {
        if (!repo.findById(albumRecommendation.getAlbumRecommendationId()).isPresent()) {
            throw new EmptyResultDataAccessException("No Console with an id of " + albumRecommendation.getAlbumId() + " exists", 0);
        }
        repo.save(albumRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRecommendation(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}