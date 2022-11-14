package com.austinhague.musicstorerecommendations.repos;

import com.austinhague.musicstorerecommendations.models.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRecommendationRepo extends JpaRepository<ArtistRecommendation,Integer> {
}
