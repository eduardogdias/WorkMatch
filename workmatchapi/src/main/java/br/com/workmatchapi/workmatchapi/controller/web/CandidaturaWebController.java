package br.com.workmatchapi.workmatchapi.controller.web;

import br.com.workmatchapi.workmatchapi.model.dto.response.CandidaturaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.service.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web/candidaturas")
public class CandidaturaWebController {

    @Autowired
    private CandidaturaService candidaturaService;

    @GetMapping
    public String listar(Model model){
        List<CandidaturaResponseDTO> candidaturas = candidaturaService.listAll();
        model.addAttribute("candidaturas", candidaturas);
        return "candidaturas";
    }



}
