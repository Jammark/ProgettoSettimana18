package com.progetto_settimana18.progetto_settimana18.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponsePayload {

	private Long id;
	private String username;
	private String nome;
	private String cognome;
	private String email;

}
