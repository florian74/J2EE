package com.testBase.fonctionWebService;

import java.awt.image.FilteredImageSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;

@WebService
public class TestBaseDBManager {

	@WebMethod
	public void createStubAuteur() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		Entity unAuteur = new Entity("T_Auteur");
		unAuteur.setProperty("Numero", "85478");
		unAuteur.setProperty("Nom", "Castafiore");
		unAuteur.setProperty("Prenom", "Emilie");
		unAuteur.setProperty("Domicile", "Paris");
		ds.put(unAuteur);
				
		unAuteur = new Entity("T_Auteur");
		unAuteur.setProperty("Numero", "54643");
		unAuteur.setProperty("Nom", "Doe");
		unAuteur.setProperty("Prenom", "John");
		unAuteur.setProperty("Domicile", "London");
		ds.put(unAuteur);
		
		unAuteur = new Entity("T_Auteur");
		unAuteur.setProperty("Numero", "77777");
		unAuteur.setProperty("Nom", "Holmes");
		unAuteur.setProperty("Prenom", "Sherlock");
		unAuteur.setProperty("Domicile", "London");
		ds.put(unAuteur);
		
		unAuteur = new Entity("T_Auteur");
		unAuteur.setProperty("Numero", "34884");
		unAuteur.setProperty("Nom", "Lucky");
		unAuteur.setProperty("Prenom", "Luke");
		unAuteur.setProperty("Domicile", "FarWest");
		ds.put(unAuteur);
		
		unAuteur = new Entity("T_Auteur");
		unAuteur.setProperty("Numero", "78731");
		unAuteur.setProperty("Nom", "Lagaffe");
		unAuteur.setProperty("Prenom", "Gaston");
		unAuteur.setProperty("Domicile", "Paris");
		ds.put(unAuteur);
	}
	
	
	@WebMethod
	public String modifierToutAuteur(String champ, String oldValeur, String newValeur)
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Query requete = new Query("T_Auteur").addFilter(champ, FilterOperator.EQUAL, oldValeur);
		
		PreparedQuery resultat = ds.prepare(requete);
		String result = "la modification a échoué";
		for(Entity un_auteur: resultat.asIterable()) {
			
			
			//modifier((long) un_auteur.getProperty("Numero"), champ , newValeur);
			Key cle = un_auteur.getKey();
			
			// récupération des données de l'ancienne entité
			String nom = un_auteur.getProperty("Nom").toString();
			String prenom = un_auteur.getProperty("Prenom").toString();
			String domicile = un_auteur.getProperty("Domicile").toString();
			String numero = un_auteur.getProperty("Numero").toString();
			
			// suppression de l'entité
			ds.delete(cle);
			
			// création d'une nouvelle entité
			Entity auteur = new Entity("T_Auteur");
			auteur.setProperty("Numero", numero);
			auteur.setProperty("Nom", nom );
			auteur.setProperty("Prenom", prenom);
			auteur.setProperty("Domicile", domicile);
			
			// modification du champ
			auteur.setProperty(champ, newValeur);
			
			ds.put(auteur);
			
			result = "la modification a réussi";
			
		}
		return result;
	}
	
	@WebMethod
	public List<String> afficherAuteur() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		List<String> str = new ArrayList<String>();

		Query requete = new Query("T_Auteur");
		
		
		PreparedQuery resultat = ds.prepare(requete);
		
		for(Entity un_auteur: resultat.asIterable()) {
			
			//affichage champ par champ
			//String nom = un_auteur.getProperty("Nom").toString();
			//String prenom = un_auteur.getProperty("Prenom").toString();
			//String domicile = un_auteur.getProperty("Domicile").toString();
			//String Numero = un_auteur.getProperty("Numero").toString();
			//str.add( Numero + " / " + nom + " / " + prenom + " / " + domicile);
			
			//affichage de l'objet direct
			str.add( "" + un_auteur);
			
		}
		
		return str;
	}
	
	@WebMethod
	public int additionner(int a, int b)
	{
		// méthode de test du web service
		return a+b;
	}
	
	@WebMethod
	public String supprimerToutAuteur(String champ, String valeur)
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		String str = "";
		Query requete = new Query("T_Auteur").addFilter(champ,FilterOperator.EQUAL, valeur);
		
		PreparedQuery resultat = ds.prepare(requete);
		
		if (resultat.countEntities() == 0)
			str = "la valeur " + valeur + " n'apparait pas dans la colonne " + champ + ". La suppression a échouée" ;
		for(Entity un_auteur: resultat.asIterable()) {
			
			try {
				
				Key cle = un_auteur.getKey();
				ds.delete(cle);
				str = "suppression réussit";
			}
			catch (Exception e)
			{
				str = "une erreur est survenue ";
			}
		}
		
		return str;
	}
	
	@WebMethod
	public String createAuteur(String Numero, String Nom , String Prenom, String Domicile)
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		String res = "ajout réussit";
		Entity unAuteur = new Entity("T_Auteur");
		
		// estimation de la clef primaire
		Query requete = new Query("T_Auteur").addFilter("Numero",FilterOperator.EQUAL, Numero);
		PreparedQuery resultat = ds.prepare(requete);
		if (resultat.countEntities() == 0)
		{
			// clef dispo --> creation de l'objet
			unAuteur.setProperty("Numero", Numero);
			unAuteur.setProperty("Nom", Nom);
			unAuteur.setProperty("Prenom", Prenom);
			unAuteur.setProperty("Domicile", Domicile);
			ds.put(unAuteur);
		}
		else // clef prise --> rien
			res = "Le numero est déjà pris";
		
		return res;
	}
	
}
