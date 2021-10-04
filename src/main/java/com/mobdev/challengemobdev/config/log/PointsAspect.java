package com.mobdev.challengemobdev.config.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Clock;

/**
 * Clase que maneja por aspectos la duraci&oacute;n de la ejecuci&oacute;n
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@Aspect
@Component
public class PointsAspect {

    private static final Logger LOGGER = LogManager.getLogger(PointsAspect.class);
    private final Clock clock = Clock.systemDefaultZone();

    @Around("@annotation(annotation)")
    public Object logMethod(final ProceedingJoinPoint proceedingJoinPoint, final ElapsedTime annotation)
            throws Throwable {
        final long start = clock.millis();
        Object obj;
        try {
            LOGGER.info("Method Name - " +proceedingJoinPoint.getSignature().getName());
            obj = proceedingJoinPoint.proceed();
        } finally {
            final long endTime = clock.millis();
            LOGGER.info("Method Name - " +proceedingJoinPoint.getSignature().getName() +";Elapsed " +
                    "execution time: {}", endTime - start);
        }
        return obj;
    }
}
