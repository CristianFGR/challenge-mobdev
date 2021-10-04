package com.mobdev.challengemobdev.service;

import com.mobdev.challengemobdev.exception.NotDataFoundException;
import com.mobdev.challengemobdev.exception.NotFoundException;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;

import java.net.MalformedURLException;

/**
 * Interface de declaracion para consumo de servicio
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
public interface CharacterService {

    CharacterResponseDTO findByIdCharacter(Long id) throws MalformedURLException, NotFoundException, NotDataFoundException;
}
