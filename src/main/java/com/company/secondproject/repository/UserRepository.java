package com.company.secondproject.repository;

import com.company.secondproject.modul.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //TODO: select u from users as u where u.user_id = ? and deleted_at is null
    Optional<User> findByUserIdAndDeletedAtIsNull(Integer userId);

    boolean existsByEmail(String email);

    //TODO: 1: method query
    //TODO: 2: native query
    //TODO: 3: HQL -> Hibernate Query Language
    //TODO: 4: Named Query
}
