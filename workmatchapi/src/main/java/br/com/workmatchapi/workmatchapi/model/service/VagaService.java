package br.com.workmatchapi.workmatchapi.model.service;

import br.com.workmatchapi.workmatchapi.model.dto.request.EmailRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.request.VagaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.VagaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import br.com.workmatchapi.workmatchapi.model.enums.ModeloTrabalho;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.mapper.VagaMapper;
import br.com.workmatchapi.workmatchapi.model.repository.UsuarioRepository;
import br.com.workmatchapi.workmatchapi.model.repository.VagaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

    @Autowired
    private VagaMapper mapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaService empresaService;


    @Value("${spring.mail.username}")
    private String emailDe;

    @Value("${spring.rabbitmq.queue}")
    private String nomeFila;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public List<VagaResponseDTO> list(){
        return mapper.toListDTO(repository.findAll());
    }

    public List<VagaResponseDTO> list(Integer pagina, Integer itens){
        Page<Vaga> paginaEntities = repository.findAll(PageRequest.of(pagina, itens));
        return paginaEntities.map(mapper::toDTO).getContent();
    }

    public List<VagaResponseDTO> list(ModeloTrabalho modelo){
        return mapper.toListDTO(repository.findByModeloTrabalho(modelo));
    }


    public Vaga findEntityById(Long id){
        Optional<Vaga> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new EntidadeNaoEncontrada("Vaga não encontrada");
        }
        return entity.get();
    }

    public VagaResponseDTO findById(Long id){
        return mapper.toDTO(findEntityById(id));
    }

    public VagaResponseDTO save(VagaRequestDTO dto){
        Empresa empresa = empresaService.findEntityById(dto.empresaId());

        Vaga vaga = repository.save(mapper.toEntity(dto, empresa));




        List<Usuario> usuarios = usuarioRepository.findAll();

        usuarios.forEach(usuario -> {
            EmailRequestDTO email = new EmailRequestDTO();
            email.setOwnerRef("workmatchapi-" + vaga.getId());
            email.setEmailFrom(emailDe);
            email.setEmailTo(usuario.getEmail());
            email.setSubject("Nova vaga disponível: " + vaga.getCargo());
            email.setText("Olá, " + usuario.getNome() + "! Temos uma nova oportunidade para você. Acesse a plataforma e confira :)");

            System.out.println("Nome fila: " + nomeFila);
            System.out.println("Email de: " + emailDe);
            System.out.println("Nome usu: " + usuario.getNome());
            System.out.println("Email usu: " + usuario.getEmail());
            System.out.println("=======================");

            try {
                // converter a entidade para JSON e enviar para o rabbit
                ObjectMapper mapperJson = new ObjectMapper();
                String json = mapperJson.writeValueAsString(email);

                Message message = MessageBuilder
                        .withBody(json.getBytes(StandardCharsets.UTF_8))
                        .setContentType("application/json")
                        .build();

                rabbitTemplate.send(nomeFila, message);


               // rabbitTemplate.convertAndSend(nomeFila, json);

            } catch (Exception e) {
                System.out.println("Erro ao converter entidade para JSON");
                e.printStackTrace();
            }
        });


        return mapper.toDTO(vaga);
    }

    public VagaResponseDTO edit(Long id, VagaRequestDTO dto){
        Empresa empresa = empresaService.findEntityById(dto.empresaId());
        Vaga entity = findEntityById(id);
        entity.setCargo(dto.cargo());
        entity.setDescricao(dto.descricao());
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
