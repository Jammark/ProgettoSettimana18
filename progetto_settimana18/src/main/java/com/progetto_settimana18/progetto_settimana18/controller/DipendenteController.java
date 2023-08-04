package com.progetto_settimana18.progetto_settimana18.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

	@GetMapping("/prova")
	@ResponseStatus(HttpStatus.OK)
	public String endpoint() {
		return "ok";
	}

}
