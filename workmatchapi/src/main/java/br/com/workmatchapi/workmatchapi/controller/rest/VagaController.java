package br.com.workmatchapi.workmatchapi.controller.rest;

import br.com.workmatchapi.workmatchapi.model.dto.request.VagaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.VagaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vagas")
public class VagaController {

    @Autowired
    private VagaService service;

    @GetMapping
    public ResponseEntity<List<VagaResponseDTO>> listar(){
        return ResponseEntity.ok(service.listaAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<VagaResponseDTO> criar(@Valid @RequestBody VagaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagaResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody VagaRequestDTO dto){
        return ResponseEntity.ok().body(service.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VagaResponseDTO> deletar(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
