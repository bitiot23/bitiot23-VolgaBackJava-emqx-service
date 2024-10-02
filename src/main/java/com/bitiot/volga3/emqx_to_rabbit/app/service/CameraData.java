package com.bitiot.volga3.emqx_to_rabbit.app.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos desconocidos
public class CameraData {

    @JsonProperty("NameCamera")
    private String nameCamera;

    @JsonProperty("ReportTime")
    private String reportTime;

    @JsonProperty("idBranchOffice")
    private String idBranchOffice;

    @JsonProperty("Entrada")
    private String entrada;

    @JsonProperty("Salida")
    private String salida;

    @JsonProperty("StartTime")
    private String startTime;

    @JsonProperty("EndTime")
    private String endTime;

    @JsonProperty("IdCamera")
    private String idCamera;
}
