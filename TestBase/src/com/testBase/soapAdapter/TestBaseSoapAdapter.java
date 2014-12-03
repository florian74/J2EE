package com.testBase.soapAdapter;

import java.util.ArrayList;
import java.util.List;

import com.testBase.fonctionWebService.TestBaseDBManager;
import com.testBase.fonctionWebService.jaxws.*;

public class TestBaseSoapAdapter {

	TestBaseDBManager tbdbm = new TestBaseDBManager();
	public AfficherAuteurResponse adapterAfficherBase( AfficherAuteur request)
	{
		
		
		
		AfficherAuteurResponse resp = new AfficherAuteurResponse();
		resp.setReturn(tbdbm.afficherAuteur());

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
	
	public CreateAuteurResponse adapterCreate( CreateAuteur request)
	{
		
		String numero = request.getArg0();
		String nom = request.getArg1();
		String prenom = request.getArg2();
		String domicile = request.getArg3();
		
		CreateAuteurResponse resp = new CreateAuteurResponse();
		

		resp.setReturn(tbdbm.createAuteur(numero, nom , prenom, domicile));
		return resp;
		
	}
	
	public SupprimerToutAuteurResponse adapterSupprimer( SupprimerToutAuteur request)
	{
		
		String champ=request.getArg0();
		String valeur=request.getArg1();
		
		SupprimerToutAuteurResponse resp = new SupprimerToutAuteurResponse();
		resp.setReturn(tbdbm.supprimerToutAuteur(champ, valeur));

		
		return resp;
		
	}
	
	public ModifierToutAuteurResponse adapterModifier2( ModifierToutAuteur request)
	{
		
		String champ=request.getArg0();
		String oldValeur=request.getArg1();
		String newValeur=request.getArg2();
		
		ModifierToutAuteurResponse resp = new ModifierToutAuteurResponse();
		resp.setReturn(tbdbm.modifierToutAuteur(champ, oldValeur, newValeur));

		
		return resp;
		
	}
	
	
	
}
