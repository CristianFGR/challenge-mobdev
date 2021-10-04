package com.mobdev.challengemobdev.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Objeto que retorna la API externa
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location implements Serializable {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "type")
    private String airDate;

    @JsonProperty(value = "dimension")
    private String dimension;

    @JsonProperty(value = "residents")
    private List<String> residentList;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "created")
    private String created;

}
