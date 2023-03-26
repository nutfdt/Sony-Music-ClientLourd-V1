package modele;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Categorie;

public class ModeleCategorie {
	private static Bdd uneBdd = new Bdd ("localhost", "sony_music_CL", "root", "");
	
	//Méthodes
		//Insertion d'une Catégorie
	public static void insertCategorie(Categorie uneCategorie) {
		String requete="insert into categorie values(null, '"+uneCategorie.getType()+"');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
	}
	
		//Récupération de toutes les Catégories
	public static ArrayList<Categorie> selectAllCategories() {
		String requete="select * from categorie;";
		ArrayList<Categorie> lesCategories= new ArrayList<Categorie>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			
			while(desResultats.next()) {
				Categorie uneCategorie= new Categorie(
						desResultats.getInt("idcategorie"), desResultats.getString("type")
					);
				lesCategories.add(uneCategorie);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesCategories;
	}
	
		//Suppression d'une categorie
	public static void deleteCategorie(int idcategorie) {
		String requete="delete from categorie where idcategorie="+idcategorie+";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
	}
	
		//Modification d'une categorie
	public static void updateCategorie(Categorie uneCategorie) {
		String requete="update categorie set type='"+uneCategorie.getType()+"' where idcategorie="+uneCategorie.getIdcategorie()+";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
	}
	
		//Récupération d'une seule Catégorie
	public static Categorie selectWhereCategorie(int idcategorie) {
		String requete="select * from categorie where idcategorie="+idcategorie+";";
		Categorie uneCategorie=null;
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat=unStat.executeQuery(requete);
			
			if(unResultat.next()) {
				uneCategorie= new Categorie(
						unResultat.getInt("idcategorie"), unResultat.getString("type")
						);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return uneCategorie;
	}
	
	public static Categorie selectWhereCategorie(String type) {
		String requete="select * from categorie where type='"+type+"';";
		Categorie uneCategorie=null;
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat=unStat.executeQuery(requete);
			
			if(unResultat.next()) {
				uneCategorie= new Categorie(
						unResultat.getInt("idcategorie"), unResultat.getString("type")
						);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return uneCategorie;
	}
}
