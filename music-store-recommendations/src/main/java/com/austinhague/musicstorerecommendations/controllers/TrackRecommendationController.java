package com.austinhague.musicstorerecommendations.controllers;

import com.austinhague.musicstorerecommendations.models.TrackRecommendation;
import com.austinhague.musicstorerecommendations.repos.TrackRecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trackRecommendation")
public class TrackRecommendationController {

    @Autowired
    TrackRecommendationRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getTrackRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation getTrackRecommendationById(@PathVariable Integer id) {
        Optional<TrackRecommendation> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation addTrackRecommendation(@RequestBody @Valid TrackRecommendation artistRecommendation) {
        return repo.save(artistRecommendation);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRecommendation(@RequestBody @Valid TrackRecommendation trackRecommendation) {
        if (!repo.findById(trackRecommendation.getTrackRecommendationId()).isPresent()) {
            throw new EmptyResultDataAccessException("No Console with an id of " + trackRecommendation.getTrackRecommendationId() + " exists", 0);
        }
        repo.save(trackRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
