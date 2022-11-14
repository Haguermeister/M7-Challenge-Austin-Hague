package com.austinhague.musicstorerecommendations.controllers;

import com.austinhague.musicstorerecommendations.models.ArtistRecommendation;
import com.austinhague.musicstorerecommendations.repos.ArtistRecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artistRecommendation")
public class ArtistRecommendationController {

    @Autowired
    ArtistRecommendationRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getArtistRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation getArtistRecommendationById(@PathVariable Integer id) {
        Optional<ArtistRecommendation> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation addArtistRecommendation(@RequestBody @Valid ArtistRecommendation artistRecommendation) {
        return repo.save(artistRecommendation);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistRecommendation(@RequestBody @Valid ArtistRecommendation artistRecommendation) {
        if (!repo.findById(artistRecommendation.getArtistRecommendationId()).isPresent()) {
            throw new EmptyResultDataAccessException("No Console with an id of " + artistRecommendation.getArtistRecommendationId() + " exists", 0);
        }
        repo.save(artistRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRecommendation(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
