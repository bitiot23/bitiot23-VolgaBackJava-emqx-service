package com.bitiot.volga3.emqx_to_rabbit.app.Controller;

import com.bitiot.volga3.emqx_to_rabbit.app.service.CameraData;
import com.bitiot.volga3.emqx_to_rabbit.app.service.CameraDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/guardar_datos_camara")
public class CameraDataController {

    @PostMapping
    public ResponseEntity<String> receiveCameraData(@RequestBody CameraData payload){
        //Poner lógica para procesar los datos recibidos
        //Validar el payload y publicarlo en RabbitMQ
        //Imprime los datos del payload
        log.info("Datos recibidos del payload: {}", payload);

        log.info("Report Time: {}", payload.getReportTime());

        System.out.println("Report Time: " + payload.getReportTime());

        return ResponseEntity.ok("Datos recibidos y procesados");
    }
}
