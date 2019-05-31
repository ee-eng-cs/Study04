package kp.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import kp.Constants;
import kp.w_s.Extra;
import kp.w_s.WebSe;

/**
 * The helper.
 *
 */
public class Helper {

	private static final String WSDL_LOC_PREFIX = "http://localhost:8080/Study04_ejb";

	/**
	 * Creates the web services implementations.
	 * 
	 * @return the web services instances array
	 */
	public static WebSe[] createWebSeImplementations() {

		final WebSe[] webSeArr = new WebSe[3];
		final String[] implNameArr = new String[] { "WebSeImplA", "WebSeImplB", "WebSeImplC" };
		for (int i = 0; i < implNameArr.length; i++) {
			webSeArr[i] = createWebSeImpl(implNameArr[i]);
		}
		return webSeArr;
	}

	/**
	 * Creates the web service implementation.
	 * 
	 * @param implName the implementation name
	 * @return the web service instance
	 */
	private static WebSe createWebSeImpl(String implName) {

		final String wsdlDocumentLocation = String.format("%s/%s?wsdl", WSDL_LOC_PREFIX, implName);
		final String localPart = String.format("%sService", implName);
		final Service service = createService(wsdlDocumentLocation, localPart);
		return service.getPort(WebSe.class);
	}

	/**
	 * Creates the extra web service implementation.
	 * 
	 * @return the web service instance
	 */
	public static Extra createExtraImplementation() {

		final String wsdlDocumentLocation = String.format("%s/ExtraService/ExtraImpl?wsdl", WSDL_LOC_PREFIX);
		final String localPart = "ExtraService";
		final Service service = createService(wsdlDocumentLocation, localPart);
		return service.getPort(Extra.class);
	}

	/**
	 * Creates the service.
	 * 
	 * @param wsdlDocumentLocation the WSDL document location
	 * @param localPart            the local part
	 * @return the service
	 */
	private static Service createService(String wsdlDocumentLocation, String localPart) {

		URL wsdlDocumentLocationURL = null;
		try {
			wsdlDocumentLocationURL = new URL(wsdlDocumentLocation);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		final QName serviceName = new QName(Constants.TARGET_NAMESPACE, localPart);
		return Service.create(wsdlDocumentLocationURL, serviceName);
	}
}
