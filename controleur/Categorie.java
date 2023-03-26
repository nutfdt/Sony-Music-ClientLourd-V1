package controleur;

public class Categorie {
	private int idcategorie;
	private String type;
	
	public Categorie(int idcategorie, String type) {
		this.idcategorie = idcategorie;
		this.type = type;
	}
	
	public Categorie(String type) {
		this.idcategorie = 0;
		this.type = type;
	}
	
	public Categorie() {
		this.idcategorie=0;
		this.type="";
	}

	
	//Getters & Setters
	public int getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
