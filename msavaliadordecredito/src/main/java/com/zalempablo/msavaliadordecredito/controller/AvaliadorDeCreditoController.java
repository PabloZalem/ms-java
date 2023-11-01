package com.zalempablo.msavaliadordecredito.controller;

import com.zalempablo.msavaliadordecredito.domain.model.*;
import com.zalempablo.msavaliadordecredito.ex.DadosClientesNotFoundException;
import com.zalempablo.msavaliadordecredito.ex.ErroComunicacaoException;
import com.zalempablo.msavaliadordecredito.service.AvaliadorDeCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorDeCreditoController {

    private final AvaliadorDeCreditoService avaliadorDeCartaoService;

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
            SituacaoCliente situacaoCliente = avaliadorDeCartaoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClientesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvalicao(@RequestBody DadosAvaliacao dados){
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorDeCartaoService
                    .retornoAvaliacaoCliente(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClientesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("/solitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoDeCartao dados) {
        try {
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = (ProtocoloSolicitacaoCartao) avaliadorDeCartaoService
                    .solicitarEmissaoDeCartao(dados);
            return ResponseEntity.ok(protocoloSolicitacaoCartao);
        }catch (ErroComunicacaoException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
