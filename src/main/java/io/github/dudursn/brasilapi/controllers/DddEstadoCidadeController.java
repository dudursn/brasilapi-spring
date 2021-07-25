package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.DddEstadoCidadeDao;
import io.github.dudursn.brasilapi.models.DddEstadoCidade;
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
public class DddEstadoCidadeController {

    @Autowired
    DddEstadoCidadeDao dddEstadoCidadeDao;

    private String mensagem = "";


    @PostMapping("/populateDddEstadoCidade")
    public String populateDddEstadoCidade(@RequestParam("ddd") String ddd) {

        DddEstadoCidade dddEstadoCidade = BrasilApiService.getDddEstadoCidade(ddd);
        this.mensagem = "Registro não encontrado";

        if(dddEstadoCidade!=null && dddEstadoCidade.getState()!=(null)){
            dddEstadoCidadeDao.save(dddEstadoCidade);
            this.mensagem = "Registro consumido da API";
        }

        return "redirect:/dddEstadoCidades";
    }

    @RequestMapping("/dddEstadoCidades")
    public String dddEstadoCidades(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("dddEstadoCidades", dddEstadoCidadeDao.findAll());
        return "dddEstadoCidades";
    }


    @RequestMapping("/dddEstadoCidades/form")
    public String create(Model model) {

        model.addAttribute("dddEstadoCidade", new DddEstadoCidade());
        return "dddEstadoCidadesForm";
    }

    @RequestMapping("/dddEstadoCidades/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<DddEstadoCidade> dddEstadoCidade = dddEstadoCidadeDao.findById(id);
        model.addAttribute("dddEstadoCidade", dddEstadoCidade);
        return "dddEstadoCidadesForm";
    }

    @GetMapping("/dddEstadoCidades/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        dddEstadoCidadeDao.findById(id)
                .map(record -> {
                    dddEstadoCidadeDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/dddEstadoCidades";
    }

    @PostMapping("/dddEstadoCidades/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<DddEstadoCidade> dddEstadoCidades = new ArrayList<>();
        Optional<DddEstadoCidade> dado = dddEstadoCidadeDao.findById(id);
        if(dado.isPresent()){

            dddEstadoCidades.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("dddEstadoCidades", dddEstadoCidades);
        return "dddEstadoCidades";
    }

    @PostMapping("/dddEstadoCidades/save")
    public String save(@Validated DddEstadoCidade dddEstadoCidade, BindingResult result) {

        if (result.hasErrors()) {
            return "dddEstadoCidadesForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        dddEstadoCidadeDao.save(dddEstadoCidade);
        return "redirect:/dddEstadoCidades";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/dddEstadoCidades/json")
    @ResponseBody
    public ResponseEntity<Collection<DddEstadoCidade>> findAllJson() {

        List<DddEstadoCidade> dddEstadoCidades = (List<DddEstadoCidade>) dddEstadoCidadeDao.findAll();

        return ResponseEntity.ok(dddEstadoCidades);
    }

}
