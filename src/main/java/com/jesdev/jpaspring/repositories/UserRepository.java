package com.jesdev.jpaspring.repositories;

import com.jesdev.jpaspring.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
}
