package com.bitiot.volga3.emqx_to_rabbit.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos desconocidos
public class CameraData {

    @JsonProperty("IdCamera")
    private Long idCamera;              //int8

    @JsonProperty("ReportTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") // Formato ISO con zona horaria
    private ZonedDateTime reportTime;   //timestamptz

    @JsonProperty("NameCamera")
    private String nameCamera;          //varchar (200)

    @JsonProperty("idBranchOffice")
    private String idBranchOffice;      //int4

    @JsonProperty("Entrada")
    private String entrada;             //int4

    @JsonProperty("Salida")
    private String salida;              //int4

    @JsonProperty("StartTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime startTime;    //timestamptz

    @JsonProperty("EndTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime endTime;      //timestamptz

}
