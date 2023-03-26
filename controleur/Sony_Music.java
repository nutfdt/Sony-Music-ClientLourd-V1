package controleur;
import vue.VueConnexion;
import vue.VueGenerale;


public class Sony_Music {
	private static VueConnexion uneVueConnexion ; 
	private static VueGenerale uneVueGenerale;
	
	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion(); 
		
	}
	
	public static void gererVueConnexion (boolean action) {
		Sony_Music.uneVueConnexion.setVisible(action);
	}
	public static void gererVueGenerale (boolean action, Agent unAgent) {
		uneVueGenerale = new VueGenerale(unAgent);
		Sony_Music.uneVueGenerale.setVisible(action);
	}

}
