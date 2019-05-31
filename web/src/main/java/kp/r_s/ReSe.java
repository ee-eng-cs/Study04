package kp.r_s;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The RESTful Web Services implemented as a stateless session bean.
 *
 */
@Stateless
@Path("text")
public class ReSe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	@GET
	public String getText() {
		logger.info("getText():");
		return "text from 'ReSe' (no-interface view stateless session bean)";
	}
}