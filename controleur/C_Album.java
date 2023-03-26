package controleur;

import java.util.ArrayList;

import modele.ModeleAlbum;


public class C_Album {
	public static void insertAlbum (Album unAlbum) {
		//on controle les donn�es
		ModeleAlbum.insertAlbum(unAlbum);
	}
	public static ArrayList<Album> selectAllAlbums() {
		//on controle les donn�es
		return ModeleAlbum.selectAllAlbums();
	}
	public static void deleteAlbum (int idinter) {
		//on controle les donn�es
		ModeleAlbum.deleteAlbum(idinter);;
	}
	public static void updateAlbum (Album unAlbum) {
		//on controle les donn�es
		ModeleAlbum.updateAlbum(unAlbum);
	}
	public static Album selectWhereAlbum(int idAlbum) {
		//on controle les donn�es
		return ModeleAlbum.selectWhereAlbum(idAlbum);
	}
	public static Album selectWhereAlbum(String nom, int anneeSortie, int idArtiste) {
		//on controle les donn�es
		return ModeleAlbum.selectWhereAlbum(nom, anneeSortie, idArtiste);
	}
	
}