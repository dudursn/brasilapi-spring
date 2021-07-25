package io.github.dudursn.brasilapi.controllers;


import io.github.dudursn.brasilapi.daos.FeriadoNacionalDao;
import io.github.dudursn.brasilapi.models.FeriadoNacional;
import io.github.dudursn.brasilapi.services.BrasilApiService;
import io.github.dudursn.brasilapi.services.HttpClientService;
import io.github.dudursn.brasilapi.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class FeriadoNacionalController {

    @Autowired
    FeriadoNacionalDao feriadoNacionalDao;

    private String mensagem = "";

    @PostMapping("/populateFeriadosNacional")
    public String populateCeps(@RequestParam("ano") String ano) {

        FeriadoNacional[] feriadosNacional = BrasilApiService.getFeriadosNacional(ano);
        feriadoNacionalDao.saveAll(Arrays.asList(feriadosNacional));
        this.mensagem = "Registros consumidos da API";
        return "redirect:/feriadosNacional";
    }

    @RequestMapping("/feriadosNacional")
    public String feriadosNacional(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("feriadosNacional", feriadoNacionalDao.findAll());
        return "feriadosNacional";
    }


    @RequestMapping("/feriadosNacional/form")
    public String create(Model model) {

        model.addAttribute("feriadoNacional", new FeriadoNacional());
        return "feriadosNacionalForm";
    }

    @RequestMapping("/feriadosNacional/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<FeriadoNacional> feriadoNacional = feriadoNacionalDao.findById(id);
        model.addAttribute("feriadoNacional", feriadoNacional);
        return "feriadosNacionalForm";
    }

    @GetMapping("/feriadosNacional/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        feriadoNacionalDao.findById(id)
                .map(record -> {
                    feriadoNacionalDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/feriadosNacional";
    }

    @PostMapping("/feriadosNacional/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<FeriadoNacional> feriadosNacional = new ArrayList<FeriadoNacional>();
        Optional<FeriadoNacional> dado = feriadoNacionalDao.findById(id);
        if(dado.isPresent()){

            feriadosNacional.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("feriadosNacional", feriadosNacional);
        return "feriadosNacional";
    }

    @PostMapping("/feriadosNacional/save")
    public String save(@Validated FeriadoNacional feriadoNacional, BindingResult result) {

        if (result.hasErrors()) {
            return "feriadosNacionalForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        feriadoNacionalDao.save(feriadoNacional);
        return "redirect:/feriadosNacional";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/feriadosNacional/json")
    @ResponseBody
    public ResponseEntity<Collection<FeriadoNacional>> findAllJson() {

        List<FeriadoNacional> feriadosNacional = (List<FeriadoNacional>) feriadoNacionalDao.findAll();

        return ResponseEntity.ok(feriadosNacional);
    }




}


