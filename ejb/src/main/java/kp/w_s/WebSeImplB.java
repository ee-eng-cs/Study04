package kp.w_s;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

/**
 * The web service endpoint implemented as a stateless session bean.
 *
 */
@Stateless
@WebService
public class WebSeImplB implements WebSe, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	/**
	 * {@inheritDoc} Implemented.
	 * 
	 */
	@Override
	public String tryIt(String arg) {

		final String msg = String.format("implementation[%s], arg[%s]", this.getClass().getSimpleName(), arg);
		report.add(msg);
		logger.info("tryIt():");
		return msg;
	}
}