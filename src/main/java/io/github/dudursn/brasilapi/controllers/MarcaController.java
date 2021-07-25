package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.MarcaDao;
import io.github.dudursn.brasilapi.models.Marca;
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
public class MarcaController {

    @Autowired
    MarcaDao marcaDao;

    private String mensagem = "";

    @PostMapping("/populateMarcas")
    public String populateMarcas(@RequestParam("tipoVeiculo") String tipoVeiculo) {

        Marca[] marcas = BrasilApiService.getMarcas(tipoVeiculo);
        marcaDao.saveAll(Arrays.asList(marcas));
        this.mensagem = "Registros consumidos da API";
        return "redirect:/marcas";
    }

    @RequestMapping("/marcas")
    public String marcas(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("marcas", marcaDao.findAll());
        return "marcas";
    }


    @RequestMapping("/marcas/form")
    public String create(Model model) {

        model.addAttribute("marca", new Marca());
        return "marcasForm";
    }

    @RequestMapping("/marcas/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Marca> marca = marcaDao.findById(id);
        model.addAttribute("marca", marca);
        return "marcasForm";
    }

    @GetMapping("/marcas/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        marcaDao.findById(id)
                .map(record -> {
                    marcaDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/marcas";
    }

    @PostMapping("/marcas/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<Marca> marcas = new ArrayList<Marca>();
        Optional<Marca> dado = marcaDao.findById(id);
        if(dado.isPresent()){

            marcas.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("marcas", marcas);
        return "marcas";
    }

    @PostMapping("/marcas/save")
    public String save(@Validated Marca marca, BindingResult result) {

        if (result.hasErrors()) {
            return "marcasForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        marcaDao.save(marca);
        return "redirect:/marcas";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/marcas/json")
    @ResponseBody
    public ResponseEntity<Collection<Marca>> findAllJson() {

        List<Marca> marcas = (List<Marca>) marcaDao.findAll();

        return ResponseEntity.ok(marcas);
    }
}
