package com.bitiot.volga3.emqx_to_rabbit.app.service;

import com.bitiot.volga3.emqx_to_rabbit.app.config.RabbitMQConfig;
import com.bitiot.volga3.emqx_to_rabbit.app.model.CameraData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RabbitMQSenderService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RabbitMQSenderService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper){
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public Mono<Void> sendCameraData(CameraData data){
        System.out.println("Nombre de la cola: "+RabbitMQConfig.QUEUE_NAME);
        System.out.println("Nombre del exchange: "+RabbitMQConfig.EXCHANGE_NAME);
        System.out.println("Nombre del routing: "+RabbitMQConfig.ROUTING_KEY);
        System.out.println("Datos enviados: "+data);
        return Mono.fromRunnable(() -> {
            try {
                //Se convierte CameraData a JSON antes de enviarlo
                String jsonData = objectMapper.writeValueAsString(data);
                rabbitTemplate.convertAndSend(
                        RabbitMQConfig.EXCHANGE_NAME,
                        RabbitMQConfig.ROUTING_KEY,
                        jsonData
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error serializando CameraData a JSON", e);
            }


        });
    }
}
