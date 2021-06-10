package br.com.linomneto.catalogomusicas.service;

import java.util.List;

import br.com.linomneto.catalogomusicas.model.Musica;

public interface CatalogoService {
    public List<Musica> findAll();
    public Musica findById(Long id);
    public Musica save(Musica musica);
    public void deleteById(Long id);
}
