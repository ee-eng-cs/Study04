package kp.r_s.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * The CDI managed bean for <B>JAX-RS</B> research<BR>
 * (JAX-RS: Java API for RESTful Web Services).
 * 
 */
@Named
@RequestScoped
public class RsManagedBean {

	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	private static final String SERVICE_ENDPOINT = "http://localhost:8080/Study04/rs/text/";

	/**
	 * Researches RESTful web service.
	 * 
	 * @return the result
	 */
	public String researchRestfulWebService() {

		final WebTarget webTarget = ClientBuilder.newClient().target(SERVICE_ENDPOINT);
		final javax.ws.rs.core.Response response = webTarget.request().get();
		report.add(String.format("JAX-RS response status[%s]", response.getStatusInfo()));
		report.add(String.format("JAX-RS response entity[%s]", response.readEntity(String.class)));
		logger.info("researchRestfulWebService():");
		return "";
	}
}