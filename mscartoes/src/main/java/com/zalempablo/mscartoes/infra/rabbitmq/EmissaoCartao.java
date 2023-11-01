package com.zalempablo.mscartoes.infra.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zalempablo.mscartoes.domain.Cartoes;
import com.zalempablo.mscartoes.domain.ClienteCartao;
import com.zalempablo.mscartoes.domain.DadosSolicitacaoDeCartao;
import com.zalempablo.mscartoes.infra.repository.CartoesRepository;
import com.zalempablo.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

//docker run -it --name projetoComRabbitMq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

@Component
@RequiredArgsConstructor
public class EmissaoCartao {


    private final CartoesRepository cartoesRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void recberSolicitacaoEmissao(@Payload String payload){
        try{
            var mapper = new ObjectMapper();

            DadosSolicitacaoDeCartao dadosSolicitacaoDeCartao = mapper.readValue(payload, DadosSolicitacaoDeCartao.class);
            Cartoes cartao = cartoesRepository.findById(dadosSolicitacaoDeCartao.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartoes((List<Cartoes>) cartao);
            clienteCartao.setCpf(dadosSolicitacaoDeCartao.getCpf());
            clienteCartao.setLimite(dadosSolicitacaoDeCartao.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
