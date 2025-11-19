package br.com.workmatchapi.workmatchapi.model.service;

import br.com.workmatchapi.workmatchapi.model.dto.request.EmpresaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.EmpresaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.mapper.EmpresaMapper;
import br.com.workmatchapi.workmatchapi.model.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private EmpresaMapper mapper;

    
    public List<EmpresaResponseDTO> listAll(){
        List<Empresa> entities = repository.findAll();
        return mapper.toListDTO(entities);
    }

    public Empresa findEntityById(Long id){
        Optional<Empresa> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new EntidadeNaoEncontrada("Empresa não encontrada");
        }
        return entity.get();
    }


    public EmpresaResponseDTO findById(Long id){
        return mapper.toDTO(findEntityById(id));
    }

    public EmpresaResponseDTO save(EmpresaRequestDTO dto){
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public EmpresaResponseDTO edit(Long id, EmpresaRequestDTO dto){
        Empresa entity = findEntityById(id);
        entity.setNome(dto.nome());
        entity.setCnpj(dto.cnpj());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());

        return mapper.toDTO(repository.save(entity));
    }

    public EmpresaResponseDTO delete(Long id){
        Empresa entity = findEntityById(id);
        repository.deleteById(id);
        return mapper.toDTO(entity);
    }

}
