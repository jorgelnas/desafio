package br.com.duxusdesafio.dto;

public class TimeMaisComumResponse {

    private Long id;
    private String nome;
    private int totalPresencas;

    public TimeMaisComumResponse() {}

    public TimeMaisComumResponse(Long id, String nome, int totalPresencas) {
        this.id = id;
        this.nome = nome;
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

    public int getTotalPresencas() {
        return totalPresencas;
    }

    public void setTotalPresencas(int totalPresencas) {
        this.totalPresencas = totalPresencas;
    }
}
