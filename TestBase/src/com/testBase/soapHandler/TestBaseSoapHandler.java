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

import com.testBase.fonctionWebService.jaxws.*;
import com.testBase.soapAdapter.TestBaseSoapAdapter;

public class TestBaseSoapHandler {

		private static final String NAMESPACE_URI = "http://fonctionWebService.testBase.com/";
		private static final QName AfficherAuteur_QNAME = new QName(NAMESPACE_URI,"afficherAuteur");
		private static final QName Additionner_QNAME = new QName(NAMESPACE_URI,"additionner");
		private static final QName CreateAuteur_QNAME = new QName(NAMESPACE_URI,"createAuteur");
		private static final QName SupprimerToutAuteur_QNAME = new QName(NAMESPACE_URI,"supprimerToutAuteur");
		private static final QName ModifierToutAuteur_QNAME = new QName(NAMESPACE_URI,"modifierToutAuteur");
		
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
					
					
					if (AfficherAuteur_QNAME.equals(qname))
					{
						response = appelerAfficherAuteur(soapElement);
						break;
					}
					
					if (Additionner_QNAME.equals(qname))
					{
						response = appelerAdditionner(soapElement);
						break;
					}
					
					if (CreateAuteur_QNAME.equals(qname))
					{
						response = appelerCreateAuteur(soapElement);
					}
					if (SupprimerToutAuteur_QNAME.equals(qname))
					{
						response = appelerSupprimerToutAuteur(soapElement);
					}
					
					if (ModifierToutAuteur_QNAME.equals(qname))
					{
						response = appelerModifierToutAuteur(soapElement);
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


		private Object appelerAfficherAuteur(SOAPElement soapElement) {
			AfficherAuteur afficherAuteur = JAXB.unmarshal(new DOMSource(soapElement), AfficherAuteur.class);
			return adapter.adapterAfficherBase(afficherAuteur);
			
		}
		
		private Object appelerAdditionner(SOAPElement soapElement) {
			Additionner additionner = JAXB.unmarshal(new DOMSource(soapElement), Additionner.class);
			return adapter.adapterAdditionner(additionner);
			
		}
		
		private Object appelerCreateAuteur(SOAPElement soapElement) {
			CreateAuteur create = JAXB.unmarshal(new DOMSource(soapElement), CreateAuteur.class);
			return adapter.adapterCreate(create);
			
		}
		
		private Object appelerSupprimerToutAuteur(SOAPElement soapElement) {
			SupprimerToutAuteur supprimer = JAXB.unmarshal(new DOMSource(soapElement), SupprimerToutAuteur.class);
			return adapter.adapterSupprimer(supprimer);
		}
		
		private Object appelerModifierToutAuteur(SOAPElement soapElement) {
			ModifierToutAuteur modifier = JAXB.unmarshal(new DOMSource(soapElement), ModifierToutAuteur.class);
			return adapter.adapterModifier2(modifier);
		}
		
}
