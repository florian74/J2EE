package com.testBase.soapHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.testBase.fonctionWebService.jaxws.AfficherBase;
import com.testBase.soapAdapter.TestBaseSoapAdapter;

public class TestBaseSoapHandler {

		private static final String NAMESPACE_URI = "http://soapHandler.testBase.com";
		private static final QName AfficherBase_QNAME = new QName(NAMESPACE_URI,"afficherBase");
		
		private MessageFactory messageFactory;
		
		private TestBaseSoapAdapter adapter;
		
		public TestBaseSoapHandler()
		{
			try {
				messageFactory = MessageFactory.newInstance();
			} catch (SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			adapter = new TestBaseSoapAdapter();
		}
		
		
		public SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException {
			
			SOAPBody soapBody= request.getSOAPBody();
			Iterator iterator = soapBody.getChildElements();
			Object response = null;
			
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (next instanceof SOAPElement)
				{
					SOAPElement soapElement = (SOAPElement) next;
					QName qname = soapElement.getElementQName();
					
					if (AfficherBase_QNAME.equals(qname))
					{
						response = appelerAfficherBase(soapElement);
						break;
					}
				}
				
			}
			
			SOAPMessage soapResponse = messageFactory.createMessage();
			soapBody = soapResponse.getSOAPBody();
			if (response != null)
			{
				JAXB.marshal(response,new SAAJResult(soapBody));
			}
			else
			{
				SOAPFault fault = soapBody.addFault();
				fault.setFaultString("Unrecognized SOAP request");
			}
			
			return soapResponse;
			
		}


		private Object appelerAfficherBase(SOAPElement soapElement) {
			AfficherBase afficherBase = JAXB.unmarshal(new DOMSource(soapElement), AfficherBase.class);
			return adapter.adapterAfficherBase(afficherBase);
			
		}
		
		
}
