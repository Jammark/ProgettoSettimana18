package com.progetto_settimana18.progetto_settimana18.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.progetto_settimana18.progetto_settimana18.controller.DispositivoRequestPayload;
import com.progetto_settimana18.progetto_settimana18.model.Dispositivo;
import com.progetto_settimana18.progetto_settimana18.model.StatoDispositivo;
import com.progetto_settimana18.progetto_settimana18.model.User;
import com.progetto_settimana18.progetto_settimana18.repository.DispositivoRepository;
import com.progetto_settimana18.progetto_settimana18.repository.UserRepository;

@Service
public class DispositivoService {

	@Autowired
	private DispositivoRepository repo;

	@Autowired
	private UserRepository uRepo;

	public Dispositivo save(DispositivoRequestPayload body) {
		if (repo.existsByNome(body.getNome())) {
			throw new IllegalArgumentException("Nome dispositivo già utilizzato: " + body.getNome());
		}
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

	public Page<Dispositivo> getDispositivi(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	public void rimuovi(Long id) {
		Dispositivo d = findById(id);
		this.repo.delete(d);
	}

	public Page<Dispositivo> searchByTipologia(String tipologia, int page, int size) {
		return repo.findByTipologia(tipologia, PageRequest.of(page, size));
	}

	public Page<Dispositivo> searchByNome(String nome, int page, int size) {
		return repo.findByNome(nome, PageRequest.of(page, size));
	}

	public Page<Dispositivo> searchByStato(String stato, int page, int size) {
		return repo.findByTipo(StatoDispositivo.valueOf(stato), PageRequest.of(page, size));
	}

	public Page<Dispositivo> searchByUser(Long userId, int page, int size) {
		return repo.findByUserId(userId, PageRequest.of(page, size));
	}

	public Dispositivo assegna(Long dId, Long uID) {
		Dispositivo d = findById(dId);
		User u = uRepo.findById(uID).orElseThrow(() -> new IllegalArgumentException("id utente non valido:" + uID));
		d.setUser(u);
		d.setTipo(StatoDispositivo.ASSEGNATO);
		return this.repo.save(d);
	}

	public Dispositivo libera(Long dId) {
		Dispositivo d = findById(dId);
		d.setUser(null);
		d.setTipo(StatoDispositivo.DISPONIBILE);
		return this.repo.save(d);
	}

	public void liberaDispositivi(User u) {
		if (u.getId() != null) {
			this.repo.findByUserId(u.getId()).stream().map(Dispositivo::getId).forEach(this::libera);
		} else {
			throw new IllegalArgumentException("id utente non valido: " + u.getId());
		}
	}
}
