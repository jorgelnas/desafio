package br.com.duxusdesafio.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.duxusdesafio.dto.TimeDataResponse;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;

/**
 * Service que possui as regras de negócio para o processamento dos dados solicitados no desafio!
 */
@Service
public class ApiService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private IntegranteRepository integranteRepository;

    @Autowired
    private ComposicaoRepository composicaoRepository;
    
    public List<Time> getAllTimes() {
        return timeRepository.findAll(); // Método para buscar todos os times
    }    

    /**
     * Vai retornar um Time, com a composição do time daquela data.
     */
    public TimeDataResponse timeDaData(LocalDate data, List<Time> todosOsTimes) {
        return todosOsTimes.stream()
                .filter(time -> time.getData().equals(data))
                .findFirst()
                .map(time -> new TimeDataResponse(
                        time.getData(),
                        time.getComposicoes().stream()
                                .map(c -> c.getIntegrante().getNome())
                                .collect(Collectors.toList())
                ))
                .orElse(null);
    }



    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times dentro do período.
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Lista todos os integrantes dentro do intervalo de datas
        List<Integrante> integrantes = integranteRepository.findByComposicoesTimeDataBetween(dataInicial, dataFinal);

        // Conta quantas vezes cada integrante aparece nas composições
        Map<Integrante, Long> integranteCount = integrantes.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Retorna o integrante mais presente
        return integranteCount.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Encontra o máximo
                .map(Map.Entry::getKey) // Retorna o integrante
                .orElse(null);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum dentro do período.
     */
    public Time timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Buscar os times com membros entre as datas fornecidas
        List<Time> times = timeRepository.findByComposicoesTimeDataBetween(dataInicial, dataFinal);

        // Contar os times mais comuns
        Map<Time, Long> timeCount = times.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Encontrar o time mais comum
        Time timeMaisComum = timeCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

            return timeMaisComum;
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período.
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Buscar os integrantes entre as datas
        List<Integrante> integrantes = integranteRepository.findByComposicoesTimeDataBetween(dataInicial, dataFinal);

        // Contar as funções mais comuns
        Map<String, Long> funcaoCount = integrantes.stream()
                .collect(Collectors.groupingBy(Integrante::getFuncao, Collectors.counting()));

        // Retorna a função mais comum
        return funcaoCount.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Encontra a função mais comum
                .map(Map.Entry::getKey)
                .orElse(null); // Caso não haja, retorna null
    }

    /**
     * Vai retornar o nome da franquia mais comum nos times dentro do período.
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Buscar as composições de times entre as datas
        List<ComposicaoTime> composicoes = composicaoRepository.findByTimeDataBetween(dataInicial, dataFinal);

        // Contar as franquias mais comuns com base no integrante
        Map<String, Long> franquiaCount = composicoes.stream()
                .map(composicao -> composicao.getIntegrante().getFranquia()) // Acessando a franquia do integrante
                .collect(Collectors.groupingBy(franquia -> franquia, Collectors.counting()));

        // Retorna a franquia mais comum
        return franquiaCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null); // Caso não haja franquia, retorna null
    }

    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período.
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Filtra as composições de time com data de entrada no intervalo de datas
        List<ComposicaoTime> composicoes = composicaoRepository.findByTimeDataBetween(dataInicial, dataFinal);

        // Conta as franquias baseadas nos integrantes das composições
        return composicoes.stream()
                .map(composicao -> composicao.getIntegrante().getFranquia())  // Acessa a franquia do integrante
                .collect(Collectors.groupingBy(franquia -> franquia, Collectors.counting()));  // Conta as franquias
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período.
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // Buscar todos os integrantes no intervalo de datas
        List<Integrante> integrantes = integranteRepository.findByComposicoesTimeDataBetween(dataInicial, dataFinal);

        // Contar as funções mais comuns
        return integrantes.stream()
                .collect(Collectors.groupingBy(Integrante::getFuncao, Collectors.counting()));
    }

}
