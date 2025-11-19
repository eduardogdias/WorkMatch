package br.com.workmatchapi.workmatchapi.controller.rest;

import br.com.workmatchapi.workmatchapi.model.dto.request.UsuarioLoginDTO;
import br.com.workmatchapi.workmatchapi.model.dto.request.UsuarioRequestDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.repository.UsuarioRepository;
import br.com.workmatchapi.workmatchapi.model.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UsuarioLoginDTO dto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UsuarioRequestDTO dto){

        if(usuarioRepository.findByEmail(dto.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dto.senha());

        Usuario entity = new Usuario(dto.nome(), dto.email(), dto.telefone(), dto.cpf(), senhaEncriptada, dto.role());
        usuarioRepository.save(entity);

        return ResponseEntity.ok().build();
    }

}
