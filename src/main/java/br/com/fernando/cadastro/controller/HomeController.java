/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author fernando
 */
@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "redirect:/index.html"; 
    }
}
