package br.com.workmatchapi.workmatchapi.model.mapper;

import br.com.workmatchapi.workmatchapi.model.dto.request.UsuarioRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.UsuarioResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO toDTO(Usuario entity){
        UsuarioResponseDTO dto = new UsuarioResponseDTO(entity.getId(), entity.getNome(), entity.getEmail(), entity.getTelefone(), entity.getCpf(), entity.getRole().name());
        return dto;
    }

    public Usuario toEntity(UsuarioRequestDTO dto){
        Usuario entity = new Usuario(dto.nome(), dto.email(), dto.telefone(), dto.cpf(), dto.senha(), dto.role());
        return entity;
    }

    public List<UsuarioResponseDTO> toListDTO(List<Usuario> entities){
        List<UsuarioResponseDTO> dtos =  new ArrayList<>();

        for (Usuario entity : entities){
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
}
