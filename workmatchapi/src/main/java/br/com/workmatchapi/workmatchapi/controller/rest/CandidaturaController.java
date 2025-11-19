package br.com.workmatchapi.workmatchapi.controller.rest;

import br.com.workmatchapi.workmatchapi.model.dto.request.CandidaturaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.CandidaturaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.service.CandidaturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidaturas")
public class CandidaturaController {

    @Autowired
    public CandidaturaService service;

    @GetMapping
    public ResponseEntity<List<CandidaturaResponseDTO>> listar(){
        return ResponseEntity.ok().body(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidaturaResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CandidaturaResponseDTO> criar(@Valid @RequestBody CandidaturaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidaturaResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody CandidaturaRequestDTO dto){
        return ResponseEntity.ok().body(service.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CandidaturaResponseDTO> deletar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.delete(id));
    }
}
