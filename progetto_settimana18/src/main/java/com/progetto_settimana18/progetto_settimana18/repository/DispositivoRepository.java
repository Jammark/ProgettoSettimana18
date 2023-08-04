package com.progetto_settimana18.progetto_settimana18.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progetto_settimana18.progetto_settimana18.model.Dispositivo;
import com.progetto_settimana18.progetto_settimana18.model.StatoDispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

	Page<Dispositivo> findByTipologia(String tipologia, Pageable p);

	Page<Dispositivo> findByTipo(StatoDispositivo tipo, Pageable p);

	Page<Dispositivo> findByNome(String nome, Pageable p);

	boolean existsByNome(String nome);

}
