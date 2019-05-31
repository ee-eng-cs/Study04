package kp.w_s.controller;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import javax.xml.ws.WebServiceRef;

import kp.util.Helper;
import kp.w_s.ExtraImpl;
import kp.w_s.WebSe;
import kp.w_s.WebSeImplA;
import kp.w_s.WebSeImplB;
import kp.w_s.WebSeImplC;
import kp.w_s.endpoint.ExtraService;
import kp.w_s.endpoint.TryItResponse;

/**
 * The CDI managed bean for <B>JAX-WS</B> research<BR>
 * (Java API for XML-Based Web Services).
 * 
 */
@Named
@RequestScoped
public class WsManagedBean {

	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	@EJB
	private WebSeImplA webSeImplA;

	@EJB
	private WebSeImplB webSeImplB;

	@EJB
	private WebSeImplC webSeImplC;

	@EJB
	private ExtraImpl extraImpl;

	/*- declaring a Service Endpoint Interface proxy reference */
	@WebServiceRef(ExtraService.class)
	private kp.w_s.endpoint.Extra extraPort;

	private static final List<String> TEXT_LIST = IntStream.concat(/*-*/
			IntStream.rangeClosed("A".codePointAt(0), "Z".codePointAt(0)), /*-*/
			IntStream.rangeClosed("a".codePointAt(0), "z".codePointAt(0)))/*-*/
			.mapToObj(num -> Character.toString((char) num)).collect(Collectors.toList());

	/**
	 * Researches web service.
	 * 
	 * @return the result
	 */
	public String researchWebService() {

		final String text = TEXT_LIST.get(0);
		Collections.rotate(TEXT_LIST, -1);

		report.add("* * * 'WebSe' from created service instance: * * *");
		final WebSe[] webSeArr = Helper.createWebSeImplementations();
		for (WebSe webSe : webSeArr) {
			System.out.println(webSe.tryIt(text));
		}
		report.add("* * * 'WebSe' from bean reference (injected with @EJB): * * *");
		for (WebSe webSe : new WebSe[] { webSeImplA, webSeImplB, webSeImplC }) {
			webSe.tryIt(text);
		}
		report.add("* * * 'Extra' from a reference to a web service (injected with @WebServiceRef): * * *");
		extraPort.tryIt(text.concat(" - synchronous"));
		researchAsynchronousWithPolling(text);
		researchAsynchronousWithCallback(text);
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// ignore
		}
		report.add("* * * 'Extra' from created service instance: * * *");
		final kp.w_s.Extra extraImplFromService = Helper.createExtraImplementation();
		extraImplFromService.tryIt(text);

		report.add("* * * 'Extra' from bean reference (injected with @EJB): * * *");
		extraImpl.tryIt(text);

		logger.info("researchWebService():");
		return "";
	}

	/**
	 * Invoking an asynchronous operation with <B>polling</B>.
	 * 
	 * @param text the text
	 */
	private void researchAsynchronousWithPolling(String text) {

		final Response<TryItResponse> response = extraPort.tryItAsync(text.concat(" - asynchronous with polling"));
		String result = null;
		try {
			while (!response.isDone()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// ignore
				}
			}
			result = response.get().getReturn();
		} catch (InterruptedException | ExecutionException e) {
			logger.severe(String.format("researchAsynchronousWithPolling(): exception[%s]", e.getMessage()));
			throw new RuntimeException(e);
		}
		logger.info(String.format("researchAsynchronousWithPolling(): result[%s]", result));
	}

	/**
	 * Invoking an asynchronous operation with a <B>callback handler</B>.
	 * 
	 * @param text the text
	 */
	private void researchAsynchronousWithCallback(String text) {

		final AsyncHandler<TryItResponse> handler = response -> {
			String result = null;
			try {
				result = response.get().getReturn();
			} catch (InterruptedException | ExecutionException e) {
				logger.severe(String.format("researchAsynchronousWithCallback().handleResponse(): exception[%s]",
						e.getMessage()));
				throw new RuntimeException(e);
			}
			logger.info(String.format("researchAsynchronousWithCallback().handleResponse(): result[%s]", result));
		};
		extraPort.tryItAsync(text.concat(" - asynchronous with callback"), handler);
	}
}