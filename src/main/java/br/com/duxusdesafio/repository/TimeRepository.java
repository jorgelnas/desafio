package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

    // Encontrar times cujas composições têm data de entrada anterior ou igual a uma data fornecida
    List<Time> findByComposicoesTimeDataLessThanEqual(LocalDate data);

    // Encontrar times cujas composições têm data de entrada dentro de um intervalo de datas
    List<Time> findByComposicoesTimeDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
