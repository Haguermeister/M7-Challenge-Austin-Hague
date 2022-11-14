package com.austinhague.musicstorecatalog.controllers;

import com.austinhague.musicstorecatalog.models.Album;
import com.austinhague.musicstorecatalog.repos.AlbumRepo;
import com.austinhague.musicstorecatalog.repos.ArtistRepo;
import com.austinhague.musicstorecatalog.repos.LabelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumRepo repo;
    @Autowired
    ArtistRepo artistRepo;
    @Autowired
    LabelRepo labelRepo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAlbums() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbumById(@PathVariable Integer id) {
        Optional<Album> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Album addAlbum(@RequestBody @Valid Album album) throws ParseException {
        if(artistRepo.findById(album.getArtistId()).isPresent()){
            if(labelRepo.findById(album.getLabelId()).isPresent()){

                return repo.save(album);
            }
            else{
                throw new RuntimeException("Label does not exist to add album too.");
            }
        }
        else{
            throw new RuntimeException("Artist does not exist to add album too.");
        }
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody @Valid Album album) {
        if (!repo.findById(album.getAlbumId()).isPresent()) {
            throw new EmptyResultDataAccessException("No Console with an id of " + album.getAlbumId() + " exists", 0);
        }
        repo.save(album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
