package com.zalempablo.msavaliadordecredito.controller;

import com.netflix.discovery.converters.Auto;
import com.zalempablo.msavaliadordecredito.domain.model.SituacaoCliente;
import com.zalempablo.msavaliadordecredito.service.AvaliadorDeCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorDeCreditoController {

    @Autowired
    private AvaliadorDeCreditoService avaliadorDeCartaoService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = avaliadorDeCartaoService.obterSituacaoCliente(cpf);
    }
}
