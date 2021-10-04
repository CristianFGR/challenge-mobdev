package com.mobdev.challengemobdev.util;

import com.mobdev.challengemobdev.exception.NotDataFoundException;
import com.mobdev.challengemobdev.service.dto.Character;
import com.mobdev.challengemobdev.service.dto.CharacterResponseDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;

import static java.util.Objects.isNull;

/**
 * Clase para validaciones
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
public final class ValidateUtil {

    private static final String[] SCHEMES = {"http","https"};
    private static final int SIZE_DEFAULT = 0;

    /**
     * Metodo que valida que la url este correcta
     * @param url string con la url
     * @throws MalformedURLException excepcion en caso que la url no sea correcta
     */
    public static void urlValidator(String url) throws MalformedURLException {
        UrlValidator urlValidator = new UrlValidator(SCHEMES);
        if (!urlValidator.isValid(url)) {
            throw new MalformedURLException("URL NOT VALID");
        }
    }

    /**
     * Metodo que valida que el objeto no sea nulo
     * @param ob objeto de entrada
     * @throws NotDataFoundException excepcion indicando objeto nulo
     */
    public static void validateObjectNull(Object ob) throws NotDataFoundException {
        if (isNull(ob)) {
            throw new NotDataFoundException("OBJETO NULO");
        }
    }

    /**
     * Metodo que cuenta la cantidad de episodios del personaje
     * @param character objeto que trae el listado de episodios en los que participa el personaje
     * @param characterResponseDTO objeto donde se almacenara la cantidad de participaciones del personaje
     * @return el objeto CharacterResponseDTO
     */
    public static CharacterResponseDTO setQuantityEpisode(Character character , CharacterResponseDTO characterResponseDTO){
        characterResponseDTO.setEpisodeCount(CollectionUtils.isNotEmpty(
                character.getEpisodeList()) ? character.getEpisodeList().size() : SIZE_DEFAULT);
        return characterResponseDTO;
    }
}
