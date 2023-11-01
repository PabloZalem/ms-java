package com.zalempablo.msavaliadordecredito.service;

import com.zalempablo.msavaliadordecredito.domain.model.*;
import com.zalempablo.msavaliadordecredito.ex.DadosClientesNotFoundException;
import com.zalempablo.msavaliadordecredito.ex.ErroComunicacaoException;
import com.zalempablo.msavaliadordecredito.infra.clients.CartoesResourcesClient;
import com.zalempablo.msavaliadordecredito.infra.clients.ClienteResources;
import com.zalempablo.msavaliadordecredito.infra.rabbitmq.SolicitacaoDeEnvioDeCartao;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorDeCreditoService {

    private final ClienteResources clienteResources;
    private final CartoesResourcesClient cartoesResourcesClient;
    private final SolicitacaoDeEnvioDeCartao pubisher;
    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClientesNotFoundException, ErroComunicacaoException {
        try {
            ResponseEntity<DadosCliente> responseEntity = clienteResources.dadosClient(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesByCliete = cartoesResourcesClient.getCartoesByCliete(cpf);

            return SituacaoCliente
                    .builder()
                    .dadosCliente(responseEntity.getBody())
                    .cartoes(cartoesByCliete.getBody())
                    .build();
        }catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientesNotFoundException();
            }
                throw new ErroComunicacaoException(e.getMessage());
        }
    }
    public RetornoAvaliacaoCliente retornoAvaliacaoCliente(String cpf, Long renda)
            throws DadosClientesNotFoundException, ErroComunicacaoException{
        try {
            ResponseEntity<DadosCliente> responseEntity = clienteResources.dadosClient(cpf);
            ResponseEntity<List<Cartao>> cartaoRendaAlta = cartoesResourcesClient.getCartoesRendaAte(renda);

            List<Cartao> body = cartaoRendaAlta.getBody();
            var listaCartoesAprovados =  body.stream().map(cartao -> {
                DadosCliente dadosCliente = responseEntity.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico() != null ? cartao.getLimiteBasico() : BigDecimal.TEN;
                BigDecimal rendaBD = BigDecimal.valueOf(renda);
                BigDecimal idadeBD = dadosCliente.getIdade() != null ? BigDecimal.valueOf(dadosCliente.getIdade()) : BigDecimal.TEN;
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);



                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientesNotFoundException();
            }
            throw new ErroComunicacaoException(e.getMessage());
        }
    }

    public Object solicitarEmissaoDeCartao(DadosSolicitacaoDeCartao dados) throws ErroComunicacaoException {
        try{
            pubisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (Exception e){
            throw new ErroComunicacaoException(e.getMessage());
        }
    }
}
