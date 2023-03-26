package modele;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controleur.Label;

public class ModeleLabel {
	private static Bdd uneBdd = new Bdd ("localhost", "sony_music_CL", "root", "");
	
	public static void insertLabel(Label unLabel) 
	{
		String requete="call insertLabel ('"
				+unLabel.getNom()+"', '"
				+unLabel.getEmail()+"', '"
				+unLabel.getMdp()+"', '"
				+unLabel.getTelephone()+"', 'label', '"
				+unLabel.getAdresse()+"', '"
				+unLabel.getNbEmployes()+"');";
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
	
	public static ArrayList<Label> selectAllLabels(){
		String requete="select * from vueLabels;";
		ArrayList<Label> lesLabels= new ArrayList<Label>();
		try {
			uneBdd.seConnecter();
			Statement unStat= uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats=unStat.executeQuery(requete);
			//Parcourir les r�sultats et construire des objets
			while(desResultats.next() ) {
				Label unLabel = new Label(
						desResultats.getInt("iduser"), desResultats.getString("nom"),
						desResultats.getString("email"), desResultats.getString("mdp"),
						desResultats.getString("telephone"), desResultats.getString("role"), desResultats.getString("adresse"),
						desResultats.getInt("nbEmployes"));
				lesLabels.add(unLabel); 
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de: "+requete);
		}
		return lesLabels;
	}
	
	public static void deleteLabel(int iduser) 
	{
		String requete="call deleteLabel ('"+iduser+"');";
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
	
	public static void updateLabel(Label unLabel) 
	{
		String requete="call updateLabel ('"+unLabel.getIdlabel()+"','"
				+unLabel.getNom()+"','"
				+unLabel.getEmail()+"', '"
				+unLabel.getMdp()+"', '"
				+unLabel.getTelephone()+"', 'label', '"
				+unLabel.getAdresse()+"' ,'"
				+unLabel.getNbEmployes()+"');";
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
	public static Label selectWhereLabel (int iduser) 
	{
		String requete ="select * from vueLabels where iduser="+iduser+";";
		Label unLabel=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets clients 
			if (unResultat.next()) {
				 unLabel = new Label(
						 unResultat.getInt("iduser"), unResultat.getString("nom"),
						 unResultat.getString("email"), unResultat.getString("mdp"),
						 unResultat.getString("telephone"),unResultat.getString("role"), unResultat.getString("adresse"), unResultat.getInt("nbEmployes")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : "+requete);
		}
		return unLabel;
	}
	public static Label selectWhereLabel (String email, String mdp) 
	{
		String requete ="select * from vueLabels where email='"+email+"' and mdp='"+mdp+"';";
		Label unLabel=null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			//parcourir les resultats et construire des objets Techniciens 
			if (unResultat.next()) {
				 unLabel = new Label(
						 unResultat.getInt("iduser"), unResultat.getString("nom"),
						 unResultat.getString("email"), unResultat.getString("mdp"),
						 unResultat.getString("telephone"),unResultat.getString("role"), unResultat.getString("adresse"), unResultat.getInt("nbEmployes")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : "+requete);
		}
		return unLabel;
	}
}
