package com.austinhague.musicstorerecommendations.repos;

import com.austinhague.musicstorerecommendations.models.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRecommendationRepo extends JpaRepository<TrackRecommendation,Integer> {
}
