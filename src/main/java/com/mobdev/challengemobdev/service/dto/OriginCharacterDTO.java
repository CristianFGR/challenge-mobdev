package com.mobdev.challengemobdev.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class OriginCharacterDTO implements Serializable {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "dimension")
    private String dimension;

    @JsonProperty(value = "residents")
    private List<String> residents;

}
