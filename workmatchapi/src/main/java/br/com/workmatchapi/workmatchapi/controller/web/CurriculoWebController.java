package br.com.workmatchapi.workmatchapi.controller.web;


import br.com.workmatchapi.workmatchapi.model.entity.Curriculo;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.enums.Estado;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.repository.CurriculoRepository;
import br.com.workmatchapi.workmatchapi.model.repository.UsuarioRepository;
import br.com.workmatchapi.workmatchapi.model.service.CurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/curriculos")
public class CurriculoWebController {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String mostrarCurriculo(Model model) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);

        Curriculo curriculo;

        // Se já existe curriculo, usa o existente
        if (usuario.getCurriculo() != null) {
            curriculo = usuario.getCurriculo();
        } else {
            // senão cria um novo
            curriculo = new Curriculo();
            curriculo.setUsuario(usuario);
        }

        model.addAttribute("curriculo", curriculo);

        // enums para os selects
        model.addAttribute("formacoes", Formacao.values());
        model.addAttribute("niveisIngles", NivelIngles.values());
        model.addAttribute("estados", Estado.values());

        return "curriculo";
    }

    @PostMapping("/salvar")
    public String salvarCurriculo(@ModelAttribute Curriculo curriculo,
                                  @RequestParam String skillsString) {

        Curriculo existente = null;

        if (curriculo.getId() != null) {
            existente = curriculoRepository.findById(curriculo.getId()).orElse(null);
        }

        if (existente == null) {
            existente = new Curriculo();
            existente.setUsuario(curriculo.getUsuario());
        }

        existente.setFormacao(curriculo.getFormacao());
        existente.setExperiencia(curriculo.getExperiencia());
        existente.setNivelIngles(curriculo.getNivelIngles());
        existente.setEstado(curriculo.getEstado());

        // 🔥 LISTA MUTÁVEL
        List<String> novasSkills = Arrays.stream(skillsString.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        existente.setSkills(novasSkills);

        curriculoRepository.save(existente);

        return "redirect:/web/curriculos?sucesso";
    }

}
