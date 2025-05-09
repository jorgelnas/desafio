package br.com.duxusdesafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.CadastroService;

/**
 * Controlador responsável pelas operações de cadastro de integrantes e times.
 * Permite exibir formulários e processar dados de cadastro.
 */
@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private IntegranteRepository integranteRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ComposicaoRepository composicaoRepository;
    
    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private IntegranteRepository integranteRepo;

    @Autowired
    private TimeRepository timeRepo;

    /**
     * Exibe o formulário para cadastrar um integrante em um time.
     *
     * @param model objeto {@link Model} utilizado para passar dados à view
     * @return nome do template HTML para exibição do formulário
     */
    @GetMapping("/formulario-integrante-time")
    public String mostrarFormulario(Model model) {
        List<Integrante> integrantes = integranteRepository.findAll();
        List<Time> times = timeRepository.findAll();

        model.addAttribute("integrantes", integrantes);
        model.addAttribute("times", times);

        return "cadastro-integrante";
    }

    /**
     * Processa o cadastro de um integrante em um time, associando-o com a função especificada.
     *
     * @param timeId        ID do time
     * @param integranteId  ID do integrante
     * @param funcaoNoTime  função do integrante no time
     * @param model         objeto {@link Model} para passar dados à view
     * @return nome do template HTML após o processamento
     */
    @PostMapping("/integrante-time")
    public String cadastrarIntegranteTime(@RequestParam Long timeId, @RequestParam Long integranteId,
                                          @RequestParam String funcaoNoTime, Model model) {
        Time time = timeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));

        if (time.getData() == null) {
            model.addAttribute("mensagem", "Data do time não definida!");
            return "cadastro-integrante";
        }

        CadastroService cadastroService = new CadastroService(timeRepository, integranteRepository, composicaoRepository);
        cadastroService.cadastrarIntegranteNoTime(timeId, integranteId, time.getData(), funcaoNoTime);

        model.addAttribute("mensagem", "Cadastro realizado com sucesso!");
        return "cadastro-integrante";
    }

    /**
     * Exibe o formulário para cadastrar um novo time com seus integrantes.
     *
     * @param model objeto {@link Model} utilizado para passar dados à view
     * @return nome do template HTML para exibição do formulário
     */
    @GetMapping("/formulario-time")
    public String mostrarFormularioTime(Model model) {
        List<Integrante> integrantes = integranteRepo.findAll();
        List<Time> times = timeRepo.findAll();

        model.addAttribute("integrantes", integrantes);
        model.addAttribute("times", times);

        return "cadastro-time";
    }

    /**
     * Processa o cadastro de um novo time com uma lista de integrantes.
     *
     * @param timeId        ID do time a ser cadastrado
     * @param integranteIds lista de IDs dos integrantes que compõem o time
     * @param model         objeto {@link Model} utilizado para passar dados à view
     * @return nome do template HTML após o processamento
     */
    @PostMapping("/cadastrar-time")
    public String cadastrarTime(@RequestParam Long timeId,
                                @RequestParam List<Long> integranteIds,
                                Model model) {
        cadastroService.cadastrarTimeComIntegrantes(timeId, integranteIds);
        model.addAttribute("mensagem", "Time cadastrado com sucesso!");
        return "cadastro-time";
    }
}
