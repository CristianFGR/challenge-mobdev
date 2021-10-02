package com.movdev.challengemobdev.service.impl;

import com.movdev.challengemobdev.service.CharacterService;
import com.movdev.challengemobdev.service.dto.Character;
import com.movdev.challengemobdev.service.dto.CharacterResponseDTO;
import com.movdev.challengemobdev.service.dto.Location;
import com.movdev.challengemobdev.service.mapper.CharacterMapper;
import com.movdev.challengemobdev.service.mapper.LocationMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    private static final int SIZE_DEFAULT = 0;

    private final CharacterMapper characterMapper;
    private final LocationMapper locationMapper;

    public CharacterServiceImpl(CharacterMapper characterMapper, LocationMapper locationMapper) {
        this.characterMapper = characterMapper;
        this.locationMapper = locationMapper;
    }

    //TODO realizar validacion del response.getStatusCode
    @Override
    public CharacterResponseDTO findByIdCharacter(Long id) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        String resourceUrl = "https://rickandmortyapi.com/api/character/";
        ResponseEntity<Character> responseCharacter = restTemplate.getForEntity(resourceUrl + id, Character.class);
        Character character = responseCharacter.getBody();
        //TODO validar que origin.url sea valida
        ResponseEntity<Location> responseLocation = restTemplate.getForEntity(character.getOrigin().getUrl(), Location.class);
        Location location = responseLocation.getBody();
        CharacterResponseDTO characterResponseDTO = characterMapper.toDto(character);
        locationMapper.updateModel(location, characterResponseDTO);
        return setQuantityEpisode(character, characterResponseDTO);
    }


    private static CharacterResponseDTO setQuantityEpisode(Character character ,CharacterResponseDTO characterResponseDTO){
        characterResponseDTO.setEpisodeCount(CollectionUtils.isNotEmpty(
                character.getEpisodeList()) ? character.getEpisodeList().size() : SIZE_DEFAULT);
        return characterResponseDTO;
    }

    private static ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }
}
