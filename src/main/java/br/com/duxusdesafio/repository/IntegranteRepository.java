package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.model.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IntegranteRepository extends JpaRepository<Integrante, Long> {

    // Encontrar todos os integrantes cujas composições têm uma data de Time dentro de um intervalo de datas
    List<Integrante> findByComposicoesTimeDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
