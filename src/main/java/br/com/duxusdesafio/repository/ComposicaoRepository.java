package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.model.ComposicaoTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComposicaoRepository extends JpaRepository<ComposicaoTime, Long> {

    // Encontrar composições entre um intervalo de datas (usando a data do Time)
    List<ComposicaoTime> findByTimeDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
