package io.github.dudursn.brasilapi.controllers;

import io.github.dudursn.brasilapi.daos.BankDao;
import io.github.dudursn.brasilapi.models.Bank;
import io.github.dudursn.brasilapi.services.BrasilApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;


@Controller
public class HomeController {

    @Autowired
    //Autowired = Diz ao compiador que instanciamos diretamente do repository sem precisar reescrever a mesma linha
    BankDao bankDao;

    @RequestMapping("/")
    public String index(){


        return "index";
    }

    @RequestMapping("/banks")
    public String banks(Model model){

        model.addAttribute("banks", bankDao.findAll());
        return "banks";
    }


    @RequestMapping("/popularBanks")
    public String populateBanks(){

        Bank[] banks = BrasilApiService.getBanks();
        bankDao.saveAll(Arrays.asList(banks));
        return "redirect:/banks";
    }


}
