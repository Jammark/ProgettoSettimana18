package com.progetto_settimana18.progetto_settimana18.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dispositivi")
@Getter
@Setter
@NoArgsConstructor
public class Dispositivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tipologia;
	private String nome;

	@Enumerated(EnumType.STRING)
	private StatoDispositivo tipo;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Dispositivo(String tipologia, String nome) {
		super();
		this.tipologia = tipologia;
		this.nome = nome;
		this.tipo = StatoDispositivo.DISPONIBILE;
	}

}
