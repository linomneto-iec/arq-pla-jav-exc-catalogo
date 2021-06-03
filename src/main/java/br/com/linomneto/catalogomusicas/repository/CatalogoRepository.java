package br.com.linomneto.catalogomusicas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.linomneto.catalogomusicas.model.Musica;

public interface CatalogoRepository extends JpaRepository<Musica, UUID> {
}