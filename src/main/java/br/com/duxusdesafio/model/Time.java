package br.com.duxusdesafio.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Representa um time de futebol.
 */
@Entity
@Table(name = "time")
public class Time {

    /**
     * Identificador único do time.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do time.
     */
    private String nome;

    /**
     * Data de fundação ou data de referência do time.
     */
    private LocalDate data;

    /**
     * Relacionamento com a tabela ComposicaoTime.
     * Um time pode ter várias composições (relacionamento com o integrante).
     */
    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComposicaoTime> composicoes;

    /**
     * Construtor padrão.
     */
    public Time() {}

    /**
     * Construtor com parâmetros.
     * 
     * @param nome  Nome do time.
     * @param data  Data de fundação ou referência do time.
     */
    public Time(String nome, LocalDate data) {
        this.nome = nome;
        this.data = data;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ComposicaoTime> getComposicoes() {
        return composicoes;
    }

    public void setComposicoes(List<ComposicaoTime> composicoes) {
        this.composicoes = composicoes;
    }
}
