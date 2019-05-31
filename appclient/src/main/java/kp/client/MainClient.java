package kp.client;

import javax.xml.ws.WebServiceRef;

import kp.Constants;
import kp.util.Helper;
import kp.w_s.WebSe;
import kp.w_s.endpoint.ExtraService;

/**
 * The application for JBoss Application Client.
 *
 */
public class MainClient {

	@WebServiceRef(ExtraService.class)
	private static kp.w_s.endpoint.Extra extraPort;

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(Constants.BREAK);

		final WebSe[] webSeArr = Helper.createWebSeImplementations();
		final kp.w_s.Extra extraImplFromService = Helper.createExtraImplementation();
		final String text = "appclient";

		System.out.println(Constants.BREAK);

		System.out.println("* * * 'WebSe' from created service instance: * * *");
		for (WebSe webSe : webSeArr) {
			System.out.println(webSe.tryIt(text));
		}
		System.out.println("* * * 'Extra' from a reference to a web service (injected with @WebServiceRef): * * *");
		System.out.println(extraPort.tryIt(text));

		System.out.println("* * * 'Extra' from created service instance: * * *");
		System.out.println(extraImplFromService.tryIt(text));

		System.out.println(Constants.BREAK);
	}
}