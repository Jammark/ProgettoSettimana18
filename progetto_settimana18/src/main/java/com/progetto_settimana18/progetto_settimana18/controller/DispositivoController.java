package com.progetto_settimana18.progetto_settimana18.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.progetto_settimana18.progetto_settimana18.model.Dispositivo;
import com.progetto_settimana18.progetto_settimana18.service.DispositivoService;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

	@Autowired
	private DispositivoService dSrv;

	@GetMapping("")
	public ResponseEntity<Page<Dispositivo>> getDispositivi(
			@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size,
			@RequestParam(required = false) String tipologia, @RequestParam(required = false) String stato,
			@RequestParam(required = false) String nome, @RequestParam(required = false) Long userId) {

		Page<Dispositivo> p;
		if (tipologia != null) {
			p = dSrv.searchByTipologia(tipologia, page, size);
		} else if (stato != null) {
			p = dSrv.searchByStato(stato, page, size);
		} else if (nome != null) {
			p = dSrv.searchByNome(nome, page, size);
		} else if (userId != null) {
			p = dSrv.searchByUser(userId, page, size);
		} else {
			p = dSrv.getDispositivi(page, size);
		}

		if (p.isEmpty()) {
			return new ResponseEntity<Page<Dispositivo>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Page<Dispositivo>>(p, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public DispositivoResponsePayload getDispositivo(@PathVariable("id") Long id) {
		Dispositivo u = dSrv.findById(id);
		return convert(u);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public DispositivoResponsePayload create(@RequestBody DispositivoRequestPayload body) {
		Dispositivo u = dSrv.save(body);
		return convert(u);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DispositivoResponsePayload create(@PathVariable("id") Long id, @RequestBody DispositivoRequestPayload body) {
		Dispositivo u = dSrv.update(body, id);
		return convert(u);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancella(@PathVariable("id") Long id) {
		this.dSrv.rimuovi(id);
	}

	@PatchMapping("/assegna/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DispositivoResponsePayload assegna(@PathVariable("id") Long dId, @RequestParam Long userId) {
		Dispositivo d = this.dSrv.assegna(dId, userId);
		return convert(d);
	}

	@PatchMapping("/libera/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DispositivoResponsePayload assegna(@PathVariable("id") Long dId) {
		Dispositivo d = this.dSrv.libera(dId);
		return convert(d);
	}

	private DispositivoResponsePayload convert(Dispositivo d) {
		Long userId = d.getUser() != null ? d.getUser().getId() : null;
		return new DispositivoResponsePayload(d.getId(), d.getNome(), d.getTipologia(), userId, d.getTipo().name());
	}

}
