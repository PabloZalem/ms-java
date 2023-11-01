package com.zalempablo.msavaliadordecredito.infra.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import com.zalempablo.msavaliadordecredito.domain.model.DadosSolicitacaoDeCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitacaoDeEnvioDeCartao {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void solicitarCartao(DadosSolicitacaoDeCartao dados) throws JsonProcessingException {
        var json = converterJson(dados);
        rabbitTemplate.convertAndSend(queue.getName(),json);
    }

    private String converterJson(DadosSolicitacaoDeCartao dados) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dados);
        return json;
    }
}
