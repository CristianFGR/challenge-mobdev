package com.mobdev.challengemobdev.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.MalformedURLException;


/**
 * Clase para manejar las excepciones
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@ControllerAdvice
@ResponseBody
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);

    /**
     * Metodo que captura la excepcion generada .
     *
     * @param exception excepcion retornada por el controlador.
     * @return ResponseStatusException con el error capturado y el codigo HTTP
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler()
    public ResponseStatusException handlerException(Exception exception) {
        LOGGER.error("Error al ********** {} .", exception.getMessage());
        return new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage());
    }

    /**
     * Metodo que captura la excepcion generada .
     *
     * @param exception excepcion retornada por el controlador.
     * @return ResponseStatusException con el error capturado y el codigo HTTP
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler()
    public ResponseStatusException handlerException(NotFoundException exception) {
        LOGGER.error("No se encontro data para la peticion {} .", exception.getMessage());
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    /**
     * Metodo que captura la excepcion generada .
     *
     * @param exception excepcion retornada por el controlador.
     * @return ResponseStatusException con el error capturado y el codigo HTTP
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler()
    public ResponseStatusException handlerException(MalformedURLException exception) {
        LOGGER.error("La url de la peticion no es valida {} .", exception.getMessage());
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * Metodo que captura la excepcion generada .
     *
     * @param exception excepcion retornada por el controlador.
     * @return ResponseStatusException con el error capturado y el codigo HTTP
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler()
    public ResponseStatusException handlerException(NotDataFoundException exception) {
        LOGGER.error("No se encontro data para la peticion {} .", exception.getMessage());
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
