package com.austinhague.musicstorecatalog.repos;

import com.austinhague.musicstorecatalog.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends JpaRepository<Artist,Integer> {
}
