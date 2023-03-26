package controleur;

public class Album {
	private int idalbum;
	private String nom;
	private int anneeSortie, idartiste;
	
	//Constructeurs
	public Album(int idalbum, String nom, int anneeSortie, int idartiste) {
		this.idalbum = idalbum;
		this.nom = nom;
		this.anneeSortie = anneeSortie;
		this.idartiste = idartiste;
	}
	public Album(String nom, int anneeSortie, int idartiste) {
		this.idalbum = 0;
		this.nom = nom;
		this.anneeSortie = anneeSortie;
		this.idartiste = idartiste;
	}
	
	public Album() {
		this.idalbum = 0;
		this.nom = "";
		this.anneeSortie = 0;
		this.idartiste = 0;
	}
	
	//Getters & Setters
	public int getIdalbum() {
		return idalbum;
	}
	public void setIdalbum(int idalbum) {
		this.idalbum = idalbum;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAnneeSortie() {
		return anneeSortie;
	}
	public void setAnneeSortie(int anneeSortie) {
		this.anneeSortie = anneeSortie;
	}
	public int getIdartiste() {
		return idartiste;
	}
	public void setIdartiste(int idartiste) {
		this.idartiste = idartiste;
	}
	
	
    
	
    
}
