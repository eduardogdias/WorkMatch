package br.com.workmatchapi.workmatchapi.model.service;

import br.com.workmatchapi.workmatchapi.model.dto.request.VagaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.VagaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.mapper.VagaMapper;
import br.com.workmatchapi.workmatchapi.model.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

    @Autowired
    private VagaMapper mapper;

    @Autowired
    private EmpresaService empresaService;

    public List<VagaResponseDTO> listaAll(){
        List<Vaga> entities = repository.findAll();
        return mapper.toListDTO(entities);
    }

    public Vaga findEntityById(Long id){
        Optional<Vaga> vaga = repository.findById(id);
        if(vaga.isEmpty()){
            throw new EntidadeNaoEncontrada("Vaga não encontrada");
        }
        return vaga.get();
    }

    public VagaResponseDTO findById(Long id){
        return mapper.toDTO(findEntityById(id));
    }

    public VagaResponseDTO save(VagaRequestDTO dto){
        Empresa empresa = empresaService.findEntityById(dto.empresaId());
        return mapper.toDTO(repository.save(mapper.toEntity(dto, empresa)));
    }

    public VagaResponseDTO edit(Long id, VagaRequestDTO dto){
        Empresa empresa = empresaService.findEntityById(dto.empresaId());
        System.out.println(empresa.getId() +" --- "+ empresa.getNome());
        Vaga entity = findEntityById(id);
        entity.setCargo(dto.cargo());
        entity.setDataFim(dto.dataFim());
        entity.setExperiencia(dto.experiencia());
        entity.setNivelIngles(dto.nivelIngles());
        entity.setModeloTrabalho(dto.modeloTrabalho());
        entity.setFormacao(dto.formacao());
        entity.setEstado(dto.estado());
        entity.setMatch(dto.match());
        entity.setEmpresa(empresa);

        return mapper.toDTO(repository.save(entity));
    }

    public VagaResponseDTO delete(Long id){
        Vaga entity = findEntityById(id);
        repository.deleteById(id);
        return mapper.toDTO(entity);
    }
}
