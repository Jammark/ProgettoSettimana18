package com.progetto_settimana18.progetto_settimana18.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String nome;
	private String cognome;
	private String email;

	public User(String username, String nome, String cognome, String email) {
		super();
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}


}
