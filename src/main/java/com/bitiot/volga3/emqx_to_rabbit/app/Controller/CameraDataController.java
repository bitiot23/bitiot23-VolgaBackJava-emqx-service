package com.bitiot.volga3.emqx_to_rabbit.app.Controller;

import com.bitiot.volga3.emqx_to_rabbit.app.model.CameraData;
import com.bitiot.volga3.emqx_to_rabbit.app.service.RabbitMQSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/guardar_datos_camara")
public class CameraDataController {

    private final RabbitMQSenderService rabbitMQSenderService;

    @Autowired
    public CameraDataController(RabbitMQSenderService rabbitMQSenderService){
        this.rabbitMQSenderService = rabbitMQSenderService;
    }

//    @PostMapping
//    public ResponseEntity<String> receiveCameraData(@RequestBody CameraData payload){
//        //Poner lógica para procesar los datos recibidos
//        //Validar el payload y publicarlo en RabbitMQ
//        //Imprime los datos del payload
//        log.info("Datos recibidos del payload: {}", payload);
//
//        log.info("Report Time: {}", payload.getReportTime());
//
//        return ResponseEntity.ok("Datos recibidos y procesados");
//    }

    @PostMapping
    public Mono<String> receiveCameraData(@RequestBody CameraData payload){
        log.info("Se enviaron datos del payload a Rabbit");
        return rabbitMQSenderService.sendCameraData(payload)
                .then(Mono.just("Datos recibidos y enviados a RabbitMQ"));
    }
}
