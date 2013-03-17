package de.noltarium.jukebox.util;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* de.noltarium.jukebox..*.*(..))")
	public void logBefore(JoinPoint joinPoint) {

		Logger logger = getLoggerFromJoinPoint(joinPoint);
		logger.trace("{}(..) start", joinPoint.getSignature().getName());
	}

	@After("execution(* de.noltarium.jukebox..*.*(..))")
	public void logAfter(JoinPoint joinPoint) {

		Logger logger = getLoggerFromJoinPoint(joinPoint);
		logger.trace("{}(..) end", joinPoint.getSignature().getName());
	}

	private Logger getLoggerFromJoinPoint(JoinPoint joinPoint) {
		Class clazz = joinPoint.getSignature().getDeclaringType();
		try {
			Field loggerField = clazz.getDeclaredField("LOGGER");
			loggerField.setAccessible(true);
			Logger logger = (Logger) loggerField.get(null);
			return logger;
		} catch (SecurityException e) {
			LOGGER.error("", e);
		} catch (NoSuchFieldException e) {
			LOGGER.warn("", e);
		} catch (IllegalArgumentException e) {
			LOGGER.error("", e);
		} catch (IllegalAccessException e) {
			LOGGER.error("", e);
		}
		LOGGER.warn("Logger not found using aspect logger");
		return LOGGER;
	}

}