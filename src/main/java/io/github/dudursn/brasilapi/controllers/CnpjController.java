package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.CnpjDao;
import io.github.dudursn.brasilapi.models.Cnpj;
import io.github.dudursn.brasilapi.services.BrasilApiService;
import io.github.dudursn.brasilapi.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class CnpjController {

    @Autowired
    CnpjDao cnpjDao;

    private String mensagem = "";

    @PostMapping("/populateCnpj")
    public String populateCnpj(@RequestParam("cnpj") String cnpjStr) {

        Cnpj cnpj = BrasilApiService.getCnpj(cnpjStr);
        this.mensagem = "Registro não encontrado";

        if(cnpj!=null && cnpj.getCnpj()!=(null)){
            cnpjDao.save(cnpj);
            this.mensagem = "Registro consumido da API";
        }

        return "redirect:/cnpjs";
    }

    @RequestMapping("/cnpjs")
    public String cnpjs(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("cnpjs", cnpjDao.findAll());
        return "cnpjs";
    }


    @RequestMapping("/cnpjs/form")
    public String create(Model model) {

        model.addAttribute("cnpj", new Cnpj());
        return "cnpjsForm";
    }

    @RequestMapping("/cnpjs/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Cnpj> cnpj = cnpjDao.findById(id);
        model.addAttribute("cnpj", cnpj);
        return "cnpjsForm";
    }

    @GetMapping("/cnpjs/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        cnpjDao.findById(id)
                .map(record -> {
                    cnpjDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/cnpjs";
    }

    @PostMapping("/cnpjs/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<Cnpj> cnpjs = new ArrayList<Cnpj>();
        Optional<Cnpj> dado = cnpjDao.findById(id);
        if(dado.isPresent()){

            cnpjs.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("cnpjs", cnpjs);
        return "/cnpjs";
    }

    @PostMapping("/cnpjs/save")
    public String save(@Validated Cnpj cnpj, BindingResult result) {

        if (result.hasErrors()) {
            return "cnpjsForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        cnpjDao.save(cnpj);
        return "redirect:/cnpjs";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/cnpjs/json")
    @ResponseBody
    public ResponseEntity<Collection<Cnpj>> findAllJson() {

        List<Cnpj> cnpjs = (List<Cnpj>) cnpjDao.findAll();

        return ResponseEntity.ok(cnpjs);
    }


}
