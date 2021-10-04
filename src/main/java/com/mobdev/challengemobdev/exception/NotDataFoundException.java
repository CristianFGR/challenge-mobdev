package com.mobdev.challengemobdev.exception;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
public class NotDataFoundException extends Exception {

    public NotDataFoundException() {
    }

    public NotDataFoundException(String message) {
        super(message);
    }

    public NotDataFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "NotFoundException: "+getMessage();
    }
}
