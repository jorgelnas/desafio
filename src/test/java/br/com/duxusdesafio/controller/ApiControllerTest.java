package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.*;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ApiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiController.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiService apiService;

    @Test
    void testFuncaoMaisComum() throws Exception {
        Mockito.when(apiService.getAllTimes()).thenReturn(List.of(new Time()));
        Mockito.when(apiService.funcaoMaisComum(any(), any(), anyList())).thenReturn("Goleiro");

        mockMvc.perform(get("/api/funcao-mais-comum"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Função").value("Goleiro"));
    }

    @Test
    void testFranquiaMaisFamosa() throws Exception {
        Mockito.when(apiService.getAllTimes()).thenReturn(List.of(new Time()));
        Mockito.when(apiService.franquiaMaisFamosa(any(), any(), anyList())).thenReturn("Marvel");

        mockMvc.perform(get("/api/franquia-mais-famosa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Marvel"));
    }

    @Test
    void testContagemPorFranquia() throws Exception {
        Mockito.when(apiService.getAllTimes()).thenReturn(List.of(new Time()));
        Map<String, Long> mockResult = new HashMap<>();
        mockResult.put("Marvel", 5L);
        Mockito.when(apiService.contagemPorFranquia(any(), any(), anyList())).thenReturn(mockResult);

        mockMvc.perform(get("/api/contagem-por-franquia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Marvel").value(5));
    }

    @Test
    void testContagemPorFuncao() throws Exception {
        Mockito.when(apiService.getAllTimes()).thenReturn(List.of(new Time()));
        Map<String, Long> mockResult = new HashMap<>();
        mockResult.put("Goleiro", 3L);
        Mockito.when(apiService.contagemPorFuncao(any(), any(), anyList())).thenReturn(mockResult);

        mockMvc.perform(get("/api/contagem-por-funcao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Goleiro").value(3));
    }
}
