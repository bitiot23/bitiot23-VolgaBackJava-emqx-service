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
    private Long idCamera;              //bigInt

    @JsonProperty("ReportTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") // Formato ISO con zona horaria
    private ZonedDateTime reportTime;          //varchar (100)

    @JsonProperty("NameCamera")
    private String nameCamera;          //varchar (200)

    @JsonProperty("idBranchOffice")
    private String idBranchOffice;      //Integer

    @JsonProperty("Entrada")
    private String entrada;             //Integer

    @JsonProperty("Salida")
    private String salida;              //Integer

    @JsonProperty("StartTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime startTime;           //Ponerlo en zonedatetime

    @JsonProperty("EndTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime endTime;             //Ponerlo en zonedatetime

}
