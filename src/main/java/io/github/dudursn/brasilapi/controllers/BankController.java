package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.BankDao;
import io.github.dudursn.brasilapi.models.Bank;
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
public class BankController {

    @Autowired
    BankDao bankDao;

    private String mensagem = "";


    @RequestMapping("/populateBanks")
    public String populateBanks() {

        Bank[] banks = BrasilApiService.getBanks();
        bankDao.saveAll(Arrays.asList(banks));
        this.mensagem = "Registros consumidos da API";
        return "redirect:/banks";
    }

    @RequestMapping("/banks")
    public String banks(Model model) {

        if (this.mensagem != "") {
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("banks", bankDao.findAll());
        return "banks";
    }


    @RequestMapping("/banks/form")
    public String create(Model model) {

        model.addAttribute("bank", new Bank());
        return "banksForm";
    }

    @RequestMapping("/banks/form/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Bank> bank = bankDao.findById(id);
        model.addAttribute("bank", bank);
        return "banksForm";
    }

    @GetMapping("/banks/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {


        bankDao.findById(id)
                .map(record -> {
                    bankDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro apagado com sucesso";
        return "redirect:/banks";
    }

    @PostMapping("/banks/search")
    public String search(@RequestParam("busca") String busca, Model model) {

        long id = Util.StringToLong(busca);
        List<Bank> banks = new ArrayList<Bank>();
        Optional<Bank> dado = bankDao.findById(id);
        if(dado.isPresent()){

            banks.add(dado.get());
            model.addAttribute("mensagem", "Resultado para o id: " + id);
        }else{

            model.addAttribute("mensagem", "Não encontrado!");
        }
        model.addAttribute("banks", banks);
        return "banks";
    }

    @PostMapping("/banks/save")
    public String save(@Validated Bank bank, BindingResult result) {

        if (result.hasErrors()) {
            return "banksForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        bankDao.save(bank);
        return "redirect:/banks";
    }



    /**
     * Listar os dados no formato json
     */
    @GetMapping("/banks/json")
    @ResponseBody
    public ResponseEntity<Collection<Bank>> findAllJson() {

        List<Bank> banks = (List<Bank>) bankDao.findAll();

        return ResponseEntity.ok(banks);
    }




}
