package com.bitiot.volga3.emqx_to_rabbit.app.service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CameraDataService {

    private final RestTemplate restTemplate;

    public CameraDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchCameraData() {
        String url = "http://206.189.183.181:5004/guardar_pg"; // URL del webhook

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(null, headers); // Petición sin body

            // Realiza la petición y espera respuesta
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            System.out.println("Response from webhook: " + response.getBody());
            return response.getBody();
        } catch (Exception e) {
            // Aquí detallamos más el error
            System.err.println("Error al conectar con el webhook: " + e.getMessage());
            e.printStackTrace();  // Esto imprimirá el stack trace completo del error en los logs
            return "{\"error\": \"Error al conectar con el webhook: " + e.getMessage() + "\"}";
        }
    }
}
