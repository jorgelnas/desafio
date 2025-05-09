package br.com.duxusdesafio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FuncaoMaisComumResponse {
    @JsonProperty("Função")
    private String funcao;

    public FuncaoMaisComumResponse(String funcao) {
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
