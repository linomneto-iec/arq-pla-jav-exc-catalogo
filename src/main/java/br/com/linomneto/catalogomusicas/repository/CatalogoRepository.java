package br.com.linomneto.catalogomusicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.linomneto.catalogomusicas.model.Musica;

public interface CatalogoRepository extends JpaRepository<Musica, Long> {
}