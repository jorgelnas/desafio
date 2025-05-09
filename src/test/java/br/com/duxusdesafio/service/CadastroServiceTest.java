package br.com.duxusdesafio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class CadastroServiceTest {

    @Mock
    private TimeRepository timeRepo;

    @Mock
    private IntegranteRepository integranteRepo;

    @Mock
    private ComposicaoRepository composicaoRepo;

    @InjectMocks
    private CadastroService cadastroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarIntegranteNoTime() {
        Long timeId = 1L;
        Long integranteId = 1L;
        LocalDate data = LocalDate.now();

        Time time = new Time();
        Integrante integrante = new Integrante();

        when(timeRepo.findById(timeId)).thenReturn(Optional.of(time));
        when(integranteRepo.findById(integranteId)).thenReturn(Optional.of(integrante));

        cadastroService.cadastrarIntegranteNoTime(timeId, integranteId, data, "Líder");

        verify(composicaoRepo, times(1)).save(any(ComposicaoTime.class));
    }

    @Test
    void testCadastrarTimeComIntegrantes() {
        Long timeId = 1L;
        Time time = new Time();
        List<Long> integranteIds = List.of(1L, 2L);

        when(timeRepo.findById(timeId)).thenReturn(Optional.of(time));
        when(integranteRepo.findById(anyLong())).thenReturn(Optional.of(new Integrante()));

        cadastroService.cadastrarTimeComIntegrantes(timeId, integranteIds);

        verify(composicaoRepo, times(2)).save(any(ComposicaoTime.class));
    }
}
