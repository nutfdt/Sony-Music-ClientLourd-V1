package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Agent;

public class ModeleAgent {
	private static Bdd uneBdd = new Bdd ("localhost", "sony_music_CL", "root", "");
	public static void insertAgent(Agent unAgent) 
	{
		String requete="call insertAgent ('"
				+unAgent.getNom()+"', '"
				+unAgent.getEmail()+"', '"
				+unAgent.getMdp()+"', '"
				+unAgent.getTelephone()+"', 'agent', '"
				+unAgent.getPrenom()+"', '"
				+unAgent.getDateEmbauche()+"', '"
				+unAgent.getIdlabel()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'exécution de : "+requete);
		}
	}
	
	public static ArrayList<Agent> selectAllAgents(){
		String requete="select * from vueAgents;";
		ArrayList<Agent> lesAgents= new ArrayList<Agent>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			//Parcourir les résultats et construire des objets
			while(desResultats.next() ) {
				Agent unAgent = new Agent(
						desResultats.getInt("iduser"), desResultats.getString("nom"),
						desResultats.getString("email"), desResultats.getString("mdp"),
						desResultats.getString("telephone"), desResultats.getString("role"), desResultats.getString("prenom"),
						desResultats.getString("dateEmbauche"), desResultats.getInt("idLabel"));
				lesAgents.add(unAgent); 
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesAgents;
	}
	
	public static void deleteAgent(int iduser) 
	{
		String requete="call deleteAgent ('"+iduser+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'exécution de : "+requete);
		}
	}
	
	public static void updateAgent(Agent unAgent) 
	{
		String requete="call updateAgent ('"+unAgent.getIduser()+"','"
				+unAgent.getNom()+"','"
				+unAgent.getEmail()+"', '"
				+unAgent.getMdp()+"', '"
				+unAgent.getTelephone()+"', 'agent', '"
				+unAgent.getPrenom()+"' ,'"
				+unAgent.getDateEmbauche()+"', '"
				+unAgent.getIdlabel()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'exécution de : "+requete);
		}
	}
	public static Agent selectWhereAgent (int iduser) 
	{
		String requete ="select * from vueAgents where iduser="+iduser+";";
		Agent unAgent=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets clients 
			if (unResultat.next()) {
				 unAgent = new Agent(
						 unResultat.getInt("iduser"), unResultat.getString("nom"),
						 unResultat.getString("email"), unResultat.getString("mdp"),
						 unResultat.getString("telephone"),unResultat.getString("role"), unResultat.getString("prenom"),
						 unResultat.getString("dateEmbauche"), unResultat.getInt("idLabel")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'exécution de : "+requete);
		}
		return unAgent;
	}
	public static Agent selectWhereAgent (String email, String mdp) 
	{
		String requete ="select * from vueAgents where email='"+email+"' and mdp='"+mdp+"';";
		Agent unAgent=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets Techniciens 
			if (unResultat.next()) {
				 unAgent = new Agent(
						 unResultat.getInt("iduser"), unResultat.getString("nom"),
						 unResultat.getString("email"), unResultat.getString("mdp"),
						 unResultat.getString("telephone"),unResultat.getString("role"), unResultat.getString("prenom"),
						 unResultat.getString("dateEmbauche"), unResultat.getInt("idLabel")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'exécution de : "+requete);
		}
		return unAgent;
	}
}
