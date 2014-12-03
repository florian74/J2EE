package com.testBase.soapHandler;


import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.testBase.fonctionWebService.jaxws.Additionner;
import com.testBase.fonctionWebService.jaxws.AfficherBase;
import com.testBase.fonctionWebService.jaxws.Create;
import com.testBase.fonctionWebService.jaxws.Supprimer;
import com.testBase.soapAdapter.TestBaseSoapAdapter;

public class TestBaseSoapHandler {

		private static final String NAMESPACE_URI = "http://fonctionWebService.testBase.com/";
		private static final QName AfficherBase_QNAME = new QName(NAMESPACE_URI,"afficherBase");
		private static final QName Additionner_QNAME = new QName(NAMESPACE_URI,"additionner");
		private static final QName Create_QNAME = new QName(NAMESPACE_URI,"create");
		private static final QName Supprimer_QNAME = new QName(NAMESPACE_URI,"supprimer");
		
		private MessageFactory messageFactory;
		
		private TestBaseSoapAdapter adapter;
		
		public TestBaseSoapHandler() throws SOAPException
		{

			messageFactory = MessageFactory.newInstance();
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
					
					if (Additionner_QNAME.equals(qname))
					{
						response = appelerAdditionner(soapElement);
						break;
					}
					
					if (Create_QNAME.equals(qname))
					{
						response = appelerCreate(soapElement);
					}
					if (Supprimer_QNAME.equals(qname))
					{
						response = appelerSupprimer(soapElement);
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
		
		private Object appelerAdditionner(SOAPElement soapElement) {
			Additionner additionner = JAXB.unmarshal(new DOMSource(soapElement), Additionner.class);
			return adapter.adapterAdditionner(additionner);
			
		}
		
		private Object appelerCreate(SOAPElement soapElement) {
			Create create = JAXB.unmarshal(new DOMSource(soapElement), Create.class);
			return adapter.adapterCreate(create);
			
		}
		
		private Object appelerSupprimer(SOAPElement soapElement) {
			Supprimer supprimer = JAXB.unmarshal(new DOMSource(soapElement), Supprimer.class);
			return adapter.adapterSupprimer(supprimer);
		}
		
}
