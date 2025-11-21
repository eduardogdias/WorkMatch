package br.com.workmatchapi.workmatchapi.controller.web;

import br.com.workmatchapi.workmatchapi.model.dto.response.CandidaturaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Candidatura;
import br.com.workmatchapi.workmatchapi.model.entity.Curriculo;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.repository.CandidaturaRepository;
import br.com.workmatchapi.workmatchapi.model.repository.CurriculoRepository;
import br.com.workmatchapi.workmatchapi.model.repository.UsuarioRepository;
import br.com.workmatchapi.workmatchapi.model.repository.VagaRepository;
import br.com.workmatchapi.workmatchapi.model.service.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/web/candidaturas")
public class CandidaturaWebController {

    @Autowired
    private CandidaturaService candidaturaService;

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private CandidaturaRepository candidaturaRepository;


    @GetMapping
    public String listar(Model model){
        List<CandidaturaResponseDTO> candidaturas = candidaturaService.listAll();
        model.addAttribute("candidaturas", candidaturas);
        return "candidaturas";
    }


    @PostMapping("/{vagaId}/inscrever")
    public String inscrever(@PathVariable Long vagaId,
                            @AuthenticationPrincipal Usuario usuarioLogado) {

        // Buscar o currículo do usuário logado


        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);


        // Se o usuário não tiver currículo, redireciona para criar
        if (usuario.getCurriculo() == null) {
            return "redirect:/web/curriculos";
        }


        Curriculo curriculo = usuario.getCurriculo();

        // Buscar a vaga
        Vaga vaga = vagaRepository.findById(vagaId).get();

        // Criar candidatura
        Candidatura candidatura = new Candidatura();
        candidatura.setVaga(vaga);
        candidatura.setCurriculo(curriculo);
        candidatura.setDataCandidatura(new Date(System.currentTimeMillis()));


        candidaturaRepository.save(candidatura);

        return "redirect:/web/vagas?sucesso";
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        candidaturaRepository.deleteById(id);

        return "redirect:/web/candidaturas";
    }



}
