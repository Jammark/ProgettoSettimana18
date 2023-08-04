package com.progetto_settimana18.progetto_settimana18.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto_settimana18.progetto_settimana18.controller.DispositivoRequestPayload;
import com.progetto_settimana18.progetto_settimana18.model.Dispositivo;
import com.progetto_settimana18.progetto_settimana18.model.StatoDispositivo;
import com.progetto_settimana18.progetto_settimana18.repository.DispositivoRepository;

@Service
public class DispositivoService {

	@Autowired
	private DispositivoRepository repo;

	public Dispositivo save(DispositivoRequestPayload body) {
		Dispositivo d = new Dispositivo(body.getTipologia(), body.getNome());
		return this.repo.save(d);
	}

	public Dispositivo update(DispositivoRequestPayload body, Long id) {
		Dispositivo d = this.repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id dispositivo non valido." + id));
		d.setNome(body.getNome());
		d.setTipologia(body.getTipologia());
		if (body.getStato() == null) {
			throw new IllegalArgumentException("Valore null non valido per stato dispositivo.");
		}
		d.setTipo(StatoDispositivo.valueOf(body.getStato()));
		return repo.save(d);
	}

	public Dispositivo findById(Long id) {
		return this.repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id dispositivo non valido." + id));
	}
}
