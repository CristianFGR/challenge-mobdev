package com.mobdev.challengemobdev.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

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
public class CharacterResponseDTO implements Serializable {

    @JsonProperty(value = "id")
    private int idCharacter;

    @JsonProperty(value = "name")
    private String nameCharacter;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "species")
    private String species;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "episode_count")
    private int episodeCount;

    @JsonProperty(value = "origin")
    private OriginCharacterDTO originCharacterDTO;

}
