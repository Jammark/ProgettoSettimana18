package com.progetto_settimana18.progetto_settimana18.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progetto_settimana18.progetto_settimana18.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Page<User> findAll(Pageable p);
}
