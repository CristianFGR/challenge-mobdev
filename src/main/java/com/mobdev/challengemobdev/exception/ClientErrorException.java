package com.mobdev.challengemobdev.exception;

/**
 * Excepcion persanalizada
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
public class ClientErrorException extends Exception {

    public ClientErrorException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "NotFoundException: "+getMessage();
    }
}
