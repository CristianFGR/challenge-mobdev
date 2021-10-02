package com.movdev.challengemobdev.controller;

import com.movdev.challengemobdev.service.CharacterService;
import com.movdev.challengemobdev.service.dto.CharacterResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/v1/rick-and-morty/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(characterService.findByIdCharacter(id));
    }

}
