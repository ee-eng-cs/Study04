package kp.w_s;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * The web service <B>WebSe</B>.
 *
 */
@WebService
public interface WebSe {
	/**
	 * The business method.
	 * 
	 * @param arg the argument
	 * @return result the result
	 */
	@WebMethod
	public String tryIt(String arg);
}