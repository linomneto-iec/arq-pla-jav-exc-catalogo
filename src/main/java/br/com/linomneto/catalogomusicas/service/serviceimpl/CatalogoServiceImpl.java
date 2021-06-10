package br.com.linomneto.catalogomusicas.service.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.linomneto.catalogomusicas.model.Musica;
import br.com.linomneto.catalogomusicas.repository.CatalogoRepository;
import br.com.linomneto.catalogomusicas.service.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoRepository repository;

    @Override
    public List<Musica> findAll() {
        return repository.findAll();
    }

    @Override
    public Musica findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Musica save(Musica musica) {
        return repository.save(musica);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
        
    }
    
}
