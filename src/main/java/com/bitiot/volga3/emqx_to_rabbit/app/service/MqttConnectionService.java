package com.bitiot.volga3.emqx_to_rabbit.app.service;
import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MqttConnectionService {

    //Cliente MQTT
    private IMqttClient mqttClient;

    @Value("${mqtt.brokerUrl}")
    private String brokerUrl;

    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.topic}")
    private String topic;

    public boolean connect(){
        try {
            //Inicia el cliente MQTT
            mqttClient = new MqttClient(brokerUrl, clientId);

            //Opciones de conexión
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true); // Habilita la reconexión automática
            options.setCleanSession(false); // Mantiene la sesión activa entre reconexiones
            options.setConnectionTimeout(10); // Tiempo de espera para la conexión
            options.setKeepAliveInterval(30); // Intervalo de keep-alive

            //Credenciales de conexión
            options.setUserName(username);
            options.setPassword(password.toCharArray());

            if (!mqttClient.isConnected()){
                mqttClient.connect(options);
                log.info("Conectado a EMQX con ID de cliente: {}", clientId);
                return true;
            }
        } catch (MqttException e){
            log.error("Error al conectar al broker MQTT: ", e);
        }
        return false;
    }

    public IMqttClient getMqttClient(){
        return mqttClient;
    }

    public String getTopic(){
        return topic;
    }
}

