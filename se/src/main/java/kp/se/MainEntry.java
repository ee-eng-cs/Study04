package kp.se;

import kp.Constants;
import kp.util.Helper;
import kp.w_s.WebSe;
import kp.w_s.endpoint.ExtraService;

/**
 * The main SE entry point.
 *
 */
public class MainEntry {

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
		final ExtraService extraService = new ExtraService();
		final kp.w_s.endpoint.Extra extraImplPort = extraService.getExtraPort();

		System.out.println(Constants.BREAK);
		final String text = "SE";

		System.out.println("* * * 'WebSe' from created service instance: * * *");
		for (WebSe webSe : webSeArr) {
			System.out.println(webSe.tryIt(text));
		}
		System.out.println("* * * 'Extra' from a new 'ExtraService': * * *");
		System.out.println(extraImplPort.tryIt(text));

		System.out.println("* * * 'Extra' from created service instance: * * *");
		System.out.println(extraImplFromService.tryIt(text));

		System.out.println(Constants.BREAK);
	}
}