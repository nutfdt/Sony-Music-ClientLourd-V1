package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Agent;
import controleur.Chanson;

public class ModeleChanson {
	private static Bdd uneBdd = new Bdd ("localhost", "sony_music_CL", "root", "");
	public static void insertChanson(Chanson uneChanson) 
	{
		String requete="insert into Chanson values(null,'"
				+uneChanson.getTitre()+"', '"
				+uneChanson.getDate()+"', '"
				+uneChanson.getDuree()+"', "
				+uneChanson.getIdcategorie()+", "
				+uneChanson.getIdalbum()+", '"
				+uneChanson.getLangue()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'execution de : "+requete+" "+exp);
		}
	}
	
	public static ArrayList<Chanson> selectAllChansons(){
		String requete="select * from chanson;";
		ArrayList<Chanson> lesChansons= new ArrayList<Chanson>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			
			//Parcourir les r�sultats et construire des objets
			while(desResultats.next() ) {
				Chanson uneChanson = new Chanson(
						desResultats.getInt("idchanson"), desResultats.getString("titre"),
						desResultats.getString("sortie"), desResultats.getString("duree"), 
						desResultats.getInt("idcategorie"), desResultats.getInt("idalbum"), desResultats.getString("langue"));
				lesChansons.add(uneChanson); 
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete+" "+exp);
		}
		return lesChansons;
	}
	
	public static void deleteChanson(int idchanson) 
	{
		String requete="delete from chanson where idchanson="+idchanson+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'execution de : "+requete);
		}
	}
	
	public static void updateChanson(Chanson uneChanson) 
	{
		String requete="update chanson set titre='"+uneChanson.getTitre()+
				"', sortie='"+uneChanson.getDate()+
				"', duree='"+uneChanson.getDuree()+
				"', idcategorie="+uneChanson.getIdcategorie()+", idalbum="+uneChanson.getIdalbum()+" where idchanson="
				+uneChanson.getIdchanson()+", langue="+uneChanson.getLangue()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'execution de : "+requete);
		}
	}
	public static Chanson selectWhereChanson (int idchanson) 
	{
		String requete ="select * from chanson where idchanson="+idchanson+";";
		Chanson uneChanson=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets chanson 
			if (unResultat.next()) {
				 uneChanson = new Chanson(
						 unResultat.getInt("idchanson"), unResultat.getString("titre"),
						 unResultat.getString("sortie"), unResultat.getString("duree"), 
						 unResultat.getInt("idcategorie"), unResultat.getInt("idalbum"), unResultat.getString("langue"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'execution de : "+requete);
		}
		return uneChanson;
	}
	
	public static Chanson selectWhereChanson (String titre, String sortie, String duree, int idAlbum, int idCategorie, String langue) 
	{
		String requete ="select * from chanson where titre='"+titre+"' and sortie='"+sortie+"' and duree='"+duree+"' and idalbum="+idAlbum+" and idcategorie="+idCategorie+""
				+ " and langue='"+langue+"';";
		Chanson uneChanson=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets chanson 
			if (unResultat.next()) {
				 uneChanson = new Chanson(
						 unResultat.getInt("idchanson"), unResultat.getString("titre"),
						 unResultat.getString("sortie"), unResultat.getString("duree"), 
						 unResultat.getInt("idcategorie"), unResultat.getInt("idalbum"), unResultat.getString("langue"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'execution de : "+requete);
		}
		return uneChanson;
	}
	
	public static Chanson Filtrer (String titre) 
	{
		String requete ="select * from chanson where titre like '%"+titre+"%';";
		Chanson uneChanson=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets chanson 
			if (unResultat.next()) {
				 uneChanson = new Chanson(
						 unResultat.getInt("idchanson"), unResultat.getString("titre"),
						 unResultat.getString("sortie"), unResultat.getString("duree"), 
						 unResultat.getInt("idcategorie"), unResultat.getInt("idalbum"), unResultat.getString("langue"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'execution de : "+requete);
		}
		return uneChanson;
	}
	
	
}
