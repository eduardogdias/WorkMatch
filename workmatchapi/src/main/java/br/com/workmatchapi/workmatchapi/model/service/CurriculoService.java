package br.com.workmatchapi.workmatchapi.model.service;

import br.com.workmatchapi.workmatchapi.model.dto.request.CurriculoRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.CurriculoResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Curriculo;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.mapper.CurriculoMapper;
import br.com.workmatchapi.workmatchapi.model.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculoService {
    
    @Autowired
    private CurriculoRepository repository;

    @Autowired
    private CurriculoMapper mapper;

    @Autowired
    public UsuarioService usuarioService;

    public List<CurriculoResponseDTO> listAll(){
        List<Curriculo> entities = repository.findAll();
        return mapper.toListDTO(entities);
    }

    public Curriculo findEntityById(Long id){
        Optional<Curriculo> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new EntidadeNaoEncontrada("Currículo não encontrado");
        }
        return entity.get();
    }


    public CurriculoResponseDTO findById(Long id){
        return mapper.toDTO(findEntityById(id));
    }

    public CurriculoResponseDTO save(CurriculoRequestDTO dto){
        Usuario usuario = usuarioService.findEntityById(dto.usuarioId());
        return mapper.toDTO(repository.save(mapper.toEntity(dto, usuario)));
    }

    public CurriculoResponseDTO edit(Long id, CurriculoRequestDTO dto){
        Usuario usuario = usuarioService.findEntityById(dto.usuarioId());
        Curriculo entity = findEntityById(id);
        entity.setFormacao(dto.formacao());
        entity.setExperiencia(dto.experiencia());
        entity.setNivelIngles(dto.nivelIngles());
        entity.setEstado(dto.estado());
        entity.setUsuario(usuario);

        return mapper.toDTO(repository.save(entity));
    }

    public CurriculoResponseDTO delete(Long id){
        Curriculo entity = findEntityById(id);
        repository.deleteById(id);
        return mapper.toDTO(entity);
    }
    
}
