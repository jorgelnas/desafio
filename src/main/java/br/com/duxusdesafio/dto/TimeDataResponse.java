package br.com.duxusdesafio.dto;

import java.time.LocalDate;
import java.util.List;

public class TimeDataResponse {
    private LocalDate data;
    private List<String> integrantes;

    public TimeDataResponse(LocalDate data, List<String> integrantes) {
        this.data = data;
        this.integrantes = integrantes;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }
}
