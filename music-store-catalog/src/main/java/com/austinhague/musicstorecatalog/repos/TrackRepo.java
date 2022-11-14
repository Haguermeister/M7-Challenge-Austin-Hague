package com.austinhague.musicstorecatalog.repos;

import com.austinhague.musicstorecatalog.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends JpaRepository<Track,Integer> {
}
