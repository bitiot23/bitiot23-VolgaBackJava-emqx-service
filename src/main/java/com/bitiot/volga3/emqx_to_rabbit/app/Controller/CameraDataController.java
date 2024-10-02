package com.bitiot.volga3.emqx_to_rabbit.app.Controller;

import com.bitiot.volga3.emqx_to_rabbit.app.service.CameraData;
import com.bitiot.volga3.emqx_to_rabbit.app.service.CameraDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guardar_datos_camara")
public class CameraDataController {

    @PostMapping
    public ResponseEntity<String> receiveCameraData(@RequestBody CameraData cameraData){
        //Poner lógica para procesar los datos recibidos
        //Validar el payload y publicarlo en RabbitMQ
        return ResponseEntity.ok("Datos recibidos y procesados");
    }

//    @Autowired
//    private CameraDataService cameraDataService;
//
//    @PostMapping("/fetch-camera-data")
//    public String fetchCameraData() {
//        // Llama al servicio que realiza la petición al webhook
//        return cameraDataService.fetchCameraData();
//    }
}
