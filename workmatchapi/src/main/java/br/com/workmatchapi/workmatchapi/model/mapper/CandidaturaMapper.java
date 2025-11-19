package br.com.workmatchapi.workmatchapi.model.mapper;

import br.com.workmatchapi.workmatchapi.model.dto.request.CandidaturaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.CandidaturaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Candidatura;
import br.com.workmatchapi.workmatchapi.model.entity.Curriculo;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CandidaturaMapper {

    public CandidaturaResponseDTO toDTO(Candidatura entity){
        CandidaturaResponseDTO dto = new CandidaturaResponseDTO(entity.getId(), entity.getVaga(), entity.getCurriculo(), entity.getDataCandidatura());
        return dto;
    }

    public Candidatura toEntity(Vaga vaga, Curriculo curriculo){
        Candidatura entity = new Candidatura(vaga, curriculo);
        return entity;
    }

    public List<CandidaturaResponseDTO> toListDTO(List<Candidatura> entities){
        List<CandidaturaResponseDTO> dtos =  new ArrayList<>();

        for (Candidatura entity : entities){
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
