package controleur;
import java.util.ArrayList;
import modele.ModeleCategorie;

public class C_Categorie {
	
	public static void insertCategorie(Categorie uneCategorie) {
		ModeleCategorie.insertCategorie(uneCategorie);
	}
	
	public static ArrayList<Categorie> selectAllCategories() {
		return ModeleCategorie.selectAllCategories();
	}
	
	public static void deleteCategorie(int idcategorie) {
		ModeleCategorie.deleteCategorie(idcategorie);
	}
	
	public static void updateCategorie(Categorie uneCategorie) {
		ModeleCategorie.updateCategorie(uneCategorie);
	}
	
	public static Categorie selectWhereCategorie(int idcategorie) {
		return ModeleCategorie.selectWhereCategorie(idcategorie);
	}
	public static Categorie selectWhereCategorie(String type) {
		return ModeleCategorie.selectWhereCategorie(type);
	}
}
