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
		if (this.repo.existsByUsername(body.getUsername())) {
			throw new IllegalArgumentException("Username già utilizzato: " + body.getUsername());
		}
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

	public void rimuovi(Long id) {
		User d = findById(id);
		this.repo.delete(d);
	}

	public Page<User> search(String nome, String cognome, int page, int size) {
		if (nome != null && cognome != null) {
			return this.repo.findByNomeAndCognome(nome, cognome, PageRequest.of(page, size));
		} else if (nome != null) {
			return this.repo.findByNome(nome, PageRequest.of(page, size));
		} else if (cognome != null) {
			return this.repo.findByCognome(cognome, PageRequest.of(page, size));
		} else {
			throw new IllegalArgumentException("I parametri di ricerca non sono valorizzati.");
		}
	}

	public Page<User> searchByUsername(String username, int page, int size) {
		if (username != null) {
			return this.repo.findByUsername(username, PageRequest.of(page, size));
		} else {
			throw new IllegalArgumentException("I parametri di ricerca non sono valorizzati.");
		}
	}

	public Page<User> searchByEmail(String email, int page, int size) {
		if (email != null) {
			return this.repo.findByEmail(email, PageRequest.of(page, size));
		} else {
			throw new IllegalArgumentException("I parametri di ricerca non sono valorizzati.");
		}
	}

}
