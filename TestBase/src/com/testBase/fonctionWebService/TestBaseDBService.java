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
			// fonction inutilisé dans le CRUD, créer une base de 4 éléments basiques 
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
		public String modifierUn(String entite,String champ, String newValeur, String Numero)
		{
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str = "la modification à echoué" + " champ" + champ + " numero" + Numero + " valeur"  + newValeur;
			
			// regarder si le tuple existe
			Query requete = new Query(entite).addFilter("Numero",FilterOperator.EQUAL , Numero);
			PreparedQuery resultat = ds.prepare(requete);
			
			// le parcourir
			for(Entity un_auteur: resultat.asIterable()) {
				
				// sauvegarde des propriétés
				Set<String> properties = un_auteur.getProperties().keySet();
				List<String> props = new ArrayList<String>();
				
				for (String property : properties)
				{
					props.add(un_auteur.getProperty(property).toString());
				}
				
				//suppression
				Key cle = un_auteur.getKey();
				
				ds.delete(cle);
				
				// chargement des propriétés
				Entity unAuteur = new Entity(entite);
				int i = 0;
				for (String property : properties)
				{
					unAuteur.setProperty(property,props.get(i));
					i++;
				}
				
				// faire la modification
				unAuteur.setProperty(champ.trim().toString(),newValeur);
				
				// persistence
				ds.put(unAuteur);
				str = champ + " modification reussie la nouvelle valeur est " +  newValeur ;
			}
			
			return str;
		}
		
		@WebMethod
		public String modifierEcrire( String champ, String newValeur, String Numero)
		{
			// modifie des valeurs dans la table L_Ecrire
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str = "la modification à echoué";
			
			// rechercher le tuple à modifier
			Query requete = new Query("L_Ecrire").addFilter("Numero",FilterOperator.EQUAL , Numero);
			
			PreparedQuery resultat = ds.prepare(requete);
			
			
			// le parcourir
			for(Entity un_auteur: resultat.asIterable()) {
				
				// tester si la nouvelle valeur existe dans T_Auteur
				Query requete2 = new Query("T_Auteur").addFilter("Numero", FilterOperator.EQUAL, newValeur);
				PreparedQuery resultat2 = ds.prepare(requete2);

				// tester si la nouvelle valeur existe dans T_Livre
				Query requete3 = new Query("T_Livre").addFilter("Numero", FilterOperator.EQUAL, newValeur);
				PreparedQuery resultat3 = ds.prepare(requete3);
				
				// tester si la valeur a modifier est valide
				if (! ((resultat2.countEntities() != 0 && champ == "NumeroAuteur" )||( champ == "NumeroLivre" && resultat3.countEntities() != 0))) break;
				
				
				//modification
				// sauvegarde des propriétés
				Set<String> properties = un_auteur.getProperties().keySet();
				List<String> props = new ArrayList<String>();
				
				for (String property : properties)
				{
					props.add(un_auteur.getProperty(property).toString());
				}
				
				// suppression
				Key cle = un_auteur.getKey();
				ds.delete(cle);
				
				// chargement des propriétés
				Entity unAuteur = new Entity("L_Ecrire");
				int i = 0;
				for (String property : properties)
				{
					unAuteur.setProperty(property,props.get(i));
					i++;
				}
				
				// faire la modification
				unAuteur.setProperty(champ,newValeur);
				
				// persistence
				ds.put(unAuteur);
				str = "modification reussie";
			}
			
			return str;
		}
		
		
		@WebMethod
		public String modifierTout(String entite,String champ, String oldValeur, String newValeur)
		{
			// inutilisé je crois, le principe est identique, s'applique à tous les tuples dont l'ancienne valeur correspond au champ indiqué
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			String str = "la modification à echoué";
			Query requete = new Query(entite).addFilter(champ, FilterOperator.EQUAL, oldValeur);
			
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
		public String[] afficher(String element, String arg) {
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			
			// element donne la table à utiliser T_Auteur, T_Livre ou L_Ecrire
			Query requete = new Query(element);
			
			// arg donne la liste des champs à utiliser séparer par des virgules, * si on on veux tout
			arg = arg.trim();
			PreparedQuery resultat = ds.prepare(requete);
			String[] properties = arg.split(",");
			List<String> result = new ArrayList<String>();
			for(Entity un_auteur: resultat.asIterable()) {
				
				String str="";
				if (arg.equals("*"))
				{
					// gestion de l'*
					for (String property : un_auteur.getProperties().keySet())
					{
						// gestion du cas de la chaine vide : on renvoie NULL
						String s = un_auteur.getProperty(property).toString();
						if (s==null || s.length() == 0 ) s="NULL";

						// création de la chaine à renvoyer
						if (str.equals(""))
						{
							str += s;
						}
						else
						{
							str += " / " + s;
						}
					}
				}
				else
				{
					// gestion de la liste de paramètre précise
					for (String property : properties )
					{
						// gestion du cas de la chaine vide : on renvoie NULL
						String s = un_auteur.getProperty(property).toString();
						if (s==null || s.length() == 0 ) s="NULL";
						
						// création de la chaine à renvoyer
						if (str.equals(""))
						{
							str += s;
						}
						else
						{
							str += " / " + s;
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

			// trouver toutes les entités avec la valeur au champ indiqué
			Query requete = new Query(entite).addFilter(champ,FilterOperator.EQUAL, valeur);
			String str = "la suppression à echoué";
			PreparedQuery resultat = ds.prepare(requete);
			
			// toutes les supprimer, si le numero est unique, on en supprimera qu'un
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
			
			// tester si le numero d'auteur est déjà pris
			Query requete = new Query("T_Auteur").addFilter("Numero",FilterOperator.EQUAL, Numero);
			PreparedQuery resultat = ds.prepare(requete);
			
			// tester
			if (resultat.countEntities() == 0)
			{
				// sauvegarder
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
			
			// verifier si le numero de livre est déjà utilisé
			Query requete = new Query("T_Livre").addFilter("Numero",FilterOperator.EQUAL, Numero);
			PreparedQuery resultat = ds.prepare(requete);
			
			// tester
			if (resultat.countEntities() == 0)
			{
				// valider
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
			
			//verifier que le numero est disponible
			Query requete = new Query("L_Ecrire").addFilter("Numero",FilterOperator.EQUAL, Numero);
			PreparedQuery resultat = ds.prepare(requete);
			
			//verifier que l'identifiant de l'auteur existe
			Query requete2 = new Query("T_Auteur").addFilter("Numero",FilterOperator.EQUAL, NumeroAuteur);
			PreparedQuery resultat2 = ds.prepare(requete2);
			
			//verifier que l'identifiant du livre existe
			Query requete3 = new Query("T_Livre").addFilter("Numero",FilterOperator.EQUAL, NumeroLivre);
			PreparedQuery resultat3 = ds.prepare(requete3);
			
			// vérifier qu'un seul livre est attribué a un seul auteur
			Query requete4 = new Query("L_Ecrire").addFilter("NumeroLivre",FilterOperator.EQUAL, NumeroLivre);
			PreparedQuery resultat4 = ds.prepare(requete4);
			
			// tester si on peux ajouter
			if (resultat.countEntities() == 0 && resultat2.countEntities() != 0 && resultat3.countEntities() != 0 && resultat4.countEntities() == 0)
			{
				
				// ajout
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
			//pas besoin de plus de détail ^^
			return arg0 + arg1;
		}
		
		
		
		
	
}
