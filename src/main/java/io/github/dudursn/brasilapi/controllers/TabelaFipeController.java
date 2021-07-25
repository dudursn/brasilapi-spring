package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.TabelaFipeDao;
import io.github.dudursn.brasilapi.models.TabelaFipe;
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
public class TabelaFipeController {

    @Autowired
    TabelaFipeDao tabelaFipeDao;

    private String mensagem = "";

    @RequestMapping("/populateTabelasFipe")
    public String populateTabelaFipe() {

        TabelaFipe[] tabelasFipe = BrasilApiService.getTabelasFipe();
        tabelaFipeDao.saveAll(Arrays.asList(tabelasFipe));
        this.mensagem = "Registros consumidos da API";
        return "redirect:/tabelasFipe";
    }

    @RequestMapping("/tabelasFipe")
    public String tabelasFipe(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("tabelasFipe", tabelaFipeDao.findAll());
        return "tabelasFipe";
    }


    @RequestMapping("/tabelasFipe/form")
    public String create(Model model) {

        model.addAttribute("tabelaFipe", new TabelaFipe());
        return "tabelasFipeForm";
    }

    @RequestMapping("/tabelasFipe/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<TabelaFipe> tabelaFipe = tabelaFipeDao.findById(id);
        model.addAttribute("tabelaFipe", tabelaFipe);
        return "tabelasFipeForm";
    }

    @GetMapping("/tabelasFipe/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        tabelaFipeDao.findById(id)
                .map(record -> {
                    tabelaFipeDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/tabelasFipe";
    }

    @PostMapping("/tabelasFipe/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<TabelaFipe> tabelasFipe = new ArrayList<TabelaFipe>();
        Optional<TabelaFipe> dado = tabelaFipeDao.findById(id);
        if(dado.isPresent()){

            tabelasFipe.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("tabelasFipe", tabelasFipe);
        return "tabelasFipe";
    }

    @PostMapping("/tabelasFipe/save")
    public String save(@Validated TabelaFipe tabelaFipe, BindingResult result) {

        if (result.hasErrors()) {
            return "tabelasFipeForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        tabelaFipeDao.save(tabelaFipe);
        return "redirect:/tabelasFipe";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/tabelasFipe/json")
    @ResponseBody
    public ResponseEntity<Collection<TabelaFipe>> findAllJson() {

        List<TabelaFipe> tabelasFipe = (List<TabelaFipe>) tabelaFipeDao.findAll();

        return ResponseEntity.ok(tabelasFipe);
    }




}
