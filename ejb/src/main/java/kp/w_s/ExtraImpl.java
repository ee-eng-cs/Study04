package kp.w_s;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import kp.Constants;

/**
 * The web service endpoint implemented as a stateless session bean.
 *
 */
@Stateless
@WebService(endpointInterface = "kp.w_s.Extra", portName = "ExtraPort", /*-*/
		serviceName = "ExtraService", targetNamespace = Constants.TARGET_NAMESPACE)
public class ExtraImpl implements Extra, Serializable {
	/*-
	 * 'ExtraImpl' class is processed with tools: 'wsgen' and 'wsimport'.
	 */

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