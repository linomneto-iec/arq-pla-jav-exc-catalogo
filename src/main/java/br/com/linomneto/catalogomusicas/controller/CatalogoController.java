package br.com.linomneto.catalogomusicas.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.linomneto.catalogomusicas.model.Musica;
import br.com.linomneto.catalogomusicas.service.CatalogoService;

@Controller
public class CatalogoController {
    
    @Autowired
    private CatalogoService service;

    @RequestMapping(value = "/musicas", method=RequestMethod.GET)
    public ModelAndView getMusicas() {
        ModelAndView mv = new ModelAndView("lista-musicas");
        List<Musica> musicas = service.findAll();
        mv.addObject("musicas", musicas);
        return mv;
    }

    @RequestMapping(value = "/musicas/{id}", method=RequestMethod.GET)
    public ModelAndView getDetalhesMusica(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("detalhes-musica");
        Musica musica = service.findById(id);
        mv.addObject("musica", musica);
        return mv;
    }

    @RequestMapping(value="/form-nova-musica", method = RequestMethod.GET)
    public String getFormNovaMusica(){
        return "form-nova-musica";
    }

    @RequestMapping(value="/form-nova-musica", method=RequestMethod.POST)
    public String salvarNovaMusica(@Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("msg","Campos obrigatórios não informados.");
            return "redirect:/form-nova-musica";
        }
        musica.setData(new Date());
        service.save(musica);
        return "redirect:/musicas/" + musica.getId();
    }

    @RequestMapping(value = "/excluir-musica/{id}", method=RequestMethod.GET)
    public String deleteMusica(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/musicas";
    }

    @RequestMapping(value="/form-editar-musica/{id}", method = RequestMethod.GET)
    public ModelAndView getFormEditarMusica(@PathVariable("id") Long id) {
        ModelAndView mv;
        
        Musica musica = service.findById(id);
        if (musica != null) {
            mv = new ModelAndView("form-editar-musica");
            mv.addObject("musica", musica);
        } 
        else 
            mv = new ModelAndView("redirect:/musicas");

        return mv;
    }

    @RequestMapping(value="/form-editar-musica/{id}", method=RequestMethod.POST)
    public String salvarEditarMusica(@PathVariable("id") Long id, @Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        Musica musicaDB = service.findById(id);

        if (musicaDB == null) 
            return "redirect:/musicas";

        if (result.hasErrors()) {
            attributes.addFlashAttribute("msg","Campos obrigatórios não informados.");
            return "redirect:/form-editar-musica/" + id;
        }

        musicaDB.setAutor(musica.getAutor());
        musicaDB.setLetra(musica.getLetra());
        musicaDB.setNome(musica.getNome());
        musicaDB.setChaveYoutube(musica.getChaveYoutube());
        service.save(musicaDB);

        return "redirect:/musicas/" + id;
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getIndex() {
        return "redirect:/musicas";
    }

}
