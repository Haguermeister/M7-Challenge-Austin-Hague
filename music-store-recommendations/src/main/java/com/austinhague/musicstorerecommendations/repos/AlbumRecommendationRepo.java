package com.austinhague.musicstorerecommendations.repos;

import com.austinhague.musicstorerecommendations.models.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRecommendationRepo extends JpaRepository<AlbumRecommendation,Integer> {
}
