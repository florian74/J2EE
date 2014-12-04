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
		private static final QName Additionner_QNAME = new QName(NAMESPACE_URI,"additionner");
		private static final QName CreateAuteur_QNAME = new QName(NAMESPACE_URI,"createAuteur");
		private static final QName CreateStubAuteur_QNAME = new QName(NAMESPACE_URI,"createStubAuteur");
		private static final QName CreateLivre_QNAME = new QName(NAMESPACE_URI,"createLivre");
		private static final QName Afficher_QNAME = new QName(NAMESPACE_URI,"afficher");
		private static final QName SupprimerTout_QNAME = new QName(NAMESPACE_URI,"supprimerTout");
		private static final QName ModifierTout_QNAME = new QName(NAMESPACE_URI,"modifierTout");
		
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
					
					
					if (Additionner_QNAME.equals(qname))
					{
						response = appelerAdditionner(soapElement);
						break;
					}
					
					if (CreateAuteur_QNAME.equals(qname))
					{
						response = appelerCreateAuteur(soapElement);
					}
					
					if (CreateStubAuteur_QNAME.equals(qname))
					{
						response = appelerStubAuteur(soapElement);
					}
					
					if (CreateLivre_QNAME.equals(qname))
					{
						response = appelerCreateLivre(soapElement);
					}
					
					if (SupprimerTout_QNAME.equals(qname))
					{
						response = appelerSupprimerTout(soapElement);
					}
					
					if (Afficher_QNAME.equals(qname))
					{
						response = appelerAfficher(soapElement);
						break;
					}
					
					if (ModifierTout_QNAME.equals(qname))
					{
						response = appelerModifierTout(soapElement);
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



		
		private Object appelerAdditionner(SOAPElement soapElement) {
			Additionner additionner = JAXB.unmarshal(new DOMSource(soapElement), Additionner.class);
			return adapter.adapterAdditionner(additionner);
			
		}
		
		private Object appelerCreateAuteur(SOAPElement soapElement) {
			CreateAuteur create = JAXB.unmarshal(new DOMSource(soapElement), CreateAuteur.class);
			return adapter.adapterCreateAuteur(create);
			
		}
		
		
		private Object appelerSupprimerTout(SOAPElement soapElement) {
			SupprimerTout supprimer = JAXB.unmarshal(new DOMSource(soapElement), SupprimerTout.class);
			return adapter.adapterSupprimerTout(supprimer);
		}
		
		
		private Object appelerModifierTout(SOAPElement soapElement) {
			ModifierTout modifier = JAXB.unmarshal(new DOMSource(soapElement), ModifierTout.class);
			return adapter.adapterModifierTout(modifier);
		}
		
		private Object appelerStubAuteur(SOAPElement soapElement) {
			CreateStubAuteur createStubAuteur = JAXB.unmarshal(new DOMSource(soapElement), CreateStubAuteur.class);
			return adapter.adapterCreateStubAuteur(createStubAuteur);
		}
		
		private Object appelerCreateLivre(SOAPElement soapElement) {
			CreateLivre create = JAXB.unmarshal(new DOMSource(soapElement), CreateLivre.class);
			return adapter.adapterCreateLivre(create);
			
		}
		
		private Object appelerAfficher(SOAPElement soapElement) {
			Afficher afficherAuteur = JAXB.unmarshal(new DOMSource(soapElement), Afficher.class);
			return adapter.adapterAfficher(afficherAuteur);
			
		}
		
}
