package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Album;

public class ModeleAlbum {
	private static Bdd uneBdd = new Bdd ("localhost", "sony_music_CL", "root", "");
	public static void insertAlbum(Album unAlbum) 
	{
		String requete="insert into album values(null,'"
				+unAlbum.getNom()+"', "
				+unAlbum.getAnneeSortie()+", "
				+unAlbum.getIdartiste()+");";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : "+requete);
		}
	}
	
	public static ArrayList<Album> selectAllAlbums(){
		String requete="select * from album;";
		ArrayList<Album> lesAlbums= new ArrayList<Album>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			
			//Parcourir les r�sultats et construire des objets
			while(desResultats.next() ) {
				Album unAlbum = new Album(
						desResultats.getInt("idalbum"), desResultats.getString("nom"),
						desResultats.getInt("anneeSortie"), desResultats.getInt("idartiste"));
				lesAlbums.add(unAlbum); 
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesAlbums;
	}
	
	public static void deleteAlbum(int idalbum) 
	{
		String requete="delete from album where idalbum="+idalbum+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : "+requete);
		}
	}
	
	public static void updateAlbum(Album unAlbum) 
	{
		String requete="update Album set nom='"+unAlbum.getNom()+
				"', anneeSortie="+unAlbum.getAnneeSortie()+
				", idartiste="+unAlbum.getIdartiste()+" where idalbum="
				+unAlbum.getIdalbum()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : "+requete);
		}
	}
	public static Album selectWhereAlbum (int idalbum) 
	{
		String requete ="select * from album where idalbum="+idalbum+";";
		Album unAlbum=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets clients 
			if (unResultat.next()) {
				 unAlbum = new Album(
						 unResultat.getInt("idalbum"), unResultat.getString("nom"),
						 unResultat.getInt("anneeSortie"), unResultat.getInt("idartiste"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : "+requete);
		}
		return unAlbum;
	}
	
	public static Album selectWhereAlbum (String nom, int anneeSortie, int idArtiste) 
	{
		String requete ="select * from album where nom='"+nom+"' and anneeSortie="+anneeSortie+
				" and idartiste="+idArtiste+";";
		Album unAlbum=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets clients 
			if (unResultat.next()) {
				 unAlbum = new Album(
						 unResultat.getInt("idalbum"), unResultat.getString("nom"),
						 unResultat.getInt("anneeSortie"), unResultat.getInt("idartiste"));
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'execution de : "+requete);
		}
		return unAlbum;
	}
	
}
