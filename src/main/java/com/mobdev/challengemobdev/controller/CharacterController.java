package com.mobdev.challengemobdev.controller;

import com.mobdev.challengemobdev.config.log.ElapsedTime;
import com.mobdev.challengemobdev.exception.NotDataFoundException;
import com.mobdev.challengemobdev.exception.NotFoundException;
import com.mobdev.challengemobdev.service.CharacterService;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.net.MalformedURLException;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/v1/rick-and-morty/character")
@Validated
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @ElapsedTime
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterResponseDTO> findById(@PathVariable("id") @Min(1) Long id)
            throws MalformedURLException, NotFoundException, NotDataFoundException {
        return ResponseEntity.ok().body(characterService.findByIdCharacter(id));
    }

}
