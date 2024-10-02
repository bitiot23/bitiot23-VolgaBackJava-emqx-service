package com.bitiot.volga3.emqx_to_rabbit.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServicioPrueba {

    @GetMapping("/")
    public String probarServicio(){
        return "Hola, el servicio funciona";
    }

    @PostMapping("/guardar-datos-camara")
    public ResponseEntity<Map<String, String>> imprimirMensaje(@RequestBody CameraData body){
        System.out.println("Me llegó la solicitud post: " + body);

        Map<String, String> reply = new HashMap<>();
        reply.put("Result", "ok");
        reply.put("message", "success");
        return ResponseEntity.ok(reply);
    }
}
