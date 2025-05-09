package br.com.duxusdesafio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.duxusdesafio.dto.TimeDataResponse;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;

public class ApiServiceTest {

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private IntegranteRepository integranteRepository;

    @Mock
    private ComposicaoRepository composicaoRepository;

    @InjectMocks
    private ApiService apiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTimes() {
        List<Time> times = List.of(new Time(), new Time());
        when(timeRepository.findAll()).thenReturn(times);

        List<Time> result = apiService.getAllTimes();
        assertEquals(2, result.size());
    }

    @Test
    void testTimeDaData() {
        LocalDate data = LocalDate.now();
        Integrante integrante = new Integrante();
        integrante.setNome("Fulano");

        ComposicaoTime comp = new ComposicaoTime();
        comp.setIntegrante(integrante);

        Time time = new Time();
        time.setData(data);
        time.setComposicoes(List.of(comp));

        TimeDataResponse response = apiService.timeDaData(data, List.of(time));
        assertNotNull(response);
        assertEquals("Fulano", response.getIntegrantes().get(0));
    }

    @Test
    void testIntegranteMaisUsado() {
        Integrante i = new Integrante();
        when(integranteRepository.findByComposicoesTimeDataBetween(any(), any()))
            .thenReturn(List.of(i, i, i));

        Integrante result = apiService.integranteMaisUsado(LocalDate.now(), LocalDate.now(), List.of());
        assertEquals(i, result);
    }

    @Test
    void testFuncaoMaisComum() {
        Integrante i = new Integrante();
        i.setFuncao("Líder");
        when(integranteRepository.findByComposicoesTimeDataBetween(any(), any()))
            .thenReturn(List.of(i, i));

        String funcao = apiService.funcaoMaisComum(LocalDate.now(), LocalDate.now(), List.of());
        assertEquals("Líder", funcao);
    }

    @Test
    void testFrequenciaFranquiaMaisFamosa() {
        Integrante integrante = new Integrante();
        integrante.setFranquia("Marvel");
        ComposicaoTime comp = new ComposicaoTime();
        comp.setIntegrante(integrante);

        when(composicaoRepository.findByTimeDataBetween(any(), any()))
            .thenReturn(List.of(comp, comp, comp));

        String franquia = apiService.franquiaMaisFamosa(LocalDate.now(), LocalDate.now(), List.of());
        assertEquals("Marvel", franquia);
    }
}
