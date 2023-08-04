package com.progetto_settimana18.progetto_settimana18.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progetto_settimana18.progetto_settimana18.model.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

}
