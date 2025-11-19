package br.com.workmatchapi.workmatchapi.controller.rest;

import br.com.workmatchapi.workmatchapi.model.dto.request.CurriculoRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.CurriculoResponseDTO;
import br.com.workmatchapi.workmatchapi.model.service.CurriculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curriculos")
public class CurriculoController {

    @Autowired
    public CurriculoService service;

    @GetMapping
    public ResponseEntity<List<CurriculoResponseDTO>> listar(){
        return ResponseEntity.ok().body(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurriculoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CurriculoResponseDTO> criar(@Valid @RequestBody CurriculoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurriculoResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody CurriculoRequestDTO dto){
        return ResponseEntity.ok().body(service.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CurriculoResponseDTO> deletar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.delete(id));
    }
}
