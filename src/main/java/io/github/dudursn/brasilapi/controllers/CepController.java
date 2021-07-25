package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.CepDao;
import io.github.dudursn.brasilapi.models.Cep;
import io.github.dudursn.brasilapi.services.BrasilApiService;
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
public class CepController {

    @Autowired
    CepDao cepDao;

    private String mensagem = "";

    @PostMapping("/populateCep")
    public String populateCeps(@RequestParam("cep") String cepStr) {

        Cep cep = BrasilApiService.getCep(cepStr);
        this.mensagem = "Registro não encontrado";

        if(cep!=null && cep.getCep()!=(null)){
            cepDao.save(cep);
            this.mensagem = "Registro consumido da API";
        }

        return "redirect:/ceps";
    }

    @RequestMapping("/ceps")
    public String ceps(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("ceps", cepDao.findAll());
        return "ceps";
    }


    @RequestMapping("/ceps/form")
    public String create(Model model) {

        model.addAttribute("cep", new Cep());
        return "cepsForm";
    }

    @RequestMapping("/ceps/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Cep> cep = cepDao.findById(id);
        model.addAttribute("cep", cep);
        return "cepsForm";
    }

    @GetMapping("/ceps/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        cepDao.findById(id)
                .map(record -> {
                    cepDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/ceps";
    }

    @PostMapping("/ceps/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<Cep> ceps = new ArrayList<Cep>();
        Optional<Cep> dado = cepDao.findById(id);
        if(dado.isPresent()){

            ceps.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("ceps", ceps);
        return "/ceps";
    }

    @PostMapping("/ceps/save")
    public String save(@Validated Cep cep, BindingResult result) {

        if (result.hasErrors()) {
            return "cepsForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        cepDao.save(cep);
        return "redirect:/ceps";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/ceps/json")
    @ResponseBody
    public ResponseEntity<Collection<Cep>> findAllJson() {

        List<Cep> ceps = (List<Cep>) cepDao.findAll();

        return ResponseEntity.ok(ceps);
    }


}
