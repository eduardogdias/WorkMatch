package br.com.workmatchapi.workmatchapi.controller.rest;

import br.com.workmatchapi.workmatchapi.model.dto.request.VagaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.VagaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.enums.ModeloTrabalho;
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
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/paginacao")
    public ResponseEntity<List<VagaResponseDTO>> listarPaginacao(@RequestParam(required = true) Integer pagina, @RequestParam(required = true) Integer itens){
        return ResponseEntity.ok(service.list(pagina, itens));
    }

    @GetMapping("/trabalho")
    public ResponseEntity<List<VagaResponseDTO>> listarModelo(@RequestParam(required = true) ModeloTrabalho modelo){
        return ResponseEntity.ok(service.list(modelo));
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
