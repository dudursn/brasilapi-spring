package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.BankDao;
import io.github.dudursn.brasilapi.models.Bank;
import io.github.dudursn.brasilapi.services.BrasilApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Controller
public class BankController {

    @Autowired
    //Autowired = Diz ao compiador que instanciamos diretamente do repository sem precisar reescrever a mesma linha
    BankDao bankDao;

    private String mensagem = "";

    @RequestMapping("/banks")
    public String banks(Model model){

        if(this.mensagem!=""){
            model.addAttribute("mensagem", this.mensagem);
            this.mensagem = "";
        }

        model.addAttribute("banks", bankDao.findAll());
        return "banks";
    }


    @RequestMapping("/banks/form/{id}")
    public String banksForm(Model model){

        model.addAttribute("bank", new Bank());
        return "banksForm";
    }
    /*
    @RequestMapping("/banks/form/{id}")

    public String banksEditForm(@PathVariable("id")  long id, Model model){

        Optional<Bank> bank = bankDao.findById(id);

        model.addAttribute("bank", bank);
        return "banksForm";
    }
    */


    @RequestMapping("/popularBanks")
    public String popularBanks(){

        Bank[] banks = BrasilApiService.getBanks();
        bankDao.saveAll(Arrays.asList(banks));
        this.mensagem = "Registros consumidos da API";
        return "redirect:/banks";
    }

    @GetMapping("/banks/delete/{id}")
    public String delete(@PathVariable("id")  long id, Model model) {


        bankDao.findById(id)
                .map(record -> {
                    bankDao.deleteById(id);

                    return 1;
                });

        this.mensagem = "Registro excluido";
        return "redirect:/banks";
    }

    @PutMapping("/banks/save/{id}")
    public String update(@PathVariable("id") long id,
                                 @RequestBody Bank bank) {
        int result =  bankDao.findById(id)
                .map(record -> {
                    record.setName(bank.getName());
                    record.setIspb(bank.getIspb());
                    record.setCode(bank.getCode());
                    record.setFullName(bank.getFullName());
                    Bank updated = bankDao.save(record);
                    return 1;
                }).orElse(0);

        return "redirect:/banks";

    }

    @PostMapping("/banks/save")
    public String save(@Validated Bank bank, BindingResult result){

        if (result.hasErrors()){
            return "banksForm";
        }
        this.mensagem = "Registro salvo com sucesso";
        bankDao.save(bank);
        return "redirect:/banks";
    }


    /**Listar todos em formato json */
    @GetMapping("/banks/json")
    @ResponseBody
    public ResponseEntity<Collection<Bank>> findAllJson() {

        List<Bank> banks = (List<Bank>) bankDao.findAll();

        return ResponseEntity.ok(banks);
    }


}
