package br.com.workmatchapi.workmatchapi.model.mapper;

import br.com.workmatchapi.workmatchapi.model.dto.request.CurriculoRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.CurriculoResponseDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.UsuarioResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Curriculo;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurriculoMapper {

    public CurriculoResponseDTO toDTO(Curriculo entity){
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                entity.getUsuario().getId(),
                entity.getUsuario().getNome(),
                entity.getUsuario().getEmail(),
                entity.getUsuario().getTelefone(),
                entity.getUsuario().getCpf(),
                entity.getUsuario().getRole().name());

        CurriculoResponseDTO dto = new CurriculoResponseDTO(
                entity.getId(),
                entity.getFormacao(),
                entity.getExperiencia(),
                entity.getNivelIngles(),
                entity.getEstado().getNome(),
                entity.getSkills(),
                usuarioDTO);
        return dto;
    }

    public Curriculo toEntity(CurriculoRequestDTO dto, Usuario usuario){
        Curriculo entity = new Curriculo(dto.formacao(), dto.experiencia(), dto.nivelIngles(), dto.estado(), dto.skills(), usuario);
        return entity;
    }

    public List<CurriculoResponseDTO> toListDTO(List<Curriculo> entities){
        List<CurriculoResponseDTO> dtos =  new ArrayList<>();

        for (Curriculo entity : entities){
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
