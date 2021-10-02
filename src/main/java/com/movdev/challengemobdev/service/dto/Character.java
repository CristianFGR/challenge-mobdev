package com.movdev.challengemobdev.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 *
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
public class Character implements Serializable {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "species")
    private String species;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "gender")
    private String gender;

    @JsonProperty(value = "origin")
    private OriginLocation origin;

    @JsonProperty(value = "location")
    private OriginLocation location;

    @JsonProperty(value = "image")
    private String image;

    @JsonProperty(value = "episode")
    private List<String> episodeList;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "created")
    private String created;


}
