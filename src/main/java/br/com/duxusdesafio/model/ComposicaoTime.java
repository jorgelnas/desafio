package br.com.duxusdesafio.model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Representa a composição de um integrante em um time específico.
 * Relacionamento intermediário entre {@link Integrante} e {@link Time}.
 * Pode conter informações adicionais como função ou data de entrada.
 * 
 * Essa é uma modelagem típica de relação muitos-para-muitos com atributos.
 * 
 */
@Entity
@Table(name = "composicao_time")
public class ComposicaoTime {

    /**
     * Identificador único da composição.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Referência ao integrante.
     */
    @ManyToOne
    @JoinColumn(name = "integrante_id", nullable = false)
    private Integrante integrante;

    /**
     * Referência ao time.
     */
    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private Time time;


    /**
     * Função específica do integrante nesse time.
     */
    private String funcaoNoTime;

    // Construtores

    /**
     * Construtor padrão.
     */
    public ComposicaoTime() {}

    /**
     * Construtor com parâmetros para inicializar a composição.
     * 
     * @param integrante    O integrante que pertence ao time.
     * @param time          O time ao qual o integrante está vinculado.
     * @param funcaoNoTime  A função ou cargo do integrante dentro do time.
     */
    public ComposicaoTime(Integrante integrante, Time time, String funcaoNoTime) {
        this.integrante = integrante;
        this.time = time;
        this.funcaoNoTime = funcaoNoTime;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getFuncaoNoTime() {
        return funcaoNoTime;
    }

    public void setFuncaoNoTime(String funcaoNoTime) {
        this.funcaoNoTime = funcaoNoTime;
    }
}
