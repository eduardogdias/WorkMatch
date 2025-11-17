package br.com.workmatchapi.workmatchapi.model.mapper;

import br.com.workmatchapi.workmatchapi.model.dto.request.VagaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.VagaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VagaMapper {

    public VagaResponseDTO toDTO(Vaga entity){
        VagaResponseDTO dto = new VagaResponseDTO(
                entity.getCargo(),
                entity.getDataFim(),
                entity.getExperiencia(),
                entity.getNivelIngles(),
                entity.getModeloTrabalho(),
                entity.getFormacao(),
                entity.getEstado(),
                entity.getMatch(),
                entity.getEmpresa());
        return dto;
    }


    public Vaga toEntity(VagaRequestDTO dto, Empresa empresa){
        Vaga entity = new Vaga(dto.cargo(), dto.dataFim(), dto.experiencia(), dto.nivelIngles(), dto.modeloTrabalho(), dto.formacao(), dto.estado(), dto.match(), empresa);
        return entity;
    }


    public List<VagaResponseDTO> toListDTO(List<Vaga> entities){
        List<VagaResponseDTO> dtos = new ArrayList<>();

        for (Vaga entity : entities){
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
