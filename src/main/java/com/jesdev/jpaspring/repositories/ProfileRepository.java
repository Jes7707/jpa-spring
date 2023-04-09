package com.jesdev.jpaspring.repositories;

import com.jesdev.jpaspring.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
}
