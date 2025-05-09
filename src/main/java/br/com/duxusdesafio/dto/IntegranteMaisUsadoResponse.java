package br.com.duxusdesafio.dto;

public class IntegranteMaisUsadoResponse {

    private Long id;
    private String nome;
    private String funcao;
    private int totalPresencas;

    public IntegranteMaisUsadoResponse() {}

    public IntegranteMaisUsadoResponse(Long id, String nome, String funcao, int totalPresencas) {
        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
        this.totalPresencas = totalPresencas;
    }

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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getTotalPresencas() {
        return totalPresencas;
    }

    public void setTotalPresencas(int totalPresencas) {
        this.totalPresencas = totalPresencas;
    }
}

