package com.progetto_settimana18.progetto_settimana18.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.progetto_settimana18.progetto_settimana18.controller.UserRequestPayload;
import com.progetto_settimana18.progetto_settimana18.model.User;
import com.progetto_settimana18.progetto_settimana18.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User save(UserRequestPayload body) {
		User u = new User(body.getUsername(), body.getNome(), body.getCognome(), body.getEmail());

		return this.repo.save(u);
	}

	public User update(UserRequestPayload body, Long id) {
		User u = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("id utente non trovato: " + id));
		u.setCognome(body.getCognome());
		u.setEmail(body.getEmail());
		u.setNome(body.getNome());
		u.setUsername(body.getUsername());
		return repo.save(u);
	}

	public User findById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new IllegalArgumentException("id utente non trovato: " + id));
	}

	public Page<User> getUsers(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

}
