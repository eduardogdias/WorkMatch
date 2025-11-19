package br.com.workmatchapi.workmatchapi.model.service;

import br.com.workmatchapi.workmatchapi.model.dto.request.CandidaturaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.CandidaturaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Candidatura;
import br.com.workmatchapi.workmatchapi.model.entity.Curriculo;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.mapper.CandidaturaMapper;
import br.com.workmatchapi.workmatchapi.model.repository.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CandidaturaService {
    
    @Autowired
    private CandidaturaRepository repository;

    @Autowired
    private CandidaturaMapper mapper;

    @Autowired
    private VagaService vagaService;

    @Autowired
    public CurriculoService curriculoService;


    public List<CandidaturaResponseDTO> listAll(){
        List<Candidatura> entities = repository.findAll();
        return mapper.toListDTO(entities);
    }

    public Candidatura findEntityById(Long id){
        Optional<Candidatura> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new EntidadeNaoEncontrada("Candidatura não encontrada");
        }
        return entity.get();
    }


    public CandidaturaResponseDTO findById(Long id){
        return mapper.toDTO(findEntityById(id));
    }

    public CandidaturaResponseDTO save(CandidaturaRequestDTO dto){
        Vaga vaga = vagaService.findEntityById(dto.vagaId());
        Curriculo curriculo = curriculoService.findEntityById(dto.curriculoId());   // se não achar nenhum dos dois, vai lançar a exception de cada um

        Candidatura entity = mapper.toEntity(vaga, curriculo);
        entity.setDataCandidatura(new Date(System.currentTimeMillis()));

        return mapper.toDTO(repository.save(entity));
    }

    public CandidaturaResponseDTO edit(Long id, CandidaturaRequestDTO dto){
        Vaga vaga = vagaService.findEntityById(dto.vagaId());
        Curriculo curriculo = curriculoService.findEntityById(dto.curriculoId());

        Candidatura entity = findEntityById(id);

        entity.setVaga(vaga);
        entity.setCurriculo(curriculo);
        // a data não é possível alterar, pois é a data que a pessoa se candidatou

        return mapper.toDTO(repository.save(entity));
    }

    public CandidaturaResponseDTO delete(Long id){
        Candidatura entity = findEntityById(id);
        repository.deleteById(id);
        return mapper.toDTO(entity);
    }
    
}
