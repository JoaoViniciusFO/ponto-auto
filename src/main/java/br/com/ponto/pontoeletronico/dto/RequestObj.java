package br.com.ponto.pontoeletronico.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestObj {

    private String dataInicio;
    private String dataFim;
    private String tokenSemBearer;
}
