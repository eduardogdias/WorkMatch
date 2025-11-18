package br.com.workmatchapi.workmatchapi.model.mapper;

import br.com.workmatchapi.workmatchapi.model.dto.request.EmpresaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.EmpresaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpresaMapper {

    public EmpresaResponseDTO toDTO(Empresa entity){
        EmpresaResponseDTO dto = new EmpresaResponseDTO(entity.getId(), entity.getNome(), entity.getCnpj(), entity.getEmail(), entity.getTelefone());
        return dto;
    }

    public Empresa toEntity(EmpresaRequestDTO dto){
        Empresa entity = new Empresa(dto.nome(), dto.cnpj(), dto.email(), dto.telefone());
        return entity;
    }


    // de lista de empresas - para lista de empresas DTO
    public List<EmpresaResponseDTO> toListDTO(List<Empresa> entities){
        List<EmpresaResponseDTO> dtos =  new ArrayList<>();

        for (Empresa entity : entities){
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
