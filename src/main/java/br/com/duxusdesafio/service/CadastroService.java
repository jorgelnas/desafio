package br.com.duxusdesafio.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;

/**
 * Service responsável por realizar os cadastros relacionados à composição de times
 * e à associação de integrantes aos times.
 */
@Service
public class CadastroService {

    private final TimeRepository timeRepo;
    private final IntegranteRepository integranteRepo;
    private final ComposicaoRepository composicaoRepo;

    /**
     * Construtor para injeção das dependências dos repositórios.
     *
     * @param timeRepo         Repositório de {@link Time}
     * @param integranteRepo   Repositório de {@link Integrante}
     * @param composicaoRepo   Repositório de {@link ComposicaoTime}
     */
    @Autowired
    public CadastroService(TimeRepository timeRepo, IntegranteRepository integranteRepo, ComposicaoRepository composicaoRepo) {
        this.timeRepo = timeRepo;
        this.integranteRepo = integranteRepo;
        this.composicaoRepo = composicaoRepo;
    }

    /**
     * Cadastra um integrante em um time específico, associando a data e função no time.
     *
     * @param timeId        ID do time ao qual o integrante será associado
     * @param integranteId  ID do integrante que será incluído no time
     * @param data          Data da composição (não utilizada diretamente no método)
     * @param funcaoNoTime  Função que o integrante exercerá no time
     * @throws RuntimeException se o time ou o integrante não forem encontrados
     */
    public void cadastrarIntegranteNoTime(Long timeId, Long integranteId, LocalDate data, String funcaoNoTime) {
        Time time = timeRepo.findById(timeId)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));

        Integrante integrante = integranteRepo.findById(integranteId)
                .orElseThrow(() -> new RuntimeException("Integrante não encontrado"));

        ComposicaoTime composicao = new ComposicaoTime();
        composicao.setTime(time);
        composicao.setIntegrante(integrante);
        composicao.setFuncaoNoTime(funcaoNoTime);

        composicaoRepo.save(composicao);
    }

    /**
     * Cadastra um time com uma lista de integrantes, sem associar função específica.
     *
     * @param timeId         ID do time que será cadastrado
     * @param integranteIds  Lista de IDs dos integrantes que comporão o time
     * @throws RuntimeException se o time ou algum integrante não for encontrado
     */
    public void cadastrarTimeComIntegrantes(Long timeId, List<Long> integranteIds) {
        Time time = timeRepo.findById(timeId).orElseThrow(() -> new RuntimeException("Time não encontrado"));

        for (Long integranteId : integranteIds) {
            Integrante integrante = integranteRepo.findById(integranteId)
                    .orElseThrow(() -> new RuntimeException("Integrante não encontrado"));

            ComposicaoTime composicaoTime = new ComposicaoTime();
            composicaoTime.setTime(time);
            composicaoTime.setIntegrante(integrante);

            composicaoRepo.save(composicaoTime);
        }
    }
}
