package com.progetto_settimana18.progetto_settimana18.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.progetto_settimana18.progetto_settimana18.model.User;
import com.progetto_settimana18.progetto_settimana18.service.UserService;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

	@Autowired
	private UserService uSrv;

	@GetMapping("/prova")
	@ResponseStatus(HttpStatus.OK)
	public String endpoint() {
		return "ok";
	}

	@GetMapping("")
	public ResponseEntity<Page<User>> getUsers(@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size) {
		Page<User> p = uSrv.getUsers(page, size);
		if (p.isEmpty()) {
			return new ResponseEntity<Page<User>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Page<User>>(p, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public UserResponsePayload getUser(@PathVariable("id") Long id) {
		User u = uSrv.findById(id);
		return convert(u);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponsePayload create(@RequestBody UserRequestPayload body) {
		User u = uSrv.save(body);
		return convert(u);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponsePayload create(@PathVariable("id") Long id, @RequestBody UserRequestPayload body) {
		User u = uSrv.update(body, id);
		return convert(u);
	}

	private UserResponsePayload convert(User u) {
		return new UserResponsePayload(u.getId(), u.getUsername(), u.getNome(), u.getCognome(), u.getEmail());
	}

}
