package br.com.linomneto.catalogomusicas.service;

import java.util.List;
import java.util.UUID;

import br.com.linomneto.catalogomusicas.model.Musica;

public interface CatalogoService {
    public List<Musica> findAll();
    public Musica findById(UUID id);
    public Musica save(Musica musica);
    public void deleteById(UUID id);
}
