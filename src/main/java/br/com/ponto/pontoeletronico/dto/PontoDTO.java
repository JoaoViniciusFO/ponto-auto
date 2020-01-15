package br.com.ponto.pontoeletronico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PontoDTO {

    //{"dia":"2020-01-02","entrada":"13:00","saida":"19:00","origem":"30","arquivo":"","justificativa":" ","centroDeCustoId":"6308"}
    private String dia;
    private String entrada;
    private String saida;
    private String origem;
    private String arquivo;
    private String justificativa;
    private String centroDeCustoId;

    public PontoDTO(String dia) {
        this.dia = dia;
        this.entrada = null;
        this.saida = null;
        this.origem = "30";
        this.arquivo = "";
        this.justificativa = " ";
        this.centroDeCustoId = "6308";
    }
}
