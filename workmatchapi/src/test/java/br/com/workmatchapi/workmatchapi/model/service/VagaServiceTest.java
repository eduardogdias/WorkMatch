package br.com.workmatchapi.workmatchapi.model.service;


import br.com.workmatchapi.workmatchapi.model.dto.request.VagaRequestDTO;
import br.com.workmatchapi.workmatchapi.model.dto.response.VagaResponseDTO;
import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import br.com.workmatchapi.workmatchapi.model.enums.*;
import br.com.workmatchapi.workmatchapi.model.exception.EntidadeNaoEncontrada;
import br.com.workmatchapi.workmatchapi.model.mapper.VagaMapper;
import br.com.workmatchapi.workmatchapi.model.repository.UsuarioRepository;
import br.com.workmatchapi.workmatchapi.model.repository.VagaRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class VagaServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private VagaRepository repository;

    @Mock
    private VagaMapper mapper;

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private VagaService service;

    private Vaga vaga;
    private Empresa empresa;
    private VagaRequestDTO dto;
    private VagaResponseDTO response;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNome("Empresa Teste");

        vaga = new Vaga(
                "Dev Java", "O profissional deverá atuar principalmente com java", 5000.0, new Date(), 2,
                NivelIngles.BASICO, ModeloTrabalho.PRESENCIAL,
                Formacao.GRADUACAO, Estado.SP, 80, List.of("Java", "Spring"), empresa
        );
        vaga.setId(10L);

        dto = new VagaRequestDTO(
                "Dev Java",
                "O profissional deverá atuar principalmente com java",
                5000.0,
                new Date(),
                2,
                NivelIngles.BASICO,
                ModeloTrabalho.PRESENCIAL,
                Formacao.GRADUACAO,
                Estado.SP,
                80,
                List.of("Java", "Spring"),
                1L
        );

        response = new VagaResponseDTO(
                10L, "Dev Java", "O profissional deverá atuar principalmente com java",5000.0, new Date(), 2,
                NivelIngles.BASICO, ModeloTrabalho.PRESENCIAL,
                Formacao.GRADUACAO, "São Paulo", 80,
                List.of("Java", "Spring"), empresa
        );
    }

    // list all
    @Test
    void testList_ReturnsList() {
        when(repository.findAll()).thenReturn(List.of(vaga));
        when(mapper.toListDTO(List.of(vaga))).thenReturn(List.of(response));

        List<VagaResponseDTO> result = service.list();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    // list page
    @Test
    void testListPaginado_ReturnsPagedList() {
        Page<Vaga> page = new PageImpl<>(List.of(vaga));
        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(page);
        when(mapper.toDTO(vaga)).thenReturn(response);

        List<VagaResponseDTO> result = service.list(0, 10);

        assertEquals(1, result.size());
    }

    // list by model
    @Test
    void testListPorModelo_ReturnsFilteredList() {
        when(repository.findByModeloTrabalho(ModeloTrabalho.PRESENCIAL))
                .thenReturn(List.of(vaga));
        when(mapper.toListDTO(List.of(vaga)))
                .thenReturn(List.of(response));

        List<VagaResponseDTO> result = service.list(ModeloTrabalho.PRESENCIAL);

        assertEquals(1, result.size());
    }

    // find by id
    @Test
    void testFindById_ReturnsDTO() {
        when(repository.findById(10L)).thenReturn(Optional.of(vaga));
        when(mapper.toDTO(vaga)).thenReturn(response);

        VagaResponseDTO result = service.findById(10L);

        assertEquals("Dev Java", result.cargo());
    }

    // find by id throws exception
    @Test
    void testFindById_ThrowsException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontrada.class, () -> service.findById(99L));
    }

    // save
    @Test
    void testSave_Success() {
        when(empresaService.findEntityById(1L)).thenReturn(empresa);
        when(mapper.toEntity(dto, empresa)).thenReturn(vaga);
        when(repository.save(vaga)).thenReturn(vaga);
        when(mapper.toDTO(vaga)).thenReturn(response);

        VagaResponseDTO result = service.save(dto);

        assertEquals("Dev Java", result.cargo());
        verify(repository).save(vaga);
    }

    // edit
    @Test
    void testEdit_Success() {
        when(empresaService.findEntityById(1L)).thenReturn(empresa);
        when(repository.findById(10L)).thenReturn(Optional.of(vaga));
        when(repository.save(vaga)).thenReturn(vaga);
        when(mapper.toDTO(vaga)).thenReturn(response);

        VagaResponseDTO result = service.edit(10L, dto);

        assertEquals(80, result.match());
        verify(repository).save(vaga);
    }

    // delete
    @Test
    void testDelete_Success() {
        when(repository.findById(10L)).thenReturn(Optional.of(vaga));
        doNothing().when(repository).deleteById(10L);
        when(mapper.toDTO(vaga)).thenReturn(response);

        VagaResponseDTO result = service.delete(10L);

        assertEquals("Dev Java", result.cargo());
        verify(repository).deleteById(10L);
    }
}
