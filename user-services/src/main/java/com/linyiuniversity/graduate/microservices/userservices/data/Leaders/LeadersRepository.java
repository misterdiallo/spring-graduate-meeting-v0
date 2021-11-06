package com.linyiuniversity.graduate.microservices.userservices.data.Leaders;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadersRepository extends CrudRepository<LeaderEntity, Long> {
    @Query("select l from LeaderEntity l where l.USER_ID = ?1")
    LeaderEntity findByUserId(String userId);

}
