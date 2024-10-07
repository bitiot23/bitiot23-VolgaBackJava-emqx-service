package com.bitiot.volga3.emqx_to_rabbit.app.service;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MqttCoordinator {// Coordina la lógica de suscripción para evitar las referencias circulares entre MqttService y MqttConnectionService

    @Autowired
    private MqttConnectionService mqttConnectionService;

    @Autowired
    private MqttService mqttService;

    @PostConstruct
    public void initialize(){
        log.info("Ingresando al initialize");
        if (mqttConnectionService.connect()){ //Verifica si la conexión fue exitosa
            mqttService.subscribeToTopic(mqttConnectionService.getTopic());

        } else {
            log.error("No se pudo conectar al broker MQTT. La suscripción al tópico no se realizará.");
        }


    }
}