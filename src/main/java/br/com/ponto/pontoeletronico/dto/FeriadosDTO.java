package br.com.ponto.pontoeletronico.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeriadosDTO {

    private List<Date> feriados;

    public List<Date> getFeriados() throws ParseException {
        populaFeriados();
        return feriados;
    }

    /**
     * popula 2019 ate 2021
     */
    private void populaFeriados() throws ParseException {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> lista = new ArrayList<>();
        lista.add(spf.parse("2019-01-01"));
        lista.add(spf.parse("2019-03-04"));
        lista.add(spf.parse("2019-03-05"));
        lista.add(spf.parse("2019-04-19"));
        lista.add(spf.parse("2019-04-21"));
        lista.add(spf.parse("2019-05-01"));
        lista.add(spf.parse("2019-06-20"));
        lista.add(spf.parse("2019-09-07"));
        lista.add(spf.parse("2019-10-12"));
        lista.add(spf.parse("2019-10-31")); // STJ
        lista.add(spf.parse("2019-11-01")); // STJ
        lista.add(spf.parse("2019-11-02"));
        lista.add(spf.parse("2019-11-13")); // STJ
        lista.add(spf.parse("2019-11-14")); // STJ
        lista.add(spf.parse("2019-11-15"));
        lista.add(spf.parse("2019-12-25"));
        lista.add(spf.parse("2020-01-01"));
        lista.add(spf.parse("2020-02-24"));
        lista.add(spf.parse("2020-02-25"));
        lista.add(spf.parse("2020-04-10"));
        lista.add(spf.parse("2020-04-21"));
        lista.add(spf.parse("2020-05-01"));
        lista.add(spf.parse("2020-06-11"));
        lista.add(spf.parse("2020-09-07"));
        lista.add(spf.parse("2020-10-12"));
        lista.add(spf.parse("2020-10-31"));
        lista.add(spf.parse("2020-11-02"));
        lista.add(spf.parse("2020-11-15"));
        lista.add(spf.parse("2020-12-25"));
        this.feriados = lista;
    }
}
