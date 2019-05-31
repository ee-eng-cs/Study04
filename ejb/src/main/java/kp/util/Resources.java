package kp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

/**
 * The resources.
 *
 */
public class Resources {
	/**
	 * Produces logger.
	 * 
	 * @param injectionPoint the injection point
	 * @return logger the logger
	 */
	@Produces
	public Logger getLogger(InjectionPoint injectionPoint) {
		final String category = injectionPoint.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}

	/**
	 * Produces report.
	 */
	@Named
	@Produces
	public static final List<String> report = new ArrayList<>();
}