package br.com.ponto.pontoeletronico.service;

import br.com.ponto.pontoeletronico.dto.ObjetoEnvio;
import br.com.ponto.pontoeletronico.dto.PontoDTO;
import br.com.ponto.pontoeletronico.dto.TokenGw;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static br.com.ponto.pontoeletronico.utils.Util.*;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Log4j2
public class PontoService {

    private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    public boolean beterPontoPeriodo(String dataInicio, String dataFim, String tokenSemBearer) throws InterruptedException, ParseException, JsonProcessingException {

        return percorreDatasAndEnviaDados(yyyyMMdd.parse(dataInicio), yyyyMMdd.parse(dataFim), tokenSemBearer);
    }

    private boolean percorreDatasAndEnviaDados(Date dataInicio, Date dataFim, String tokenSemBearer) throws InterruptedException, JsonProcessingException {
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.setTime(dataInicio);
        TokenGw tokenGw = new TokenGw(tokenSemBearer);

        while (calendarInicio.getTime().before(dataFim)) {
            if (isDataValida(calendarInicio)) {
                ObjetoEnvio objetoEnvio = new ObjetoEnvio(yyyyMMdd.format(calendarInicio.getTime()), tokenGw);

                efetuaEnvio(objetoEnvio.getIdUsuario(), objetoEnvio.getPrimeiroHorario(), tokenSemBearer);

                if (!isMeioPeriodo(objetoEnvio.getSegundoHorario().getDia())) {
                    efetuaEnvio(objetoEnvio.getIdUsuario(), objetoEnvio.getSegundoHorario(), tokenSemBearer);
                }
            }
            calendarInicio.add(Calendar.DAY_OF_YEAR, 1);
        }
        return true;
    }

    /**
     * @param idUsuario      id do usuario recuperado do token
     * @param pontoDTO       objeto com data e horario para registro do ponto
     * @param tokenSemBearer token do usuario
     * @throws InterruptedException em caso de erro no envio
     */
    private void efetuaEnvio(String idUsuario, PontoDTO pontoDTO, String tokenSemBearer) throws InterruptedException, JsonProcessingException {
        Thread.sleep(5000);
        HttpEntity<String> httpEntity = buildHttpEntityDefault(pontoDTO, tokenSemBearer);
        if (nonNull(httpEntity)) {
            RestTemplate rest = new RestTemplate();
            String url = "https://portal.globalweb.com.br/api/v1/folhas/marcacao/" + idUsuario;
//            String personResultAsJsonStr = rest.postForObject(url, httpEntity, String.class);
            log.info("Url: {}", url);
            log.info("Enviado com sucesso : {} ", pontoDTO.getDia());
            log.info("Hora inicio: {} - Hora fim: {} \n\n", pontoDTO.getEntrada(), pontoDTO.getSaida());
        }
    }
}
