package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.FranquiaMaisFamosaResponse;
import br.com.duxusdesafio.dto.FuncaoMaisComumResponse;
import br.com.duxusdesafio.dto.IntegranteMaisUsadoResponse;
import br.com.duxusdesafio.dto.TimeDataResponse;
import br.com.duxusdesafio.dto.TimeMaisComumResponse;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controlador responsável por expor endpoints da API relacionados a dados
 * de times de futebol, integrantes, funções e franquias.
 * 
 * <p>Todos os endpoints podem filtrar informações por data inicial e final,
 * permitindo análise estatística ao longo do tempo.</p>
 * 
 * <p>As operações expostas envolvem busca de dados mais frequentes, contagens,
 * e filtros baseados na data de composição dos times.</p>
 * 
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * Retorna o time correspondente à data informada.
     *
     * @param data Data para buscar o time.
     * @return TimeDataResponse com informações do time da data ou 404 se não encontrado.
     */
    @Operation(summary = "Obter time de uma data específica", description = "Retorna o time correspondente à data informada.")
    @GetMapping("/time-da-data")
    public ResponseEntity<?> timeDaData(
            @Parameter(description = "Data desejada", required = true) @RequestParam LocalDate data) {

        List<Time> todosOsTimes = apiService.getAllTimes();
        TimeDataResponse timeDataResponse = apiService.timeDaData(data, todosOsTimes);
        return ResponseEntity.ok(timeDataResponse);
    }


    /**
     * Retorna o integrante mais usado no intervalo de datas especificado.
     *
     * @param dataInicial Data inicial do intervalo (opcional).
     * @param dataFinal   Data final do intervalo (opcional).
     * @return IntegranteMaisUsadoResponse com os dados do integrante.
     */
    @Operation(summary = "Obter o integrante mais usado", description = "Retorna o integrante mais usado no intervalo de datas (opcional).")
    @GetMapping("/integrante-mais-usado")
    public ResponseEntity<?> integranteMaisUsado(
            @Parameter(description = "Data inicial do intervalo") @RequestParam(required = false) LocalDate dataInicial,
            @Parameter(description = "Data final do intervalo") @RequestParam(required = false) LocalDate dataFinal) {

        List<Time> todosOsTimes = apiService.getAllTimes();
        Integrante integrante = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);
        IntegranteMaisUsadoResponse integranteMaisUsadoResponse = 
        		new IntegranteMaisUsadoResponse(integrante.getId(), integrante.getNome(), integrante.getFuncao(), integrante.getComposicoes().size());
        return ResponseEntity.ok(integranteMaisUsadoResponse);
    }

    /**
     * Retorna os integrantes do time mais comum (mais recorrente) no intervalo informado.
     *
     * @param dataInicial Data inicial do intervalo (opcional).
     * @param dataFinal   Data final do intervalo (opcional).
     * @return TimeMaisComumResponse com os dados do time.
     */
    @Operation(summary = "Obter integrantes do time mais comum", description = "Retorna os integrantes do time mais recorrente no intervalo de datas (opcional).")
    @GetMapping("/time-mais-comum")
    public ResponseEntity<?> timeMaisComum(
            @Parameter(description = "Data inicial do intervalo") @RequestParam(required = false) LocalDate dataInicial,
            @Parameter(description = "Data final do intervalo") @RequestParam(required = false) LocalDate dataFinal) {

        List<Time> todosOsTimes = apiService.getAllTimes();
        Time timeMaisComum = apiService.timeMaisComum(dataInicial, dataFinal, todosOsTimes);
        TimeMaisComumResponse timeMaisComumResponse = new TimeMaisComumResponse(timeMaisComum.getId(), timeMaisComum.getNome(), timeMaisComum.getComposicoes().size());
        return ResponseEntity.ok(timeMaisComumResponse);
    }

    /**
     * Retorna a função mais comum entre os integrantes no intervalo informado.
     *
     * @param dataInicial Data inicial do intervalo (opcional).
     * @param dataFinal   Data final do intervalo (opcional).
     * @return FuncaoMaisComumResponse com a função mais frequente.
     */
    @Operation(summary = "Obter a função mais comum", description = "Retorna a função mais comum no intervalo de datas (opcional).")
    @GetMapping("/funcao-mais-comum")
    public ResponseEntity<?> funcaoMaisComum(
            @Parameter(description = "Data inicial do intervalo") @RequestParam(required = false) LocalDate dataInicial,
            @Parameter(description = "Data final do intervalo") @RequestParam(required = false) LocalDate dataFinal) {

        List<Time> todosOsTimes = apiService.getAllTimes();
        FuncaoMaisComumResponse comumResponse = new FuncaoMaisComumResponse(apiService.funcaoMaisComum(dataInicial, dataFinal, todosOsTimes));
        return ResponseEntity.ok(comumResponse);
    }

    /**
     * Retorna a franquia mais famosa (mais recorrente) no intervalo informado.
     *
     * @param dataInicial Data inicial do intervalo (opcional).
     * @param dataFinal   Data final do intervalo (opcional).
     * @return FranquiaMaisFamosaResponse com o nome da franquia.
     */
    @Operation(summary = "Obter a franquia mais famosa", description = "Retorna a franquia mais famosa no intervalo de datas (opcional).")
    @GetMapping("/franquia-mais-famosa")
    public ResponseEntity<?> franquiaMaisFamosa(
            @Parameter(description = "Data inicial do intervalo") @RequestParam(required = false) LocalDate dataInicial,
            @Parameter(description = "Data final do intervalo") @RequestParam(required = false) LocalDate dataFinal) {

        List<Time> todosOsTimes = apiService.getAllTimes();
        String franquia = apiService.franquiaMaisFamosa(dataInicial, dataFinal, todosOsTimes);
        FranquiaMaisFamosaResponse franquiaMaisFamosaResponse = new FranquiaMaisFamosaResponse(franquia);
        return ResponseEntity.ok(franquiaMaisFamosaResponse);
    }

    /**
     * Retorna a contagem de times por franquia no intervalo informado.
     *
     * @param dataInicial Data inicial do intervalo (opcional).
     * @param dataFinal   Data final do intervalo (opcional).
     * @return Mapa com nome da franquia e sua quantidade de ocorrência.
     */
    @Operation(summary = "Obter contagem por franquia", description = "Retorna a contagem de franquias no intervalo de datas (opcional).")
    @GetMapping("/contagem-por-franquia")
    public ResponseEntity<?> contagemPorFranquia(
            @Parameter(description = "Data inicial do intervalo") @RequestParam(required = false) LocalDate dataInicial,
            @Parameter(description = "Data final do intervalo") @RequestParam(required = false) LocalDate dataFinal) {

        List<Time> todosOsTimes = apiService.getAllTimes();
        Map<String, Long> contagem = apiService.contagemPorFranquia(dataInicial, dataFinal, todosOsTimes);
        return ResponseEntity.ok(contagem);
    }

    /**
     * Retorna a contagem de integrantes por função no intervalo informado.
     *
     * @param dataInicial Data inicial do intervalo (opcional).
     * @param dataFinal   Data final do intervalo (opcional).
     * @return Mapa com nome da função e sua quantidade de ocorrência.
     */
    @Operation(summary = "Obter contagem por função", description = "Retorna a contagem de funções no intervalo de datas (opcional).")
    @GetMapping("/contagem-por-funcao")
    public ResponseEntity<?> contagemPorFuncao(
            @Parameter(description = "Data inicial do intervalo") @RequestParam(required = false) LocalDate dataInicial,
            @Parameter(description = "Data final do intervalo") @RequestParam(required = false) LocalDate dataFinal) {

        List<Time> todosOsTimes = apiService.getAllTimes();
        Map<String, Long> contagem = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);
        return ResponseEntity.ok(contagem);
    }
}
