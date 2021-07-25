package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.EstadoDao;
import io.github.dudursn.brasilapi.models.Estado;
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
public class EstadoController {

    @Autowired
    EstadoDao estadoDao;

    private String mensagem = "";


    @RequestMapping("/populateEstados")
    public String populateEstados() {

        Estado[] estados = BrasilApiService.getEstados();
        estadoDao.saveAll(Arrays.asList(estados));
        this.mensagem = "Registros consumidos da API";
        return "redirect:/estados";
    }

    @RequestMapping("/estados")
    public String estados(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("estados", estadoDao.findAll());
        return "estados";
    }


    @RequestMapping("/estados/form")
    public String create(Model model) {

        model.addAttribute("estado", new Estado());
        return "estadosForm";
    }

    @RequestMapping("/estados/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Estado> estado = estadoDao.findById(id);
        model.addAttribute("estado", estado);
        return "estadosForm";
    }

    @GetMapping("/estados/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        estadoDao.findById(id)
                .map(record -> {
                    estadoDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/estados";
    }

    @PostMapping("/estados/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<Estado> estados = new ArrayList<>();
        Optional<Estado> dado = estadoDao.findById(id);
        if(dado.isPresent()){

            estados.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("estados", estados);
        return "estados";
    }

    @PostMapping("/estados/save")
    public String save(@Validated Estado estado, BindingResult result) {

        if (result.hasErrors()) {
            return "estadosForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        estadoDao.save(estado);
        return "redirect:/estados";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/estados/json")
    @ResponseBody
    public ResponseEntity<Collection<Estado>> findAllJson() {

        List<Estado> estados = (List<Estado>) estadoDao.findAll();

        return ResponseEntity.ok(estados);
    }

}
