package com.movdev.challengemobdev.service;

import com.movdev.challengemobdev.service.dto.CharacterResponseDTO;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
public interface CharacterService {

    CharacterResponseDTO findByIdCharacter(Long id);
}
