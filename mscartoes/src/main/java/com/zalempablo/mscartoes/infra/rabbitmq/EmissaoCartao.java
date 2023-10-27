package com.zalempablo.mscartoes.infra.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//docker run -it --name projetoComRabbitMq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

@Component
public class EmissaoCartao {

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void recberSolicitacaoEmissao(@Payload String payload){
        System.out.println(payload);
    }
}
