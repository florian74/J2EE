package com.testBase.soapAdapter;

import java.util.ArrayList;
import java.util.List;

import com.testBase.fonctionWebService.TestBaseDBManager;
import com.testBase.fonctionWebService.jaxws.*;

public class TestBaseSoapAdapter {

	TestBaseDBManager tbdbm = new TestBaseDBManager();
	public AfficherBaseResponse adapterAfficherBase( AfficherBase request)
	{
		
		
		
		AfficherBaseResponse resp = new AfficherBaseResponse();
		resp.setReturn(tbdbm.afficherBase());

		return resp;
		
	}
	
	public AdditionnerResponse adapterAdditionner( Additionner request)
	{
		
		int i = request.getArg0();
		int j = request.getArg1();
		
		AdditionnerResponse resp = new AdditionnerResponse();
		

		resp.setReturn(tbdbm.additionner(i, j));
		return resp;
		
	}
	
	public CreateResponse adapterCreate( Create request)
	{
		
		String numero = request.getArg0();
		String nom = request.getArg1();
		String prenom = request.getArg2();
		String domicile = request.getArg3();
		
		CreateResponse resp = new CreateResponse();
		

		resp.setReturn(tbdbm.create(numero, nom , prenom, domicile));
		return resp;
		
	}
	
	public SupprimerResponse adapterSupprimer( Supprimer request)
	{
		
		String champ=request.getArg0();
		String valeur=request.getArg1();
		
		SupprimerResponse resp = new SupprimerResponse();
		resp.setReturn(tbdbm.supprimer(champ, valeur));

		
		return resp;
		
	}
	
	public Modifier2Response adapterModifier2( Modifier2 request)
	{
		
		String champ=request.getArg0();
		String oldValeur=request.getArg1();
		String newValeur=request.getArg2();
		
		Modifier2Response resp = new Modifier2Response();
		resp.setReturn(tbdbm.modifier2(champ, oldValeur, newValeur));

		
		return resp;
		
	}
	
	
	
}
