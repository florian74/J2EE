package com.testBase.soapAdapter;

import java.util.ArrayList;
import java.util.List;

import com.testBase.fonctionWebService.TestBaseDBService;
import com.testBase.fonctionWebService.jaxws.*;

public class TestBaseSoapAdapter {

	TestBaseDBService tbdbm = new TestBaseDBService();

	
	public AfficherResponse adapterAfficher( Afficher request)
	{
		
		
		String entite = request.getArg0();
		String prop = request.getArg1();
		AfficherResponse resp = new AfficherResponse();
		resp.setReturn(tbdbm.afficher(entite, prop));

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
	
	public CreateAuteurResponse adapterCreateAuteur( CreateAuteur request)
	{
		
		String numero = request.getArg0();
		String nom = request.getArg1();
		String prenom = request.getArg2();
		String domicile = request.getArg3();
		
		CreateAuteurResponse resp = new CreateAuteurResponse();
		

		resp.setReturn(tbdbm.createAuteur(numero, nom , prenom, domicile));
		return resp;
		
	}
	
	public CreateLivreResponse adapterCreateLivre( CreateLivre request)
	{
		
		String numero = request.getArg0();
		String titre = request.getArg1();
		String prix = request.getArg2();
		String resume = request.getArg3();
		
		CreateLivreResponse resp = new CreateLivreResponse();
		

		resp.setReturn(tbdbm.createLivre(numero, titre , prix, resume));
		return resp;
		
	}
	
	public CreateLienAuteurLivreResponse adapterCreateLienAuteurLivre( CreateLienAuteurLivre request)
	{
		
		String numero = request.getArg0();
		String livre = request.getArg1();
		String auteur = request.getArg2();

		
		CreateLienAuteurLivreResponse resp = new CreateLienAuteurLivreResponse();
		

		resp.setReturn(tbdbm.createLienAuteurLivre(numero, livre , auteur));
		return resp;
		
	}
	

	
	public SupprimerToutResponse adapterSupprimerTout( SupprimerTout request)
	{
		String entite = request.getArg0();
		String champ=request.getArg1();
		String valeur=request.getArg2();
		
		SupprimerToutResponse resp = new SupprimerToutResponse();
		resp.setReturn(tbdbm.supprimerTout(entite,champ, valeur));

		
		return resp;
		
	}
	
	
	public ModifierToutResponse adapterModifierTout( ModifierTout request)
	{
		String entite=request.getArg0();
		String champ=request.getArg1();
		String oldValeur=request.getArg2();
		String newValeur=request.getArg3();
		
		ModifierToutResponse resp = new ModifierToutResponse();
		resp.setReturn(tbdbm.modifierTout(entite, champ, oldValeur, newValeur));

		
		return resp;
		
	}
	
	public ModifierUnResponse adapterModifierUn( ModifierUn request)
	{
		String entite=request.getArg0();
		String champ=request.getArg1();
		String newValeur=request.getArg2();
		String Numero=request.getArg3();
		
		ModifierUnResponse resp = new ModifierUnResponse();
		resp.setReturn(tbdbm.modifierUn(entite, champ, newValeur,Numero));

		
		return resp;
		
	}
	
	public ModifierEcrireResponse adapterModifierEcrire( ModifierEcrire request)
	{
		String numero=request.getArg2();
		String champ=request.getArg0();
		String newValeur=request.getArg1();
		
		ModifierEcrireResponse resp = new ModifierEcrireResponse();
		resp.setReturn(tbdbm.modifierEcrire( champ, newValeur,numero));

		
		return resp;
		
	}
	
	public CreateStubAuteurResponse adapterCreateStubAuteur( CreateStubAuteur request)
	{
		
		
		CreateStubAuteurResponse resp = new CreateStubAuteurResponse();
		tbdbm.createStubAuteur();

		
		return resp;
		
	}
	
	
	
	
	
}
