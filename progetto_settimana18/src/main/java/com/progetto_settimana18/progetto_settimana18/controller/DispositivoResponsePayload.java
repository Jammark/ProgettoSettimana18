package com.progetto_settimana18.progetto_settimana18.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DispositivoResponsePayload {

	private Long id;
	private String nome;
	private String tipologia;
	private Long userId;
	private String stato;

}
