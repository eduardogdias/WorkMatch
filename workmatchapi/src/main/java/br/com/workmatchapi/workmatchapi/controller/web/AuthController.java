package br.com.workmatchapi.workmatchapi.controller.web;

import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/login")
public class AuthController {

    @GetMapping
    public String loginPage() {
        return "login";
    }

}
