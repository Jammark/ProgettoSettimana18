package com.progetto_settimana18.progetto_settimana18.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progetto_settimana18.progetto_settimana18.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Page<User> findAll(Pageable p);

	Page<User> findByNomeAndCognome(String nome, String cognome, Pageable p);

	Page<User> findByNome(String nome, Pageable p);

	Page<User> findByCognome(String cognome, Pageable p);

	Page<User> findByUsername(String username, Pageable p);

	Page<User> findByEmail(String email, Pageable p);

	boolean existsByUsername(String username);
}
