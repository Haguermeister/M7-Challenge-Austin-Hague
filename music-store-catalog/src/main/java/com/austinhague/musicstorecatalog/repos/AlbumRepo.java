package com.austinhague.musicstorecatalog.repos;

import com.austinhague.musicstorecatalog.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<Album,Integer> {
}
