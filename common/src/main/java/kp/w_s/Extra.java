package kp.w_s;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Action;

/**
 * The web service <B>Extra</B>.
 *
 */
@WebService
public interface Extra {
	/**
	 * The business method.
	 * 
	 * @param arg the argument
	 * @return result the result
	 */
	@WebMethod
	@Action(input = "http://w_s.kp/Extra/tryItRequest", output = "http://w_s.kp/Extra/tryItResponse")
	public String tryIt(String arg);
}
/*-
 * Annotation '@Action' was added only to remove that warning in SE client:
 * 		WARNING: Input Action on WSDL operation tryIt and @Action on its associated Web Method
 * 				 tryIt did not match and will cause problems in dispatching the requests
 */
