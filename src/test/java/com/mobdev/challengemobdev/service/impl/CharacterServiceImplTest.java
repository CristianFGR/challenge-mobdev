package com.mobdev.challengemobdev.service.impl;

import com.mobdev.challengemobdev.config.template.RestTemplateResponseErrorHandler;
import com.mobdev.challengemobdev.exception.NotDataFoundException;
import com.mobdev.challengemobdev.service.dto.Character;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import com.mobdev.challengemobdev.service.dto.Location;
import com.mobdev.challengemobdev.service.dto.OriginLocation;
import com.mobdev.challengemobdev.service.mapper.CharacterMapper;
import com.mobdev.challengemobdev.service.mapper.LocationMapper;
import com.mobdev.challengemobdev.util.ValidateUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CharacterServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private CharacterServiceImpl characterService;

    @Mock
    private CharacterMapper characterMapper;

    @Mock
    private LocationMapper locationMapper ;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Before
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
        restTemplate = mock(RestTemplate.class);
        doReturn(restTemplateBuilder).when(restTemplateBuilder).errorHandler(any(RestTemplateResponseErrorHandler.class));
        doReturn(restTemplate).when(restTemplateBuilder).build();
        characterService = new CharacterServiceImpl(characterMapper, locationMapper, restTemplateBuilder);
    }

    @Test
    public void findByIdCharacter() throws MalformedURLException, NotDataFoundException {
        Character character = mock(Character.class);
        Location location = mock(Location.class);
        OriginLocation originLocation = mock(OriginLocation.class);
        CharacterResponseDTO characterResponseDTO = mock(CharacterResponseDTO.class);

        doReturn(originLocation).when(character).getOrigin();
        doReturn("urlLocation").when(originLocation).getUrl();
        ReflectionTestUtils.setField(characterService, "resourceUrl", "test-url");

        doReturn(new ResponseEntity(character, HttpStatus.OK)).when(restTemplate).getForEntity("test-url2", Character.class);
        doReturn(new ResponseEntity(location, HttpStatus.OK)).when(restTemplate).getForEntity("urlLocation", Location.class);

        doReturn(characterResponseDTO).when(characterMapper).toDto(character);

        try(MockedStatic<ValidateUtil> mocked = Mockito.mockStatic(ValidateUtil.class)) {
            mocked.when(() -> ValidateUtil.setQuantityEpisode(character, characterResponseDTO)).thenReturn(characterResponseDTO);
            assertEquals(characterResponseDTO, characterService.findByIdCharacter(2L));
        }
    }

    @Test
    public void findByIdCharacter_NotDataFound() {
        Character character = mock(Character.class);
        ReflectionTestUtils.setField(characterService, "resourceUrl", "test-url");

        doReturn(new ResponseEntity(character, HttpStatus.OK)).when(restTemplate).getForEntity("test-url2", Character.class);

        try(MockedStatic<ValidateUtil> mocked = Mockito.mockStatic(ValidateUtil.class)) {
            NotDataFoundException dataFoundException = new NotDataFoundException("OBJETO NULO");
            mocked.when(() -> ValidateUtil.validateObjectNull(character)).thenThrow(dataFoundException);
            NotDataFoundException notDataFoundException = assertThrows(NotDataFoundException.class, ()->characterService.findByIdCharacter(2L));
            assertEquals(dataFoundException, notDataFoundException);
        }
    }

    @Test
    public void findByIdCharacter_NotURLFound() {
        Character character = mock(Character.class);
        OriginLocation originLocation = mock(OriginLocation.class);
        ReflectionTestUtils.setField(characterService, "resourceUrl", "test-url");
        doReturn(originLocation).when(character).getOrigin();
        doReturn("url-location").when(originLocation).getUrl();

        doReturn(new ResponseEntity(character, HttpStatus.OK)).when(restTemplate).getForEntity("test-url2", Character.class);

        try(MockedStatic<ValidateUtil> mocked = Mockito.mockStatic(ValidateUtil.class)) {
            MalformedURLException malformedURLException = new MalformedURLException("URL NOT VALID");
            mocked.when(() -> ValidateUtil.urlValidator("url-location")).thenThrow(malformedURLException);
            MalformedURLException malformedURL = assertThrows(MalformedURLException.class, ()->characterService.findByIdCharacter(2L));
            assertEquals(malformedURL, malformedURLException);
        }
    }

}
