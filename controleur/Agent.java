package controleur;

public class Agent {
	private int iduser;
	private String nom, email, mdp, telephone, role, prenom, dateEmbauche;
	private int idlabel;
	
	public Agent(int iduser, String nom, String email, String mdp, String telephone, String role, String prenom, String dateEmbauche,
			int idlabel) {
		this.iduser = iduser;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.telephone = telephone;
		this.role = role;
		this.prenom = prenom;
		this.dateEmbauche = dateEmbauche;
		this.idlabel = idlabel;
	}
	public Agent( String nom, String email, String mdp, String telephone, String role, String prenom,
			String dateEmbauche, int idlabel) {
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.telephone = telephone;
		this.role = role;
		this.prenom = prenom;
		this.dateEmbauche = dateEmbauche;
		this.idlabel = idlabel;
	}
	public Agent() {
		this.iduser = 0;
		this.nom = "";
		this.email = "";
		this.mdp = "";
		this.telephone = "";
		this.role = "";
		this.prenom = "";
		this.dateEmbauche = "";
		this.idlabel = 0;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
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
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDateEmbauche() {
		return dateEmbauche;
	}
	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}
	
}
