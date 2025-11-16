package br.com.workmatchapi.workmatchapi.controller.rest;

import br.com.workmatchapi.workmatchapi.model.dto.request.EmpresaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.EmpresaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    public EmpresaService service;

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listar(){
        return ResponseEntity.ok().body(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> criar(@Valid @RequestBody EmpresaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> editar(@PathVariable Long id, @Valid @RequestBody EmpresaRequestDTO dto){
        return ResponseEntity.ok().body(service.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> deletar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.delete(id));
    }
}
