package com.progetto_settimana18.progetto_settimana18.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestPayload {

	private String username;
	private String email;
	private String nome;
	private String cognome;

}
