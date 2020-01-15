package br.com.ponto.pontoeletronico.controller;

import br.com.ponto.pontoeletronico.dto.RequestObj;
import br.com.ponto.pontoeletronico.service.PontoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@RequestMapping
@Controller
@AllArgsConstructor
@Api(value = "PontoEletronico")
public class PontoController {

    private PontoService pontoService;

    @ApiOperation(value = "Ajusta os pontos válidos no periodo informado (dateFormat = yyyy-MM-dd)")
    @PostMapping(value = "ajuste-mensal")
    public ResponseEntity<Boolean> efetuaAlteracao(@RequestBody RequestObj requestObj) throws ParseException, InterruptedException, JsonProcessingException {
        return ResponseEntity.ok(pontoService.beterPontoPeriodo(requestObj.getDataInicio(), requestObj.getDataFim(), requestObj.getTokenSemBearer()));
    }
}
