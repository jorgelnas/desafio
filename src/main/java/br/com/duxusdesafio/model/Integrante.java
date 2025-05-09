package br.com.duxusdesafio.model;

import javax.persistence.*;
import java.util.List;

/**
 * Entidade que representa um integrante de um time.
 */
@Entity
@Table(name = "integrante")
public class Integrante {

    /**
     * Identificador único do integrante.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da franquia à qual o integrante pertence.
     */
    private String franquia;

    /**
     * Nome do integrante.
     */
    private String nome;

    /**
     * Função ou cargo do integrante dentro do time.
     */
    private String funcao;

    /**
     * Relacionamento com a tabela ComposicaoTime
     */
    @OneToMany(mappedBy = "integrante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComposicaoTime> composicoes;

    /**
     * Construtor padrão.
     */
    public Integrante() {}

    /**
     * Construtor com todos os campos (exceto o ID).
     * 
     * @param franquia franquia do integrante
     * @param nome     nome do integrante
     * @param funcao   função do integrante
     */
    public Integrante(String franquia, String nome, String funcao) {
        this.franquia = franquia;
        this.nome = nome;
        this.funcao = funcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFranquia() {
        return franquia;
    }

    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public List<ComposicaoTime> getComposicoes() {
        return composicoes;
    }

    public void setComposicoes(List<ComposicaoTime> composicoes) {
        this.composicoes = composicoes;
    }
}
