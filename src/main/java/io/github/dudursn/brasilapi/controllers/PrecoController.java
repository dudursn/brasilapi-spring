package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.PrecoDao;
import io.github.dudursn.brasilapi.models.Preco;
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
public class PrecoController {

    @Autowired
    PrecoDao precoDao;

    private String mensagem = "";

    @PostMapping("/populatePrecos")
    public String populatePrecos(@RequestParam("codigoFipe") String codigoFipe) {

        Preco[] precos = BrasilApiService.getPrecos(codigoFipe);
        this.mensagem = "Registro não encontrado";

        if(precos[0]!=null && precos[0].getMarca()!=null){

            precoDao.saveAll(Arrays.asList(precos));
            this.mensagem = "Registros consumidos da API";
        }

        return "redirect:/precos";
    }

    @RequestMapping("/precos")
    public String precos(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("precos", precoDao.findAll());
        return "precos";
    }


    @RequestMapping("/precos/form")
    public String create(Model model) {

        model.addAttribute("preco", new Preco());
        return "precosForm";
    }

    @RequestMapping("/precos/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Preco> preco = precoDao.findById(id);
        model.addAttribute("preco", preco);
        return "precosForm";
    }

    @GetMapping("/precos/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        precoDao.findById(id)
                .map(record -> {
                    precoDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/precos";
    }

    @PostMapping("/precos/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<Preco> precos = new ArrayList<Preco>();
        Optional<Preco> dado = precoDao.findById(id);
        if(dado.isPresent()){

            precos.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("precos", precos);
        return "/precos";
    }

    @PostMapping("/precos/save")
    public String save(@Validated Preco preco, BindingResult result) {

        if (result.hasErrors()) {
            return "precosForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        precoDao.save(preco);
        return "redirect:/precos";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/precos/json")
    @ResponseBody
    public ResponseEntity<Collection<Preco>> findAllJson() {

        List<Preco> precos = (List<Preco>) precoDao.findAll();

        return ResponseEntity.ok(precos);
    }


}
