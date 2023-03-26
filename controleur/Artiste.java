package controleur;

public class Artiste {
	private int iduser;
	private String nom, email, mdp, telephone, role, prenom, nomDeScene, typePrincipal;
	private int idAgent;
	
	//Constructeurs
	public Artiste(int iduser, String nom, String email, String mdp, String telephone, String role, String prenom,
			String nomDeScene, String typePrincipal, int idAgent) {
		this.iduser = iduser;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.telephone = telephone;
		this.role = role;
		this.prenom = prenom;
		this.nomDeScene = nomDeScene;
		this.typePrincipal = typePrincipal;
		this.idAgent = idAgent;
	}
	
	public Artiste(String nom, String email, String mdp, String telephone, String role, String prenom,
			String nomDeScene, String typePrincipal, int idAgent) {
		this.iduser = 0;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.telephone = telephone;
		this.role = role;
		this.prenom = prenom;
		this.nomDeScene = nomDeScene;
		this.typePrincipal = typePrincipal;
		this.idAgent = idAgent;
	}

	public Artiste() {
		this.iduser = 0;
		this.nom = "";
		this.email = "";
		this.mdp = "";
		this.telephone = "";
		this.role = "";
		this.prenom = "";
		this.nomDeScene = "";
		this.typePrincipal = "";
		this.idAgent = 0;
	}

	
	//Getters & Setters
	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNomDeScene() {
		return nomDeScene;
	}

	public void setNomDeScene(String nomDeScene) {
		this.nomDeScene = nomDeScene;
	}

	public String getTypePrincipal() {
		return typePrincipal;
	}

	public void setTypePrincipal(String typePrincipal) {
		this.typePrincipal = typePrincipal;
	}

	public int getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}
	
	
	
	
	
}
