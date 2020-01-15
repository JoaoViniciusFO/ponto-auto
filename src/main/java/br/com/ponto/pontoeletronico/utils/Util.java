package br.com.ponto.pontoeletronico.utils;

import br.com.ponto.pontoeletronico.dto.FeriadosDTO;
import br.com.ponto.pontoeletronico.dto.ObjetoEnvio;
import br.com.ponto.pontoeletronico.dto.PontoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

@NoArgsConstructor
@Log4j2
public class Util {

    private static Random gerador = new Random();

    public static void geraHorarioEntradaSaida(ObjetoEnvio objetoEnvio) {
        String minutos = String.valueOf(gerador.nextInt(60));

        if (minutos.length() < 2) {
            minutos = "0" + minutos;
        }
        objetoEnvio.getPrimeiroHorario().setEntrada("09:" + minutos);
        objetoEnvio.getSegundoHorario().setSaida("18:" + minutos);
    }

    /**
     * caso impar: 12:10 ~ 13:10
     * caso par: 12:20 ~ 13:20
     */
    public static void geraHorarioAlmoco(ObjetoEnvio objetoEnvio) {
        int numero = gerador.nextInt(10);
        boolean par = numero % 2 == 0;
        objetoEnvio.getPrimeiroHorario().setSaida("12:" + (par ? "10" : "20"));
        objetoEnvio.getSegundoHorario().setEntrada("13:" + (par ? "10" : "20"));
    }

    public static String pontoDtoToJson(PontoDTO ponto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(ponto);
    }

    public static boolean isMeioPeriodo(String data) {
        String vesperaNatal = "2019-12-24";
        String vesperaAnoNovo = "2019-12-31";
        return data.equals(vesperaNatal) || data.equals(vesperaAnoNovo);
    }

    public static HttpEntity buildHttpEntityDefault(PontoDTO pontoDTO, String tokenSemBearer) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(new ArrayList<MediaType>() {{
            add(MediaType.APPLICATION_JSON);
        }});
        headers.setBearerAuth(tokenSemBearer);
        headers.set("Accept-Language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7");
        try {
            httpEntity = new HttpEntity<>(pontoDtoToJson(pontoDTO), headers);
        } catch (Exception e) {
            return null;
        }

        return httpEntity;
    }

    public static boolean isDataValida(Calendar data) {
        GregorianCalendar theDate = new GregorianCalendar();
        theDate.setTime(data.getTime());
        FeriadosDTO feriadosDTO = new FeriadosDTO();
        try {

            return theDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    && theDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && !feriadosDTO.getFeriados().contains(data.getTime());

        } catch (ParseException e) {
            log.error(e);
            return false;
        }
    }
}
