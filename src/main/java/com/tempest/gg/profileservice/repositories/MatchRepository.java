package com.tempest.gg.profileservice.repositories;

import com.tempest.gg.profileservice.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match, String> {
}
