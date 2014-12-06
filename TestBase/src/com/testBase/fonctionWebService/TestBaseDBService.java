package com.testBase.fonctionWebService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

@WebService
public class TestBaseDBService {



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
		public String modifierUn(String entite,String champ, String oldValeur, String newValeur, String Numero)
		{
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str = "la modification à echoué";
			Query requete = new Query(entite).addFilter(champ, FilterOperator.EQUAL, oldValeur);
			requete = requete.addFilter("Numero",FilterOperator.EQUAL , Numero);
			PreparedQuery resultat = ds.prepare(requete);
			
			for(Entity un_auteur: resultat.asIterable()) {
				
				Set<String> properties = un_auteur.getProperties().keySet();
				List<String> props = new ArrayList<String>();
				
				for (String property : properties)
				{
					props.add(un_auteur.getProperty(property).toString());
				}
				
				
				Key cle = un_auteur.getKey();
				
				ds.delete(cle);
				
				
				Entity unAuteur = new Entity(entite);
				int i = 0;
				for (String property : properties)
				{
					unAuteur.setProperty(property,props.get(i));
					i++;
				}
				
				unAuteur.setProperty(champ,newValeur);
				
				ds.put(unAuteur);
				str = "modification reussie";
			}
			
			return str;
		}
		
		@WebMethod
		public String modifierEcrire( String champ, String oldValeur, String newValeur, String Numero)
		{
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str = "la modification à echoué";
			Query requete = new Query("L_Ecrire").addFilter(champ, FilterOperator.EQUAL, oldValeur).addFilter("Numero",FilterOperator.EQUAL , Numero);
			
			PreparedQuery resultat = ds.prepare(requete);
			
			
			
			for(Entity un_auteur: resultat.asIterable()) {
				
				Query requete2 = new Query("T_Auteur").addFilter("Numero", FilterOperator.EQUAL, newValeur);
				PreparedQuery resultat2 = ds.prepare(requete2);

				Query requete3 = new Query("T_Livre").addFilter("Numero", FilterOperator.EQUAL, newValeur);
				PreparedQuery resultat3 = ds.prepare(requete3);
				
				if (resultat2.countEntities() + resultat3.countEntities() == 0) break;
				
				//modifier((long) un_auteur.getProperty("ID/Name"), champ , newValeur);
				Set<String> properties = un_auteur.getProperties().keySet();
				List<String> props = new ArrayList<String>();
				
				for (String property : properties)
				{
					props.add(un_auteur.getProperty(property).toString());
				}
				
				
				Key cle = un_auteur.getKey();
				
				ds.delete(cle);
				
				
				Entity unAuteur = new Entity("L_Ecrire");
				int i = 0;
				for (String property : properties)
				{
					unAuteur.setProperty(property,props.get(i));
					i++;
				}
				
				unAuteur.setProperty(champ,newValeur);
				
				ds.put(unAuteur);
				str = "modification reussie";
			}
			
			return str;
		}
		
		
		@WebMethod
		public String modifierTout(String entite,String champ, String oldValeur, String newValeur)
		{
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str = "la modification à echoué";
			Query requete = new Query(entite).addFilter(champ, FilterOperator.EQUAL, oldValeur);
			
			PreparedQuery resultat = ds.prepare(requete);
			
			for(Entity un_auteur: resultat.asIterable()) {
				//modifier((long) un_auteur.getProperty("ID/Name"), champ , newValeur);
				Set<String> properties = un_auteur.getProperties().keySet();
				List<String> props = new ArrayList<String>();
				
				for (String property : properties)
				{
					props.add(un_auteur.getProperty(property).toString());
				}
				
				
				Key cle = un_auteur.getKey();
				
				ds.delete(cle);
				
				
				Entity unAuteur = new Entity(entite);
				int i = 0;
				for (String property : properties)
				{
					unAuteur.setProperty(property,props.get(i));
					i++;
				}
				
				unAuteur.setProperty(champ,newValeur);
				
				ds.put(unAuteur);
				str = "modification reussie";
			}
			
			return str;
		}
		
		@WebMethod
		public String[] afficher(String element, String arg) {
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			
			Query requete = new Query(element);
			arg = arg.trim();
			PreparedQuery resultat = ds.prepare(requete);
			String[] properties = arg.split(",");
			List<String> result = new ArrayList<String>();
			for(Entity un_auteur: resultat.asIterable()) {
				
				String str="";
				if (arg.equals("*"))
				{
					for (String property : un_auteur.getProperties().keySet())
					{
						if (str.equals(""))
						{
							str += un_auteur.getProperty(property).toString();
						}
						else
						{
							str += " / " + un_auteur.getProperty(property).toString();
						}
					}
				}
				else
				{
					for (String property : properties )
					{
						if (str.equals(""))
						{
							str += un_auteur.getProperty(property).toString();
						}
						else
						{
							str += " / " + un_auteur.getProperty(property).toString();
						}
					}
					
				}
				
				result.add(str);
			}
			
			
			String[] s = new String[result.size()];
			for (int i=0 ; i < result.size() ; i++)
			{
				s[i]=result.get(i);
			}
			
			return s;
		}
		
		@WebMethod
		public String supprimerTout(String entite, String champ, String valeur)
		{
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

			Query requete = new Query(entite).addFilter(champ,FilterOperator.EQUAL, valeur);
			String str = "la suppression à echoué";
			PreparedQuery resultat = ds.prepare(requete);
			
			for(Entity un_auteur: resultat.asIterable()) {
				
				try {
					
					Key cle = un_auteur.getKey();
					ds.delete(cle);
					str = "suppression réussie";
				}
				catch (Exception e)
				{
				}
			}
			return str;
		}
		
		@WebMethod
		public String createAuteur(String Numero, String Nom , String Prenom, String Domicile)
		{
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str="la création a echoué";
			
			Query requete = new Query("T_Auteur").addFilter("Numero",FilterOperator.EQUAL, Numero);
			PreparedQuery resultat = ds.prepare(requete);
			
			if (resultat.countEntities() == 0)
			{
				Entity unAuteur = new Entity("T_Auteur");
				unAuteur.setProperty("Numero", Numero);
				unAuteur.setProperty("Nom", Nom);
				unAuteur.setProperty("Prenom", Prenom);
				unAuteur.setProperty("Domicile", Domicile);
				ds.put(unAuteur);
				str = "création réussie";
			}
			
			return str;
		}
		
		@WebMethod
		public String createLivre(String Numero, String Titre , String Prix, String Resume)
		{
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str="la création a echoué";
			
			Query requete = new Query("T_Livre").addFilter("Numero",FilterOperator.EQUAL, Numero);
			PreparedQuery resultat = ds.prepare(requete);
			
			if (resultat.countEntities() == 0)
			{
				Entity unAuteur = new Entity("T_Livre");
				unAuteur.setProperty("Numero", Numero);
				unAuteur.setProperty("Titre", Titre);
				unAuteur.setProperty("Prix", Prix);
				unAuteur.setProperty("Resume", Resume);
				ds.put(unAuteur);
				str = "création réussie";
			}
			
			return str;
		}

		@WebMethod
		public String createLienAuteurLivre(String Numero, String NumeroLivre , String NumeroAuteur)
		{
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str="la création a echoué";
			
			Query requete = new Query("L_Ecrire").addFilter("Numero",FilterOperator.EQUAL, Numero);
			PreparedQuery resultat = ds.prepare(requete);
			
			Query requete2 = new Query("T_Auteur").addFilter("Numero",FilterOperator.EQUAL, NumeroAuteur);
			PreparedQuery resultat2 = ds.prepare(requete2);
			
			Query requete3 = new Query("T_Livre").addFilter("Numero",FilterOperator.EQUAL, NumeroLivre);
			PreparedQuery resultat3 = ds.prepare(requete3);
			
				
			if (resultat.countEntities() == 0 && resultat2.countEntities() != 0 && resultat3.countEntities() != 0)
			{
				Entity unAuteur = new Entity("L_Ecrire");
				unAuteur.setProperty("Numero", Numero);
				unAuteur.setProperty("NumeroLivre", NumeroLivre);
				unAuteur.setProperty("NumeroAuteur", NumeroAuteur);
				ds.put(unAuteur);
				str = "création réussie";
			}
			
			return str;
		}

		public int additionner(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return arg0 + arg1;
		}
		
		
		
		
	
}
