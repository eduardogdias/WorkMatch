package br.com.workmatchapi.workmatchapi.controller.web;

import br.com.workmatchapi.workmatchapi.model.dto.response.VagaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Curriculo;
import br.com.workmatchapi.workmatchapi.model.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web/vagas")
public class VagaWebController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public String listar(Model model){
        List<VagaResponseDTO> vagas = vagaService.list();
        model.addAttribute("vagas", vagas);
        return "vagas";
    }



}
