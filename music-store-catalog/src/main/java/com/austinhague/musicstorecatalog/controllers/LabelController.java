package com.austinhague.musicstorecatalog.controllers;

import com.austinhague.musicstorecatalog.models.Label;
import com.austinhague.musicstorecatalog.repos.LabelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    LabelRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Label> getLabels() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Label getLabelById(@PathVariable Integer id) {
        Optional<Label> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Label addLabel(@RequestBody @Valid Label label) {
        return repo.save(label);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@RequestBody @Valid Label label) {
        if (!repo.findById(label.getLabelId()).isPresent()) {
            throw new EmptyResultDataAccessException("No Console with an id of " + label.getLabelId() + " exists", 0);
        }
        repo.save(label);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
