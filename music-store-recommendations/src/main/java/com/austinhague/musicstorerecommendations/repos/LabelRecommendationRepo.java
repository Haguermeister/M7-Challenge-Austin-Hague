package com.austinhague.musicstorerecommendations.repos;

import com.austinhague.musicstorerecommendations.models.LabelRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRecommendationRepo extends JpaRepository<LabelRecommendation,Integer> {
}
