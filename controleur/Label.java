package controleur;

public class Label {
	private int idlabel;
	private String nom, email, mdp, telephone, adresse, role;
	private int nbEmployes;
	
	//Constructeurs 
	public Label(int idlabel, String nom, String email, String mdp, String telephone, String role, String adresse, int nbEmployes) {
		this.idlabel = idlabel;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.telephone = telephone;
		this.role = role;
		this.adresse = adresse;
		this.nbEmployes = nbEmployes;
	}
	
	
	public Label(String nom, String email, String mdp, String telephone, String role, String adresse, int nbEmployes) {
		this.idlabel = 	0;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.telephone = telephone;
		this.role = role;
		this.adresse = adresse;
		this.nbEmployes = nbEmployes;
	}
	
	public Label() {
		this.idlabel = 0;
		this.nom = "";
		this.email = "";
		this.mdp = "";
		this.telephone = "";
		this.role = "";
		this.adresse = "";
		this.nbEmployes = 0;
	}


	
	
	//Getters & Setters
	public int getIdlabel() {
		return idlabel;
	}


	public void setIdlabel(int idlabel) {
		this.idlabel = idlabel;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getRole() {
		return nom;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public int getNbEmployes() {
		return nbEmployes;
	}


	public void setNbEmployes(int nbEmployes) {
		this.nbEmployes = nbEmployes;
	}
	
	
	
	
	
	
}
