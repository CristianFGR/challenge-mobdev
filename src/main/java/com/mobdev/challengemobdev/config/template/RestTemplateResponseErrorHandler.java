package com.mobdev.challengemobdev.config.template;

import com.mobdev.challengemobdev.exception.ClientErrorException;
import com.mobdev.challengemobdev.exception.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * Clase para manejar las respuestas al usar el resttemplate
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @SneakyThrows
    @Override
    public void handleError(ClientHttpResponse httpResponse) {
        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new Exception(HttpStatus.SERVICE_UNAVAILABLE.toString());
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            throw new ClientErrorException(HttpStatus.NOT_FOUND.toString());
        } if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.toString());
        }
    }

}
