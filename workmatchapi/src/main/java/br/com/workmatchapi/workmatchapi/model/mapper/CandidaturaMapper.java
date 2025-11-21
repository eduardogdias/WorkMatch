package br.com.workmatchapi.workmatchapi.model.mapper;

import br.com.workmatchapi.workmatchapi.model.dto.request.CandidaturaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.CandidaturaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.CurriculoResponseDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.UsuarioResponseDTO;
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


        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                entity.getCurriculo().getUsuario().getId(),
                entity.getCurriculo().getUsuario().getNome(),
                entity.getCurriculo().getUsuario().getEmail(),
                entity.getCurriculo().getUsuario().getTelefone(),
                entity.getCurriculo().getUsuario().getCpf(),
                entity.getCurriculo().getUsuario().getRole().name());

        CurriculoResponseDTO curriculoResponseDTO = new CurriculoResponseDTO(
                entity.getCurriculo().getId(),
                entity.getCurriculo().getFormacao(),
                entity.getCurriculo().getExperiencia(),
                entity.getCurriculo().getNivelIngles(),
                entity.getCurriculo().getEstado().getNome(),
                entity.getCurriculo().getSkills(),
                usuarioDTO);

        CandidaturaResponseDTO dto = new CandidaturaResponseDTO(entity.getId(), entity.getVaga(), curriculoResponseDTO, entity.getDataCandidatura());

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
