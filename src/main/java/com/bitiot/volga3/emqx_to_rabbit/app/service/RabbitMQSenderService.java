package com.bitiot.volga3.emqx_to_rabbit.app.service;

import com.bitiot.volga3.emqx_to_rabbit.app.config.RabbitMQConfig;
import com.bitiot.volga3.emqx_to_rabbit.app.model.CameraData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RabbitMQSenderService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSenderService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public Mono<Void> sendCameraData(CameraData data){
        System.out.println("Nombre de la cola: "+RabbitMQConfig.QUEUE_NAME);
        System.out.println("Nombre del exchange: "+RabbitMQConfig.EXCHANGE_NAME);
        System.out.println("Nombre del routing: "+RabbitMQConfig.ROUTING_KEY);
        System.out.println("Datos enviados: "+data);
        return Mono.fromRunnable(() -> {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY,
                    data
            );
        });
    }
}
