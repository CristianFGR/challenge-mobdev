package com.mobdev.challengemobdev.service.impl;

import com.mobdev.challengemobdev.exception.NotDataFoundException;
import com.mobdev.challengemobdev.service.CharacterService;
import com.mobdev.challengemobdev.service.mapper.CharacterMapper;
import com.mobdev.challengemobdev.config.template.RestTemplateResponseErrorHandler;
import com.mobdev.challengemobdev.service.dto.Character;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import com.mobdev.challengemobdev.service.dto.Location;
import com.mobdev.challengemobdev.service.mapper.LocationMapper;
import com.mobdev.challengemobdev.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterMapper characterMapper;
    private final LocationMapper locationMapper;
    private final RestTemplate restTemplate;

    @Value("${endpoint.api.url}")
    private String resourceUrl;

    public CharacterServiceImpl(CharacterMapper characterMapper, LocationMapper locationMapper,
                                RestTemplateBuilder restTemplateBuilder) {
        this.characterMapper = characterMapper;
        this.locationMapper = locationMapper;
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    /**
     * Metodo que busca al personaje por el identificador
     * @param id identificador del personaje
     * @return Objeto con las caracteristicas del personaje
     * @throws MalformedURLException excepcion en caso de url no valida
     * @throws NotDataFoundException excepcion en caso de data no encontrada
     */
    @Override
    public CharacterResponseDTO findByIdCharacter(Long id) throws MalformedURLException, NotDataFoundException {
        ResponseEntity<Character> responseCharacter = restTemplate.getForEntity(resourceUrl + id, Character.class);
        ValidateUtil.validateObjectNull(responseCharacter.getBody());
        return addOriginCharacter(responseCharacter.getBody());
    }

    /**
     * Metodo que busca informacion del origen del personaje
     * @param character personaje
     * @return CharacterResponseDTO objeto al cual se le agrega informacion del origen
     * @throws MalformedURLException excepcion en caso de url no valida
     */
    private CharacterResponseDTO addOriginCharacter(Character character) throws MalformedURLException, NotDataFoundException {
        ValidateUtil.validateObjectNull(character.getOrigin());
        ValidateUtil.urlValidator(character.getOrigin().getUrl());
        ResponseEntity<Location> responseLocation = restTemplate.getForEntity(character.getOrigin().getUrl(), Location.class);
        CharacterResponseDTO characterResponseDTO = characterMapper.toDto(character);
        locationMapper.updateModel(responseLocation.getBody(), characterResponseDTO);
        return ValidateUtil.setQuantityEpisode(character, characterResponseDTO);
    }

}
