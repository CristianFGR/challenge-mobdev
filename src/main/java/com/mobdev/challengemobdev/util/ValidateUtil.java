package com.mobdev.challengemobdev.util;

import com.mobdev.challengemobdev.exception.NotDataFoundException;
import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;

import static java.util.Objects.isNull;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
public final class ValidateUtil {

    private static final String[] SCHEMES = {"http","https"};

    public static void urlValidator(String url) throws MalformedURLException {
        UrlValidator urlValidator = new UrlValidator(SCHEMES);
        if (!urlValidator.isValid(url)) {
            throw new MalformedURLException("URL NOT VALID");
        }
    }

    public static void validateObjectNull(Object ob) throws NotDataFoundException {
        if (isNull(ob)) {
            throw new NotDataFoundException("OBJETO NULO");
        }
    }
}
