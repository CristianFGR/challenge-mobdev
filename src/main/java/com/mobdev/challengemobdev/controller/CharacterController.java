package com.mobdev.challengemobdev.controller;

import com.mobdev.challengemobdev.config.log.ElapsedTime;
import com.mobdev.challengemobdev.exception.NotDataFoundException;
import com.mobdev.challengemobdev.exception.NotFoundException;
import com.mobdev.challengemobdev.service.CharacterService;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
 * Clase controller para exponer el servicio
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/v1/rick-and-morty")
@Validated
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    /**
     *
     * Controlador que obtiene al personaje y sus caracteristicas
     *
     * @param id identificador del personaje a buscar
     * @return  ResponseEntity con el modelo diseñoado
     * @throws MalformedURLException excepcion en caso de error en la url
     * @throws NotFoundException excepcion en el caso del servidor retorne error
     * @throws NotDataFoundException excepcion en caso que el servidor retorne objeto nulo
     */
    @ApiOperation(value = "Consulta de personaje", response = CharacterResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 503, message = "API de consumo no disponible", response = Object.class),
            @ApiResponse(code = 400, message = "Peticion mal realizada", response = Object.class),
            @ApiResponse(code = 404, message = "No se encontro personaje para mostror", response = Object.class),
            @ApiResponse(code = 200, message = "Correo enviado satisfactoriamente", response = CharacterResponseDTO.class)
    })
    @ElapsedTime
    @GetMapping(path = "/character/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterResponseDTO> findById(@PathVariable("id") @Min(1) Long id)
            throws MalformedURLException, NotFoundException, NotDataFoundException {
        return ResponseEntity.ok().body(characterService.findByIdCharacter(id));
    }

}
