package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Artiste;

public class ModeleArtiste {
	private static Bdd uneBdd = new Bdd ("localhost", "sony_music_CL", "root", "");
	public static void insertArtiste(Artiste unArtiste) 
	{
		String requete="call insertArtiste ('"
				+unArtiste.getNom()+"', '"
				+unArtiste.getEmail()+"', '"
				+unArtiste.getMdp()+"', '"
				+unArtiste.getTelephone()+"', '"+unArtiste.getRole()+"', '"
				+unArtiste.getPrenom()+"', '"
				+unArtiste.getNomDeScene()+"', '"
				+unArtiste.getTypePrincipal()+"', "
				+unArtiste.getIdAgent()+");";
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
	
	public static ArrayList<Artiste> selectAllArtistes(){
		String requete="select * from vueArtistes;";
		ArrayList<Artiste> lesArtistes= new ArrayList<Artiste>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			
			//Parcourir les r�sultats et construire des objets
			while(desResultats.next() ) {
				Artiste unArtiste = new Artiste(
						desResultats.getInt("iduser"),
						desResultats.getString("nom"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("telephone"),
						desResultats.getString("role"),
						desResultats.getString("prenom"),
						desResultats.getString("nomDeScene"), 
						desResultats.getString("typePrincipal"),
					    desResultats.getInt("idagent"));
				lesArtistes.add(unArtiste); 
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesArtistes;
	}
	
	public static void deleteArtiste(int iduser) 
	{
		String requete="call deleteArtiste ("+iduser+");";
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
	
	public static void updateArtiste(Artiste unArtiste) 
	{
		String requete="call updateArtiste ("+unArtiste.getIduser()+",'"
				+unArtiste.getNom()+"','"
				+unArtiste.getEmail()+"', '"
				+unArtiste.getMdp()+"', '"
				+unArtiste.getTelephone()+"', 'artiste', '"
				+unArtiste.getPrenom()+"' ,'"
				+unArtiste.getNomDeScene()+"', '"
				+unArtiste.getTypePrincipal()+"', "
				+unArtiste.getIdAgent()+");";
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
	public static Artiste selectWhereArtiste (int iduser) 
	{
		String requete ="select * from vueArtistes where iduser="+iduser+";";
		Artiste unArtiste=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets clients 
			if (unResultat.next()) {
				 unArtiste = new Artiste(
						 unResultat.getInt("iduser"), unResultat.getString("nom"),
						 unResultat.getString("email"), unResultat.getString("mdp"),
						 unResultat.getString("telephone"), unResultat.getString("role"), unResultat.getString("prenom"),
						 unResultat.getString("nomDeScene"), unResultat.getString("typePrincipal"),
						 unResultat.getInt("idagent")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : "+requete);
		}
		return unArtiste;
	}
	
	public static Artiste selectWhereArtiste (String nom, String email, String mdp, String telephone, String prenom, String nomDeScene, String typePrincipal) 
	{
		String requete ="select * from vueArtistes where nom='"+nom+"' and email='"+email+"' and mdp='"+mdp+"' and telephone='"+telephone+
				"' and prenom='"+prenom+"' and nomDeScene='"+nomDeScene+"' and typePrincipal='"+typePrincipal+"';";
		Artiste unArtiste=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets clients 
			if (unResultat.next()) {
				 unArtiste = new Artiste(
						 unResultat.getInt("iduser"), unResultat.getString("nom"),
						 unResultat.getString("email"), unResultat.getString("mdp"),
						 unResultat.getString("telephone"), unResultat.getString("role"), unResultat.getString("prenom"),
						 unResultat.getString("nomDeScene"), unResultat.getString("typePrincipal"),
						 unResultat.getInt("idagent")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : "+requete);
		}
		return unArtiste;
	}
	
}
