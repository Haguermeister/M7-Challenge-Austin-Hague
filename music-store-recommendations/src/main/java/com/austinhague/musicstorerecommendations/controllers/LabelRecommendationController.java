package com.austinhague.musicstorerecommendations.controllers;

import com.austinhague.musicstorerecommendations.models.LabelRecommendation;
import com.austinhague.musicstorerecommendations.repos.LabelRecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labelRecommendation")
public class LabelRecommendationController {

    @Autowired
    LabelRecommendationRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getLabelRecommendations() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation getLabelRecommendationById(@PathVariable Integer id) {
        Optional<LabelRecommendation> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation addLabelRecommendation(@RequestBody @Valid LabelRecommendation artistRecommendation) {
        return repo.save(artistRecommendation);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelRecommendation(@RequestBody @Valid LabelRecommendation labelRecommendation) {
        if (!repo.findById(labelRecommendation.getLabelRecommendationId()).isPresent()) {
            throw new EmptyResultDataAccessException("No Console with an id of " + labelRecommendation.getLabelRecommendationId() + " exists", 0);
        }
        repo.save(labelRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRecommendation(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}

