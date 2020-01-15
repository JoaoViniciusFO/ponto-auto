package br.com.ponto.pontoeletronico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.ponto.pontoeletronico.utils.Util.geraHorarioAlmoco;
import static br.com.ponto.pontoeletronico.utils.Util.geraHorarioEntradaSaida;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ObjetoEnvio {

    private String idUsuario;
    private PontoDTO primeiroHorario;
    private PontoDTO segundoHorario;

    public ObjetoEnvio(String dia, TokenGw tokenGw) {
        this.idUsuario = tokenGw.getIdToken();
        this.primeiroHorario = new PontoDTO(dia);
        this.segundoHorario = new PontoDTO(dia);

        geraHorarioEntradaSaida(this);
        geraHorarioAlmoco(this);
    }
}
