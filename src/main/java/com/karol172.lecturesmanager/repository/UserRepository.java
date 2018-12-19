package com.karol172.lecturesmanager.repository;

import com.karol172.lecturesmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Set<User> findByLogin (String login);
}