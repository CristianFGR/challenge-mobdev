package com.mobdev.challengemobdev.controller;

import com.mobdev.challengemobdev.exception.NotDataFoundException;
import com.mobdev.challengemobdev.exception.NotFoundException;
import com.mobdev.challengemobdev.service.CharacterService;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CharacterControllerTest {

    @InjectMocks
    private CharacterController controller;

    @Mock
    private CharacterService service;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findById_correct_returnCharacter() throws MalformedURLException, NotFoundException, NotDataFoundException {
        CharacterResponseDTO serviceResponse = Mockito.mock(CharacterResponseDTO.class);
        doReturn(serviceResponse).when(service).findByIdCharacter(1L);
        ResponseEntity<CharacterResponseDTO> responseEntity = controller.findById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void findById_serviceException_returnCharacter() throws MalformedURLException, NotFoundException, NotDataFoundException {
        MalformedURLException malformedURLException = mock(MalformedURLException.class);
        doThrow(malformedURLException).when(service).findByIdCharacter(1L);
        MalformedURLException mfe = assertThrows(MalformedURLException.class, ()->controller.findById(1L));
        assertEquals(malformedURLException , mfe);
    }
}
