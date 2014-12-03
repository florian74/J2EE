package com.testBase;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.*;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.testBase.soapHandler.TestBaseSoapHandler;


public class TestBaseServlet extends HttpServlet {
	
	static MessageFactory messageFactory;
	static TestBaseSoapHandler soapHandler;
	
	static {
		
		try {
			messageFactory = MessageFactory.newInstance();
			soapHandler = new TestBaseSoapHandler();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			MimeHeaders headers = getHeaders(req);
			InputStream is = req.getInputStream();
			
			SOAPMessage soapRequest = messageFactory.createMessage(headers,is);
			
			// Gérer soapRequest
			SOAPMessage soapResponse = soapHandler.handleSOAPRequest(soapRequest);
			// Créer HttpServletResponse
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf-8\"");
			OutputStream os = resp.getOutputStream();
			soapResponse.writeTo(os);
			os.flush();
			
			
			
			
		} catch (SOAPException e)
		{
			throw new IOException("Exception while creating SOAP message",e);
		}
	}
	
	private MimeHeaders getHeaders(HttpServletRequest req) {
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue,",");
			while (values.hasMoreTokens())
			{
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		
	
		return headers;

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	
}
