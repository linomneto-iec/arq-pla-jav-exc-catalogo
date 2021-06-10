package br.com.linomneto.catalogomusicas.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        ModelAndView mv = new ModelAndView("musicas");
        List<Musica> musicas = service.findAll();
        mv.addObject("musicas", musicas);
        return mv;
    }

    @RequestMapping(value = "/musicas/{id}", method=RequestMethod.GET)
    public ModelAndView getDetalhesMusica(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("detalhesMusica");
        Musica musica = service.findById(id);
        mv.addObject("musica", musica);
        return mv;
    }

    @RequestMapping(value="/form-musica", method = RequestMethod.GET)
    public String getFormMusica(){
        return "form-musica";
    }

    @RequestMapping(value="/form-musica", method=RequestMethod.POST)
    public String salvarMusica(@Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("msg","Campos obrigatórios não informados.");
            return "redirect:/form-musicas";
        }
        musica.setData(new Date());
        service.save(musica);
        return "redirect:/musicas";
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getIndex() {
        return "redirect:/musicas";
    }

}
