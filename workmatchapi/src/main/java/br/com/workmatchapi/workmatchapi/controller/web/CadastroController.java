package br.com.workmatchapi.workmatchapi.controller.web;

import br.com.workmatchapi.workmatchapi.model.dto.request.UsuarioRequestDTO;
import br.com.workmatchapi.workmatchapi.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String loginPage() {
        return "cadastro";
    }

    @PostMapping
    public String cadastroConta(Model model, UsuarioRequestDTO dto){
        usuarioService.save(dto);
        return "redirect:/web/login?sucesso";
    }
}
