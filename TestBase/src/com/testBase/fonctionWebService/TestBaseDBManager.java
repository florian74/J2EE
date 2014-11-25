package com.testBase.fonctionWebService;

import java.awt.image.FilteredImageSource;

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
	public void createBDD() {
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
	public void modifier(long clef, String champ, Object valeur) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key cle = KeyFactory.createKey("T_Auteur", clef);
		try {
			Entity AuteurConcerne = ds.get(cle);
			System.out.println("Element trouve =" + AuteurConcerne);
			
			ds.delete(cle);
			Entity unAuteur = new Entity("T_Auteur", clef);
			unAuteur.setProperty("Nom", AuteurConcerne.getProperty("Nom"));
			unAuteur.setProperty("Prenom", AuteurConcerne.getProperty("Prenom"));
			unAuteur.setProperty("Domicile", AuteurConcerne.getProperty("Domicile"));
			unAuteur.setProperty("Numero", AuteurConcerne.getProperty("Numero"));
			unAuteur.setProperty("ID/Name", AuteurConcerne.getProperty("ID/Name"));
			unAuteur.setProperty(champ, valeur);
			ds.put(unAuteur);
		}
		catch(Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
	}
	
	@WebMethod
	public void modifier2(String champ, Object oldValeur, Object newValeur)
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Query requete = new Query("T_Auteur").addFilter(champ, FilterOperator.EQUAL, oldValeur);
		
		PreparedQuery resultat = ds.prepare(requete);
		
		for(Entity un_auteur: resultat.asIterable()) {
			modifier((long) un_auteur.getProperty("ID/Name"), champ , newValeur);
		}
	}
	
	@WebMethod
	public void afficherBase() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Query requete = new Query("T_Auteur");
		
		PreparedQuery resultat = ds.prepare(requete);
		
		for(Entity un_auteur: resultat.asIterable()) {
			String nom = un_auteur.getProperty("Nom").toString();
			String prenom = un_auteur.getProperty("Prenom").toString();
			String domicile = un_auteur.getProperty("Domicile").toString();
			System.out.println(nom + " / " + prenom + " / " + domicile);
		}
	}
	
	@WebMethod
	public void supprimer(String champ, String valeur)
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		Query requete = new Query("T_Auteur").addFilter(champ,FilterOperator.EQUAL, valeur);
		
		PreparedQuery resultat = ds.prepare(requete);
		
		for(Entity un_auteur: resultat.asIterable()) {
			
			try {
				
				Key cle = KeyFactory.createKey("T_Auteur",(long)  un_auteur.getProperty("ID/name"));
				//Entity AuteurConcerne = ds.get(cle);
				ds.delete(cle);
			}
			catch (Exception e)
			{
			}
		}
	}
	
	@WebMethod
	public void create(String Numero, String Nom , String Prenom, String Domicile)
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		Entity unAuteur = new Entity("T_Auteur");
		unAuteur.setProperty("Numero", Numero);
		unAuteur.setProperty("Nom", Nom);
		unAuteur.setProperty("Prenom", Prenom);
		unAuteur.setProperty("Domicile", Domicile);
		ds.put(unAuteur);
	}
	
}
