package com.bitiot.volga3.emqx_to_rabbit.app.service;

import com.bitiot.volga3.emqx_to_rabbit.app.model.CameraData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MqttService {

    private final MqttConnectionService mqttConnectionService;

    private final RabbitMQSenderService rabbitMQSenderService;

    private final ObjectMapper objectMapper;

    public MqttService(MqttConnectionService mqttConnectionService, RabbitMQSenderService rabbitMQSenderService, ObjectMapper objectMapper){
        this.mqttConnectionService = mqttConnectionService;
        this.rabbitMQSenderService = rabbitMQSenderService;
        this.objectMapper = objectMapper;
    }

    //Método para suscribirse a un tópico
    public void subscribeToTopic(String topic) {
        try {
            //Suscribirse al tópico especificado y procesar mensajes recibidos
            mqttConnectionService.getMqttClient().subscribe(topic, this::processMessage);
            log.info("Suscrito al tópico: {}", topic);
        } catch (MqttException e){
            log.error("Error al suscribirse al tópico: {}", e.getMessage());
        }
    }

    //Método para procesar los mensajes recibidos del tópico
    private void processMessage(String topic, MqttMessage message){
        String payload = new String(message.getPayload()); // Convierte el payload del mensaje a String
        log.info("Mensaje recibido del tópico: {}: {}", topic, payload);

        // Procesar los datos JSON
        try {
            CameraData data = objectMapper.readValue(payload, CameraData.class);
            log.info("Datos procesados: {}", data.toString());

            //Enviar los datos a Rabbit
            rabbitMQSenderService.sendCameraData(data)
                    .doOnSuccess(v -> log.info("Datos enviados a Rabbit correctamente"))
                    .doOnError(e -> log.error("Error enviando los datos a Rabbit: ", e))
                    .subscribe();

        } catch (Exception e){
            log.error("Error al procesar el mensaje JSON: ", e);
        }
    }
}
