package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciamento de {@link Integrante}.
 */
@RestController
@RequestMapping("/api/integrantes")
public class IntegranteController {

    @Autowired
    private IntegranteRepository integranteRepository;

    /**
     * Lista todos os integrantes cadastrados.
     * 
     * @return lista de integrantes
     */
    @GetMapping
    public List<Integrante> listar() {
        return integranteRepository.findAll();
    }

    /**
     * Busca um integrante pelo ID.
     * 
     * @param id identificador do integrante
     * @return integrante encontrado ou 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Integrante> buscarPorId(@PathVariable Long id) {
        Optional<Integrante> integrante = integranteRepository.findById(id);
        return integrante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cadastra um novo integrante.
     * 
     * @param integrante dados do novo integrante
     * @return integrante salvo
     */
    @PostMapping
    public Integrante criar(@RequestBody Integrante integrante) {
        return integranteRepository.save(integrante);
    }

    /**
     * Atualiza um integrante existente.
     * 
     * @param id         id do integrante a ser atualizado
     * @param atualizado dados atualizados
     * @return integrante atualizado ou 404
     */
    @PutMapping("/{id}")
    public ResponseEntity<Integrante> atualizar(@PathVariable Long id, @RequestBody Integrante atualizado) {
        return integranteRepository.findById(id).map(existing -> {
            existing.setNome(atualizado.getNome());
            existing.setFranquia(atualizado.getFranquia());
            existing.setFuncao(atualizado.getFuncao());
            return ResponseEntity.ok(integranteRepository.save(existing));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Remove um integrante pelo ID.
     * 
     * @param id id do integrante
     * @return 204 No Content se excluído
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (integranteRepository.existsById(id)) {
            integranteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
