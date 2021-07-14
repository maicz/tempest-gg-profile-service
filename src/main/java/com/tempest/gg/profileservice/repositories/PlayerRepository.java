package com.tempest.gg.profileservice.repositories;

import com.tempest.gg.profileservice.models.SummonerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<SummonerProfile, String> {

    SummonerProfile findByNameIgnoreCase(String name);

}
