package br.com.workmatchapi.workmatchapi.controller.rest;

import br.com.workmatchapi.workmatchapi.model.dto.request.UsuarioRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.UsuarioResponseDTO;
import br.com.workmatchapi.workmatchapi.model.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar(){
        return ResponseEntity.ok().body(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.ok().body(service.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> deletar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.delete(id));
    }
}
